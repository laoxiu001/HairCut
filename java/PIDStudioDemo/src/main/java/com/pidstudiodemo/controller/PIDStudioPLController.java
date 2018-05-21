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

import com.pidstudiodemo.model.PayList;
import com.pidstudiodemo.service.PayListService;
@Controller
@RequestMapping(value="/payList")
/**
 *充值*/
public class PIDStudioPLController {
	@Autowired
	@Qualifier(value="payListService")
	private PayListService payListService;
	/**
	 * 查询用户充值记录
	 * Attribute名为queryPayList
	 * @param payPage 页数
	 * @param paySize 显示条数
	 * @param session 存值
	 * **/
	@RequestMapping(value="/",method={RequestMethod.GET})
	public String toPayList(Model m,@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="10")int size,HttpSession session){
		//查充值记录表
		//返回当前页size条数据的记录存入pagePayList
		int count = payListService.countPayList();
		Page<PayList> pagePayList = payListService.queryPayList(page,size);
		m.addAttribute("payPage", page);//
		m.addAttribute("paySize", size);
		m.addAttribute("count", count);
		addAttribute(m, "pagePayList", pagePayList, true);
		m.addAttribute("remind", session.getAttribute("remind"));
		m.addAttribute("managerNumber", session.getAttribute("managerNumber"));
		session.setAttribute("remind", "");
		return "pay";
	}
	/**
	 * 插入充值记录
	 * @param payList 表单绑定的表
	 * @param session 存值
	 * */
	@RequestMapping(value="/insertPayList")
	public String pay(@ModelAttribute(name="payList") PayList paylist,
			HttpSession session){
		String remind = payListService.insertPayList(paylist);//添加数据返回是否添加成功
		session.setAttribute("remind", remind);//remind 存入session
		return "redirect:/payList/";
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
