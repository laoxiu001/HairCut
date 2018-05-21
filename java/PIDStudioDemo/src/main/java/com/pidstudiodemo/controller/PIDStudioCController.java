package com.pidstudiodemo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pidstudiodemo.model.Customer;
import com.pidstudiodemo.service.CustomerService;

@Controller
@RequestMapping(value="/customer")
/**
 * 用户管理
 * **/
public class PIDStudioCController {
	@Autowired
	@Qualifier(value="customerService")
	private CustomerService customerService;
	
	/**
	 * 分页查询
	 *Attribute名 customerPage
	 *@param page 页数
	 *@param size 每页显示的条数
	 * **/
	@RequestMapping(value="/",method={RequestMethod.GET})
	private String queryCustomer(Model m,@RequestParam(name="page",required=false,defaultValue="0")int page,
			@RequestParam(name="size",required=false,defaultValue="10")int size,HttpSession session){
		Page<Customer> customerPage = customerService.queryCustomer(page,size);
		int count = customerService.countCustomer();
		addAttribute(m, "customerPage", customerPage, true);
		m.addAttribute("page", page);//attribute中存入page
		m.addAttribute("size", size);//attribute中存入size
		m.addAttribute("count", count);//用户的总数
		m.addAttribute("remind", session.getAttribute("remind"));//获取session中的提示信息
		m.addAttribute("managerNumber", session.getAttribute("managerNumber"));
		session.setAttribute("remind", "");//清空session中的remind的数据
		return "customer";
	}
	/**
	 * 添加用户记录
	 * @param customer form 表单绑定的表
	 * @param session 储存
	 * **/
	@RequestMapping(value="/insertCustomer",method={RequestMethod.POST})
	public String insertCustomer(@ModelAttribute("customer") Customer customer,
			HttpSession session){
		String remind = customerService.insertCustomer(customer);
		//像session中存入remind
		session.setAttribute("remind", remind);
		return "redirect:/customer/";
	}
	/**
	 * 修改用户记录
	 * @param customer form 表单绑定的表
	 * @param session 储存
	 * **/
	@RequestMapping(value="/updateCustomer",method={RequestMethod.POST})
	public String updateCustomer(@ModelAttribute(name="customer") Customer customer,
			HttpSession session){
		String remind = customerService.insertCustomer(customer);
		//像session中存入remind
		session.setAttribute("remind", remind);
		return "redirect:/customer/";
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
