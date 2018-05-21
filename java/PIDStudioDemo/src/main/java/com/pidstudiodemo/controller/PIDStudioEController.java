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

import com.pidstudiodemo.model.Expense;
import com.pidstudiodemo.service.ExpenseService;

@Controller
@RequestMapping(value="/expense")
public class PIDStudioEController {
	@Autowired
	@Qualifier(value="expenseService")
	private ExpenseService expenseService;
	/**
	 *查询费用
	 *@param page 页数
	 *@param size 每页显示的条数
	 **/
	@RequestMapping(value="/",method={RequestMethod.GET})
	private String queryExpense(Model m,@RequestParam(name="page",required=false,defaultValue="0")int page,
			@RequestParam(name="size",required=false,defaultValue="10")int size,HttpSession session){
		Page<Expense> customerPage = expenseService.queryExpense(page,size);
		int count = expenseService.countExpense();
		addAttribute(m, "customerPage", customerPage, true);
		m.addAttribute("page", page);//attribute中存入page
		m.addAttribute("size", size);//attribute中存入size
		m.addAttribute("count", count);//用户的总数
		m.addAttribute("remind", session.getAttribute("remind"));//获取session中的提示信息
		m.addAttribute("managerNumber", session.getAttribute("managerNumber"));
		session.setAttribute("remind", "");//清空session中的remind的数据
		return "expense";
	}
	
	@RequestMapping(value="/insertExpense",method={RequestMethod.POST})
	public String insertExpense(@ModelAttribute(name="expense") Expense expense,
			HttpSession session){
		String remind = expenseService.insertExpense(expense);
		session.setAttribute("remind", remind);
		return "redirect:/expense/";
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
