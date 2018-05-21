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

import com.pidstudiodemo.model.ServiceItem;
import com.pidstudiodemo.service.ServiceItemService;

@Controller
@RequestMapping(value="/serviceItem")
/**
 * 服务管理表
 * */
public class PIDSyudioSController {
	@Autowired
	@Qualifier(value="serviceItemService")
	private ServiceItemService serviceItemService;
	/**
	 * 查询服务类表
	 * @param session 储存值
	 * @param m 与页面进行数据传递
	 * */
	@RequestMapping(value="/",method={RequestMethod.GET})
	public String queryServiceItem(HttpSession session,Model m){
		List<ServiceItem> pageServiceItemTrue = serviceItemService.queryServiceItemAll(true);//查询出全部的服务信息
		List<ServiceItem> pageServiceItemFalse = serviceItemService.queryServiceItemAll(false);//查询出全部的服务信息
		m.addAttribute("pageServiceItemTrue", pageServiceItemTrue);//将服务信息的List对象存入attribute
		m.addAttribute("pageServiceItemFalse", pageServiceItemFalse);//将服务信息的List对象存入attribute
		m.addAttribute("remind", session.getAttribute("remind"));//将提示信息存入attribute
		session.setAttribute("remind", "");//将提示信息清空
		m.addAttribute("managerNumber", session.getAttribute("managerNumber"));
		return "serviceItem";
	}
	/**
	 * 添加服务类型
	 * @param serviceItem 与页面的表单绑定
	 * @param session 储存值
	 * */
	@RequestMapping(value="/insertServiceItem",method={RequestMethod.POST})
	public String insertServiceItem(@ModelAttribute(name="serviceItem") ServiceItem serviceItem
			,HttpSession session){
		String remind = serviceItemService.insertServiceItem(serviceItem);
		session.setAttribute("remind", remind);
		return	"redirect:/serviceItem/";
	}
	/**
	 * 删除服务类型
	 * session 储存
	 * */
	@RequestMapping(value="/deleteServiceItem")
	public String updateStatus(@ModelAttribute("serviceItem") ServiceItem serviceItem,
			HttpSession session){
		String remind = serviceItemService.updateStatus(serviceItem);
		session.setAttribute("remind", remind);
		return "rediret:/serviceItem/";
		}
	}
