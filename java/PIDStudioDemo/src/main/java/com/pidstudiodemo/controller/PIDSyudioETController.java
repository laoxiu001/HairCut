package com.pidstudiodemo.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pidstudiodemo.model.Customer;
import com.pidstudiodemo.model.EmployeeType;
import com.pidstudiodemo.service.EmployeeTypeService;

@Controller
@RequestMapping(value="/employeeType")
/**
 * 员工类型管理
 * */
public class PIDSyudioETController {
@Autowired
@Qualifier(value="employeeTypeService")
private EmployeeTypeService employeeTypeService;

/**
 * ajax多字段模糊查询
 * */
@RequestMapping(value="/query")
@ResponseBody
private String toquery(HttpServletRequest req){
	String conditions =req.getParameter("conditions");
	List<EmployeeType> list = employeeTypeService.queryConditions(conditions);
	 JSONArray json = new JSONArray();
     for (EmployeeType em : list) {
         JSONObject jo = new JSONObject();
         jo.put("name",em.getName()); 
         System.out.println(em.getName());
         jo.put("id",em.getId()); 
         jo.put("WeightingFactor",em.getWeightingFactor()); 
         jo.put("basicSalary",em.getBasicSalary()); 
         jo.put("commissionRate", em.getCommissionRate());
         json.add(jo);
    }
     System.out.println(json.size());
    return json.toString();
}
/**
 *@author tanyongchao
 *EmployeeType 获取表中的所有数据 存入Attribute 名为employeeTypeList
 ***/
@RequestMapping(value="/",method={RequestMethod.GET})
public String toemployeeType(Model m,HttpSession session,			
		@RequestParam(name="page",required=false,defaultValue="0") int page,
		@RequestParam(name="size",required=false,defaultValue="10") int size){
	try{int count = employeeTypeService.countEmployeeType();
	List<EmployeeType> list=employeeTypeService.queryEmployeeTypeF(page,size,1);
	int maxPage = employeeTypeService.queryMaxPage(page,size);
	addAttribute(m, "employeeTypeList", list,true);//在用服务的所有记录
	m.addAttribute("maxPage", maxPage);//最大页数
	m.addAttribute("page", page);//attribute中存入page
	m.addAttribute("size", size);//attribute中存入size
	m.addAttribute("count", count);//所有的记录条数
	m.addAttribute("remind", session.getAttribute("remind"));//提示信息
	m.addAttribute("managerNumber", session.getAttribute("managerNumber"));//获取当前的店长工号
	session.setAttribute("remind", "");  //清空提示信息
	return "employeeType";
	}catch (Exception e) {
		// TODO: handle exception
		return "employeeType";
	}
}
/**
 * update
 * 修改表需要提交表tr中所有的数据
 * **/
@RequestMapping(value="/updateEmployeeType",method={RequestMethod.POST})
public String updateEmployeeType(@ModelAttribute("employeeType") EmployeeType employeeType,
		Model m,HttpSession session){
	String remind = employeeTypeService.updateEmployeeType(employeeType);
	session.setAttribute("remind", remind);
	return "redirect:/employeeType/";
}
/**
 * delete
 * 删除表需要传入id 在页面显示中修改标识字段让 记录不被查询到
 * **/
@RequestMapping(value="/deleteEmployeeType",method={RequestMethod.GET})
public String deleteEmployeeType(@RequestParam(name="id")int id,Model m,HttpSession session){
	String remind = employeeTypeService.deleteEmployeeType(id);
	session.setAttribute("remind", remind);
	return "redirect:/employeeType/";
}
/**
 * employeeType
 * insert
 * 增加字段需要提交表tr中所有的数据
 * **/
@RequestMapping(value="/insertEmployeeType",method={RequestMethod.POST})
public String insertEmployeeType(@ModelAttribute("employeeType") EmployeeType employeeType,
		Model m,HttpSession session){
	String remind = employeeTypeService.insertEmployeeType(employeeType);
	session.setAttribute("remind", remind);
	return "redirect:/employeeType/";
}
private void addAttribute(Model m,String attributeName,Object attributeValue,boolean replace){
	if(replace==true){//判断是否添加attribute
		m.addAttribute(attributeName, attributeValue);
	}else{
		if(!m.containsAttribute(attributeName)){//判断是否已经存在的attribute
			m.addAttribute(attributeName, attributeValue);
		}
	}
	}
}
