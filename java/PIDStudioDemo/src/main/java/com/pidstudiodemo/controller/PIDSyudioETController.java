package com.pidstudiodemo.controller;
import java.util.List;




import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
 *@author tanyongchao
 *EmployeeType 获取表中的所有数据 存入Attribute 名为employeeTypeList
 ***/
@RequestMapping(value="/",method={RequestMethod.GET})
public String toemployeeType(Model m,HttpSession session){
	int count = employeeTypeService.countEmployeeType();
	List<EmployeeType> list=employeeTypeService.queryEmployeeType();
	addAttribute(m, "employeeTypeList", list,true);
	m.addAttribute("count", count);
	m.addAttribute("remind", session.getAttribute("remind"));
	m.addAttribute("managerNumber", session.getAttribute("managerNumber"));
	session.setAttribute("remind", "");;  
	return "employeeType";
	
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
 * 删除表需要传入id
 * **/
@RequestMapping(value="/deleteEmployeeType",method={RequestMethod.POST})
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
