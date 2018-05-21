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

import com.pidstudiodemo.model.Record;
import com.pidstudiodemo.service.RecordService;
@Controller
@RequestMapping(value="/record")
/**
 * 消费记录*/
public class PIDStudioRController {
	@Autowired
	@Qualifier(value="recordService")
	private RecordService recordService;
	/**
	 * 消费记录分页查询
	 * @param page 页数
	 * @param size 显示条数
	 * @param session 存值
	 * **/
	@RequestMapping(value="/",method={RequestMethod.GET})
	public String queryRecord(Model m,HttpSession session,
			@RequestParam(name="page",required=false,defaultValue="0") int page,
			@RequestParam(name="size",required=false,defaultValue="10") int size){
		int count = recordService.countRecord();
		Page<Record> pageRecord = recordService.queryRecord(page,size);
		addAttribute(m, "pageRecord", pageRecord, true);
		m.addAttribute("count", count);
		m.addAttribute("remind", session.getAttribute("remind"));
		m.addAttribute("managerNumber", session.getAttribute("managerNumber"));
		session.setAttribute("remind", "");
		return "service";
		
	}
	/**
	 * 添加消费记录
	 * @param record 表单绑定的表
	 * @param serviceIemId 服务类型的id 与多选框传入的值绑定
	 * */
	@RequestMapping(value="/insertRecord",method={RequestMethod.POST})
	public String insertRecord(@ModelAttribute(name="record") Record record,
			HttpSession session,@RequestParam(name="serviceIemId") int[] serviceIemId,
			@RequestParam(name="number") String [] number){
		recordService.insertRecord(record,serviceIemId,number);
		return "redirect:/record/";
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
