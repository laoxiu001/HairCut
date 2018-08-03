package com.pidstudiodemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
	 * ajax多字段模糊查询
	 * */
	@RequestMapping(value="/query")
	@ResponseBody
	private String toquery(HttpServletRequest req){
		String conditions =req.getParameter("conditions");
		List<PayList> list = payListService.queryConditions(conditions);
		 JSONArray json = new JSONArray();
         for (PayList em : list) {
             JSONObject jo = new JSONObject();
             jo.put("userName",em.getUserName()); 
             jo.put("id",em.getId()); 
             jo.put("type",em.getType()); 
             jo.put("gold",em.getGold()); 
             String date = em.getDate().toString();
             jo.put("date", date);
             json.add(jo);
        }
         System.out.println(json.size());
        return json.toString();
	}
	/**
	 * 查询用户充值记录
	 * Attribute名为queryPayList
	 * @param Page 页数
	 * @param Size 显示条数
	 * @param session 存值
	 * **/
	@RequestMapping(value="/",method={RequestMethod.GET})
	public String toPayList(Model m,@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="10")int size,HttpSession session){
		//查充值记录表
		//返回当前页size条数据的记录存入pagePayList
		try{int count = payListService.countPayList();
		Page<PayList> pagePayList = payListService.queryPayList(page,size);
		int maxPage = payListService.queryMaxPage(page,size);
		m.addAttribute("maxPage", maxPage);
		m.addAttribute("page", page);
		m.addAttribute("size", size);
		m.addAttribute("count", count);
		addAttribute(m, "pagePayList", pagePayList, true);
		m.addAttribute("balance", "余额："+session.getAttribute("balance"));
		m.addAttribute("remind", session.getAttribute("remind"));//获得提示信息
		m.addAttribute("managerNumber", session.getAttribute("managerNumber"));
		session.setAttribute("balance","0.00");
		session.setAttribute("remind", "");//清空提示信息
		return "pay";}catch (Exception e) {
			// TODO: handle exception
			return "pay";
		}
	}
	/**
	 * 插入充值记录
	 * @param payList 表单绑定的表
	 * @param session 存值
	 * */
	@RequestMapping(value="/insertPayList",method={RequestMethod.POST})
	public String pay(@ModelAttribute(name="payList") PayList paylist,
			HttpSession session){
		String remind = payListService.insertPayList(paylist,session);//添加数据返回是否添加成功
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