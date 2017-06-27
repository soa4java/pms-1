package com.zq.controller.REST;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zq.commons.utils.CMCCConstant;

/** 
* @ClassName: FinancialViewController 
* @Description: TODO(财务视图) 
* @author shujukuss 
* @date 2017年6月18日 下午7:01:20 
*  
*/
@Controller
@RequestMapping("/")
public class FinancialViewController {
	
	private static Logger logger = Logger.getLogger(FinancialViewController.class);  
	
	/** 
	* @Title: financialView 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author shujukuss 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "financialview", method = RequestMethod.GET)
	public String financialView() {
		return CMCCConstant.FVDashBoard;
	}
}