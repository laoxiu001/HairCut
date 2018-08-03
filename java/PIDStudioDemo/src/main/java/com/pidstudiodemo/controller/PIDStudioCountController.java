package com.pidstudiodemo.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.MediaSize.JIS;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pidstudiodemo.model.Record;
import com.pidstudiodemo.service.RecordService;
import com.pidstudiodemo.service.SalaryService;
import com.pidstudiodemo.view.model.EmployeeManageSum;
import com.pidstudiodemo.view.model.IncomeAndSpending;

@Controller
@RequestMapping(value="/count")
public class PIDStudioCountController {
	@Autowired
	@Qualifier(value="salaryService")
	private SalaryService salaryService;
	@Autowired
	@Qualifier(value="recordService")
	private RecordService rs;
	/**
	 * ajax多字段模糊查询
	 * */
	@RequestMapping(value="/query")
	@ResponseBody
	private String toquery(HttpServletRequest req){
		String conditions =req.getParameter("conditions");
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");// 年	月份字段
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");// 获取月份字段
		DecimalFormat df = new DecimalFormat("#0.00");
		List<EmployeeManageSum> list = salaryService.queryConditions(conditions);
		 JSONArray json = new JSONArray();
         for (EmployeeManageSum ems : list) {
             JSONObject jo = new JSONObject();
             jo.put("name", ems.getEmployeeManage().getName());
             jo.put("number", ems.getEmployeeManage().getNumber());
             jo.put("count", ems.getCount());
             jo.put("basicSalary", ems.getEmployeeType().getBasicSalary());
             jo.put("commissionRate", ems.getEmployeeType().getCommissionRate());
             jo.put("weightingFactor", ems.getEmployeeType().getWeightingFactor());
             jo.put("sumSalary",df.format(ems.getSumSalary()));
             jo.put("sumPayavle",df.format(ems.getSumPayavle()));
             Date date = ems.getRecord().getDate();//截取为年月格式的字段
             jo.put("date",sdf.format(date));
             int month = Integer.parseInt(sdfMonth.format(date));//截取为月份字段
             jo.put("month", month);
             json.add(jo);
        }
         System.out.println(json.size());
        return json.toString();
	}
/**
 * 统计工资结算
 * */
	@RequestMapping(value="/salary",method={RequestMethod.GET })
	public String querySalary(Model m,HttpSession session,
	@RequestParam(name ="month",required=false,defaultValue="0") int month,
	@RequestParam(name="page",required=false,defaultValue="0")int page,
	@RequestParam(name="size",required=false,defaultValue="10")int size){
		try{
		 List<EmployeeManageSum> list = salaryService.querySalary(page,size,month,session);
		 int maxPage = salaryService.queryMaxPage(page, size);
		 m.addAttribute("month",session.getAttribute("dateMonth"));
		 m.addAttribute("maxPage", maxPage);
		 m.addAttribute("page", page);//attribute中存入page
		 m.addAttribute("size", size);//attribute中存入size
		 m.addAttribute("listSalary", list);
		 m.addAttribute("remind", session.getAttribute("remind"));//获取session中的提示信息
		 session.setAttribute("remind", "");//清空session中的remind的数据
		return "salary";}catch (Exception e) {
			return "salary";
			// TODO: handle exception
		}
	}
	/**
	 * 员工的服务详细
	 * */
	@RequestMapping(value="/salaryDetails",method={RequestMethod.GET})
	public String querySalaryDetails(Model m,HttpSession session,
			@RequestParam(name="number") String number,
			@RequestParam(name ="month",required=false,defaultValue="0") int month,
			@RequestParam(name="page",required=false,defaultValue="0")int page,
			@RequestParam(name="size",required=false,defaultValue="10")int size){
		List<Record> list = salaryService.querySalaryDetails(page,size,number,month);
		int maxPage = salaryService.queryMaxPage(page, size, number,month);
		m.addAttribute("number", number);
		m.addAttribute("maxPage", maxPage);
		m.addAttribute("month", month);
		m.addAttribute("page", page);//attribute中存入page
		m.addAttribute("size", size);//attribute中存入size
		m.addAttribute("listRecord", list);
		return "serviceDetail";
	}
	/**
	 * 统计营收
	 * 默认为当前的年 当前的月 
	 * */
	@RequestMapping(value="/statistics",method={RequestMethod.GET})
	public String queryStatistics(Model m){
		List<Integer> listy =salaryService.querYear(); 
		List<String> list = rs.queryService();
		String number = rs.queryNumber();
		m.addAttribute("list", list);
		m.addAttribute("number",number);
		m.addAttribute("listy", listy);
		return "statistics";
	}
	
	@RequestMapping(value="/queryMinToMaxSINameIncome",method={RequestMethod.POST})
	@ResponseBody
	public String queryMinToMaxSINameIncome(@RequestParam(name="year",required=false,defaultValue="0")int year,
			@RequestParam(name="name",required=false,defaultValue="")String name,
			@RequestParam(name="minMonth",required=false,defaultValue="0") int minMonth,
			@RequestParam(name="maxMonth",required=false,defaultValue="0") int maxMonth){
		List<IncomeAndSpending> listsins =salaryService.queryMinToMaxSINameIncome(year,name,minMonth,maxMonth);//某一服务项目几月到几月收入
		JSONArray json = new JSONArray();
		for(IncomeAndSpending ias : listsins){
			JSONObject jo = new JSONObject();
			jo.put("monthIncome", ias.getMonth());
			jo.put("sumIncome", ias.getSum());
			json.add(jo);
		}
		return json.toString();
	}
	/**
	 * 员工指定月份
	 * 创造的收益*/
	@RequestMapping(value="/queryNumberIncomeTo",method={RequestMethod.POST})
	@ResponseBody
	public String queryNumberIncomeTo(@RequestParam(name="year",required=false,defaultValue="0")int year,
			@RequestParam(name="number",required=false,defaultValue="")String number,
			@RequestParam(name="minMonth",required=false,defaultValue="0") int minMonth,
			@RequestParam(name="maxMonth",required=false,defaultValue="0") int maxMonth){
		List<IncomeAndSpending> listsins =salaryService.queryNumberIncomeTo(year,number,minMonth,maxMonth);//某一服务项目几月到几月收入
		JSONArray json = new JSONArray();
		for(IncomeAndSpending ias : listsins){
			JSONObject jo = new JSONObject();
			jo.put("monthIncome", ias.getMonth());
			jo.put("sumIncome", ias.getSum());
			jo.put("count", ias.getCount());
			json.add(jo);
		}
		return json.toString();
	}
	/**
	 * 查询收入和支出
	 * 指定月份的收入和支出*/
	@RequestMapping(value="/queryMonthIncome",method={RequestMethod.POST})
	@ResponseBody
	public String queryMonthIncome(@RequestParam(name="year",required=false,defaultValue="0")int year,
			@RequestParam(name="minMonth",required=false,defaultValue="0") int minMonth,
			@RequestParam(name="maxMonth",required=false,defaultValue="0") int maxMonth){
		List<IncomeAndSpending> listymti =salaryService.queryMinToMaxIncome(year,minMonth,maxMonth);//几月到几月查询收入
		List<IncomeAndSpending> listymts =salaryService.queryMinToMaxSpending(year,minMonth,maxMonth);//几月到几月查询支出
		JSONArray json = new JSONArray();
		for(IncomeAndSpending ias : listymti){
			JSONObject jo = new JSONObject();
			jo.put("monthIncome", ias.getMonth());
			jo.put("sumIncome", ias.getSum());
			json.add(jo);
		}
		for(IncomeAndSpending ias : listymts){
			JSONObject jo = new JSONObject();
			jo.put("monthSpending", ias.getMonth());
			jo.put("sumSpending", ias.getSum());
			json.add(jo);
		}
		return json.toString();
	}
}
