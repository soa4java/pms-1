package com.zq.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zq.commons.result.Tree;
import com.zq.commons.shiro.captcha.CMCCCaptcha;
import com.zq.commons.utils.CMCCConstant;
import com.zq.commons.utils.StringUtils;
import com.zq.service.system.IResourceService;


/** 
* @ClassName: LoginController 
* @Description: TODO(登陆) 
* @author shujukuss 
* @date 2017年6月18日 下午7:01:35 
*  
*/
@Controller
@RequestMapping("/") 
public class LoginController extends BaseController{	
	private static Logger logger = Logger.getLogger(LoginController.class);  
	
    @Autowired
    private CMCCCaptcha cmccCaptcha;
    @Autowired
    private IResourceService iResourceService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)  
	 public String login(){  
		logger.info("登录中！"); 
		return CMCCConstant.LOGIN;  
	 } 
	/** 
	* @Title: login 
	* @Description: TODO(登录校验) 
	* @author shujukuss 
	* @date 2017年6月25日 下午6:56:04 
	* @param @param user
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model,String captcha, 
            @RequestParam(value = "rememberMe", defaultValue = "0") Integer rememberMe) {		 	
		String msg = "";
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		logger.info("userName:" + userName + "请求登录！");
		try {
			if (StringUtils.isBlank(userName)) {
				logger.warn("用户名不能为空");
				throw new RuntimeException("用户名不能为空");
			}
			if (StringUtils.isBlank(password)) {
				logger.warn("密码不能为空");
				throw new RuntimeException("密码不能为空");
			}
		} catch (RuntimeException e) {
			msg = e.getMessage();
			model.addAttribute("message", msg);
			logger.warn(msg);
			return CMCCConstant.LOGIN;
		}
/*		try {
			if (StringUtils.isBlank(captcha)) {
				throw new RuntimeException("验证码不能为空");
			}
			if (!cmccCaptcha.validate(request, response, captcha)) {
				throw new RuntimeException("验证码错误");
			}
		} catch (RuntimeException e) {
			msg = e.getMessage();
			model.addAttribute("message", msg);
			logger.warn(msg);
			model.addAttribute("userName", userName);
			model.addAttribute("password", password);
			return CMCCConstant.LOGIN;
		}*/

		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		token.setRememberMe(1 == rememberMe);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				logger.info("userName:" + userName + "登录成功！");
		        Session session = subject.getSession();  
		        logger.info("sessionId:" + session.getId());
		        logger.info("IP地址:" + session.getHost());
		        logger.info("session存活时间:" + session.getTimeout()); 
		        session.setAttribute("userName", userName);
		        List<Tree> NavMenu = iResourceService.selectTree(getShiroUser());
		        session.setAttribute("NavMenu", NavMenu);  
				return CMCCConstant.CVDashBoard;
			} else {
				return CMCCConstant.LOGIN;
			}
		} catch (IncorrectCredentialsException e) {
			msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
			model.addAttribute("message", msg);
			logger.warn(msg);
		} catch (ExcessiveAttemptsException e) {
			msg = "登录失败次数过多,锁定半小时";
			model.addAttribute("message", msg);
			logger.warn(msg);
		} catch (LockedAccountException e) {
			msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
			model.addAttribute("message", msg);
			logger.warn(msg);
		} catch (DisabledAccountException e) {
			msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
			model.addAttribute("message", msg);
			logger.warn(msg);
		} catch (ExpiredCredentialsException e) {
			msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
			model.addAttribute("message", msg);
			logger.warn(msg);
		} catch (UnknownAccountException e) {
			msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
			model.addAttribute("message", msg);
			logger.warn(msg);
		} catch (UnauthorizedException e) {
			msg = "您没有得到相应的授权！" + e.getMessage();
			model.addAttribute("message", msg);
			logger.warn(msg);
		}
		return CMCCConstant.LOGIN;
	}
    
	/** 
	* @Title: unauth 
	* @Description: TODO(未授权) 
	* @author shujukuss 
	* @date 2017年6月27日 下午6:07:00 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "unauth", method = RequestMethod.POST)
    public String unauth() {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return CMCCConstant.LOGIN;
        }
        return "unauth";
    }


    /** 
    * @Title: logout 
    * @Description: TODO(退出) 
    * @author shujukuss 
    * @date 2017年6月27日 下午6:08:03 
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        logger.info("登出");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return CMCCConstant.LOGIN;
    }
    
  
    /** 
    * @Title: captcha 
    * @Description: TODO(图形验证码) 
    * @author shujukuss 
    * @date 2017年6月28日 上午11:33:26 
    * @param @param request
    * @param @param response    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    @RequestMapping(value = "captcha.jpg", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        cmccCaptcha.generate(request, response);
    }
}
