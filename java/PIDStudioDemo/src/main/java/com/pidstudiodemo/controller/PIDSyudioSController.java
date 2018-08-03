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
import com.pidstudiodemo.model.EmployeeManage;
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
	/**
	 * ajax测试
	 * **/
		@RequestMapping(value="/serviceItem",method={RequestMethod.POST})
		@ResponseBody
		public String ajaxTest(HttpServletRequest req){
			int page = Integer.parseInt( req.getParameter("page"));
			int size =Integer.parseInt( req.getParameter("size"));
			List<ServiceItem> list = serviceItemService.queryServiceItemAllF(page, size, false);
			int maxPage = serviceItemService.queryMaxPage(page, size, false);
			System.out.println(maxPage);
			 JSONArray json = new JSONArray();
	         for (ServiceItem si : list) {
	             JSONObject jo = new JSONObject();
	             jo.put("name",si.getName()); 
	             jo.put("id",si.getId()); 
	             jo.put("price",si.getPrice());
	             jo.put("status",si.getStatus()); 
	             jo.put("type", si.getType());
	             jo.put("introduction",si.getIntroduction());
	             json.add(jo);
	        }
	         JSONObject jo1 = new JSONObject();
	         jo1.put("maxPage",maxPage); 
	         json.add(jo1);
	        return json.toString();
		}
		/**
		 * ajax模糊查询
		 * **/
		@RequestMapping(value="/sIConditions",method={RequestMethod.POST})
		@ResponseBody
		public String queryConditions(HttpServletRequest req){
			String conditions =req.getParameter("conditions");
			String statys = req.getParameter("status");
			List<ServiceItem> list = serviceItemService.queryConditions(conditions,statys);
			 JSONArray json = new JSONArray();
	         for (ServiceItem si : list) {
	             JSONObject jo = new JSONObject();
	             jo.put("name",si.getName()); 
	             jo.put("id",si.getId()); 
	             jo.put("price",si.getPrice());
	             jo.put("status",si.getStatus()); 
	             jo.put("type", si.getType());
	             jo.put("introduction",si.getIntroduction());
	             json.add(jo);
	        }
	         System.out.println(list.size());
	        return json.toString();
		}
	@RequestMapping(value="/",method={RequestMethod.GET})
	public String queryServiceItem(HttpSession session,Model m,
			@RequestParam(name="page",required=false,defaultValue="0") int page,
			@RequestParam(name="size",required=false,defaultValue="10") int size,
			@RequestParam(name="pageFalse",required=false,defaultValue="0") int pageFalse,
			@RequestParam(name="status",required=false,defaultValue="true") Boolean status){
		try{List<ServiceItem> pageServiceItemTrue = serviceItemService.queryServiceItemAllF(page,size,true);//查询出全部的服务信息
		List<ServiceItem> pageServiceItemFalse = serviceItemService.queryServiceItemAllF(pageFalse,size,false);//查询出全部的服务信息
		int maxPageTrue = serviceItemService.queryMaxPage(page,size,status);
		int maxPageFalse = serviceItemService.queryMaxPage(page,size,status);
		m.addAttribute("maxPageTrue", maxPageTrue);//最大页数
		m.addAttribute("maxPageFalse", maxPageFalse);//最大页数
		m.addAttribute("page", page);//attribute中存入page
		m.addAttribute("pageFalse", pageFalse);//attribute中存入page
		m.addAttribute("size", size);//attribute中存入size
		m.addAttribute("pageServiceItemTrue", pageServiceItemTrue);//将服务信息的List对象存入attribute
		m.addAttribute("pageServiceItemFalse", pageServiceItemFalse);//将服务信息的List对象存入attribute
		m.addAttribute("remind", session.getAttribute("remind"));//将提示信息存入attribute
		session.setAttribute("remind", "");//将提示信息清空
		m.addAttribute("managerNumber", session.getAttribute("managerNumber"));
		m.addAttribute("employeeType", serviceItemService.queryEmployeeType());
		return "serviceItem";}
		catch (Exception e) {
			// TODO: handle exception
			return "serviceItem";
		}
	}
	/**
	 * 添加服务类型
	 * @param serviceItem 与页面的表单绑定
	 * @param session 储存值
	 * */
	@RequestMapping(value="/insertServiceItem",method={RequestMethod.POST})
	public String insertServiceItem(@ModelAttribute(name="serviceItem") ServiceItem serviceItem,
		@RequestParam(name="type") int[] typrId,HttpSession session){
		String remind = serviceItemService.insertServiceItem(serviceItem,typrId);
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
		return "redirect:/serviceItem/";
		}
	}
