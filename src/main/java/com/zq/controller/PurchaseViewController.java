package com.zq.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zq.commons.result.HighChartData;
import com.zq.commons.utils.CMCCConstant;
import com.zq.commons.utils.TypeUtils;
import com.zq.entity.basic.purchase.BasFirstBill;
import com.zq.entity.basic.purchase.BasFrameContract;
import com.zq.entity.basic.purchase.BasSecondBill;
import com.zq.entity.system.SysDic;
import com.zq.service.basic.capex.IBasCAPEXProjectService;
import com.zq.service.basic.purchase.IBasFirstBillService;
import com.zq.service.basic.purchase.IBasFrameContractService;
import com.zq.service.basic.purchase.IBasSecondBillService;
import com.zq.service.system.ISysDicService;


/** 
* @ClassName: PurchaseViewController 
* @Description: TODO(采购视图) 
* @author shujukuss 
* @date 2017年6月18日 下午7:05:47 
*  
*/
@Controller
@RequestMapping("/purchaseview/")
public class PurchaseViewController extends BaseController{
	
	@Autowired
	private IBasFirstBillService iBasFirstBillService;
	
	@Autowired
	private IBasSecondBillService iBasSecondBillService;
	
	@Autowired
	private IBasFrameContractService iBasFrameContractService;
	
	@Autowired
	private ISysDicService iSysDicService;
	
	private static Logger logger = Logger.getLogger(PurchaseViewController.class);  

	/** 
	* @Title: totalMoneyInfo 
	* @Description: TODO(采购金额) 
	* @author shujukuss 
	* @date 2017年7月5日 下午4:00:21 
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "totalMoneyInfo",method =RequestMethod.POST)
	public String totalMoneyInfo(HttpServletRequest request){
		request=iBasFirstBillService.getTotalMoneyFromBasFirstBill(request);
		request=iBasSecondBillService.getTotalMoneyFromBasSecondBill(request);
		return CMCCConstant.TotalMoneyInfo;		
	}
	
	/** 
	* @Title: totalProjectInfo 
	* @Description: TODO(采购项目数) 
	* @author shujukuss 
	* @date 2017年7月5日 下午4:00:24 
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "totalProjectInfo",method =RequestMethod.POST)
	public String totalProjectInfo(HttpServletRequest request){
		request=iBasFirstBillService.getTotalMoneyFromBasFirstBill(request);
		request=iBasSecondBillService.getTotalMoneyFromBasSecondBill(request);
		return CMCCConstant.TotalProjectInfo;		
	}
	
	/**
	* @Title: purchaseKindInfo
	* @Description: TODO(采购品类分析)
	* @author BigCoin
	* @date 2017年8月1日 下午2:54:17
	* @param @param request
	* @param @return 设定文件
	* @return String 返回类型
	* @throws
	*/
	@RequestMapping(value = "purchasekindinfo",method =RequestMethod.POST)
	public String purchaseKindInfo(HttpServletRequest request){
		
		Map<String,Double> categoryMoney = new HashMap<String, Double>();
		Map<String,Integer> categoryProject = new HashMap<String, Integer>();
		List<String> pinleiNames = new ArrayList<String>();
		List<SysDic> commonCodes = iSysDicService.getAllSysDicListByClass("purchasing_category");
		for(SysDic code: commonCodes){
			categoryMoney.put(code.getName(), new Double(0));
			categoryProject.put(code.getName(), new Integer(0));
			pinleiNames.add(code.getName());
		}
		int index = TypeUtils.getIntFromString(request.getParameter("index"));
		request.setAttribute("index", index);
		request=iBasSecondBillService.getPKindFromSecondBill(request,categoryMoney,categoryProject);
		request=iBasFrameContractService.getPKindFromFrameContract(request,categoryMoney,categoryProject);
		
		List<HighChartData> moneyDatas = new ArrayList<HighChartData>();
		List<HighChartData> countDatas = new ArrayList<HighChartData>();
		Double totalMoney = 0d;
		Integer totalCount = 0;
		for(int i=0,j=pinleiNames.size();i<j;i++){
			String pinleiName =pinleiNames.get(i);
			HighChartData moneyData = new HighChartData();
			HighChartData countData = new HighChartData();
			moneyData.setName(pinleiName);
			moneyData.setY(((Map<String,Double>) request.getAttribute("categoryMoney")).get(pinleiName));
			moneyDatas.add(moneyData);
			countData.setName(pinleiName);
			countData.setY(((Map<String,Integer>) request.getAttribute("categoryProject")).get(pinleiName));
			countDatas.add(countData);
			totalMoney += ((Map<String,Double>) request.getAttribute("categoryMoney")).get(pinleiName);
			totalCount += ((Map<String,Integer>) request.getAttribute("categoryProject")).get(pinleiName);
		}
		request.setAttribute("totalMoney", totalMoney);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("moneyDatas", moneyDatas);
		request.setAttribute("countDatas", countDatas);
		request.setAttribute("categoryNames", pinleiNames);
		return CMCCConstant.PurchaseKindInfo;		
	}
	
	/**
	* @Title: purchaseMethodInfo
	* @Description: TODO(采购方式分析)
	* @author BigCoin
	* @date 2017年8月2日 下午3:49:10
	* @param @param request
	* @param @return 设定文件
	* @return String 返回类型
	* @throws
	*/
	@RequestMapping(value = "purchasemethodinfo",method =RequestMethod.POST)
	public String purchaseMethodInfo(HttpServletRequest request){
		Date dataUpdateDate = null;
		Map<String,Double> zhichuMoney = new HashMap();
		Map<String,Integer> zhichuProject = new HashMap();
		List<String> methodNames = new ArrayList();
		//获取二级集采采购方式代码表
		List<SysDic> commonCodes = iSysDicService.getAllSysDicListByClass("purchasing_method");
		for(SysDic code: commonCodes){
			zhichuMoney.put(code.getName(), new Double(0));
			zhichuProject.put(code.getName(), new Integer(0));
			methodNames.add(code.getName());
		}
		int index = TypeUtils.getIntFromString(request.getParameter("index"));
		request.setAttribute("index", index);
		request = iBasSecondBillService.getPMethodFromSecondBill(request,zhichuMoney,zhichuProject);
		
		List<HighChartData> moneyDatas = new ArrayList();
		List<HighChartData> countDatas = new ArrayList();
		Double totalMoney = 0d;
		Integer totalCount = 0;
		for(int i=0,j=methodNames.size();i<j;i++){
			String methodName = methodNames.get(i);
			HighChartData moneyData = new HighChartData();
			HighChartData countData = new HighChartData();
			moneyData.setName(methodName);
			moneyData.setY(((Map<String,Double>) request.getAttribute("zhichuMoney")).get(methodName));
			moneyDatas.add(moneyData);
			countData.setName(methodName);
			countData.setY(((Map<String,Integer>) request.getAttribute("zhichuProject")).get(methodName));
			countDatas.add(countData);
			totalMoney += ((Map<String,Double>) request.getAttribute("zhichuMoney")).get(methodName);
			totalCount += ((Map<String,Integer>) request.getAttribute("zhichuProject")).get(methodName);
		}
		request.setAttribute("totalMoney", totalMoney);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("moneyDatas", moneyDatas);
		request.setAttribute("countDatas", countDatas);
		request.setAttribute("methodNames", methodNames);
		return CMCCConstant.PurchaseMethodInfo;
	}
}
