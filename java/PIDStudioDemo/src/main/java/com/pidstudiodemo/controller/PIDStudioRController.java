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
import com.pidstudiodemo.model.EmployeeManage;
import com.pidstudiodemo.model.PayList;
import com.pidstudiodemo.model.Record;
import com.pidstudiodemo.model.ServiceItem;
import com.pidstudiodemo.service.RecordService;
import com.pidstudiodemo.service.ServiceItemService;
@Controller
@RequestMapping(value="/record")
/**
 * 消费记录*/
public class PIDStudioRController {
	@Autowired
	@Qualifier(value="recordService")
	private RecordService recordService;
	@Autowired
	@Qualifier(value="serviceItemService")
	private ServiceItemService serviceItemService;
	/**
	 * 余额查询
	 * */
	@RequestMapping(value="/balance")
	@ResponseBody
	private String balance(HttpServletRequest req,HttpSession session){
		String userName = req.getParameter("conditions");
		try{
		double balance = recordService.queryDalance(userName);
		String a =""+balance;
		return a;}catch (Exception e) {
			// TODO: handle exception
			return "没有该用户";
		}
	}
	/**
	 * ajax多字段模糊查询
	 * */
	@RequestMapping(value="/query")
	@ResponseBody
	private String toquery(HttpServletRequest req){
		String conditions =req.getParameter("conditions");
		List<Record> list = recordService.queryConditions(conditions);
		 JSONArray json = new JSONArray();
         for (Record em : list) {
             JSONObject jo = new JSONObject();
             jo.put("userName",em.getUsername()); 
             jo.put("id",em.getId()); 
             jo.put("siName",em.getServiceItem().getName()); 
             jo.put("cName",em.getCustomer().getName()); 
             jo.put("number",em.getNumber());
             jo.put("payavle", em.getPayavle());
             String date = em.getDate().toString();
             jo.put("date", date);
             json.add(jo);
        }
        return json.toString();
	}
	/**
	 * ajax查询对应服务可选择的员工
	 * */
	@RequestMapping(value="/queryEmployee",method={RequestMethod.POST})
	@ResponseBody
	public String queryEmployee(@RequestParam("conditions") int typrId){
		 System.out.println("数据条数"+typrId);
		List<EmployeeManage> lsit = recordService.queryEmployee(typrId);
		 JSONArray json = new JSONArray();
         for (EmployeeManage em : lsit) {
             JSONObject jo = new JSONObject();
             jo.put("number", em.getNumber());
             jo.put("name",em.getName());
             jo.put("weighted",em.getEmployeeType().getWeightingFactor());
             json.add(jo);
        }
         System.out.println("数据条数"+json.size());
        return json.toString();
	}
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
		try{int count = recordService.countRecord();//记录总条数
		Page<Record> pageRecord = recordService.queryRecord(page,size);//查询总的Record表中所有的记录
		List<ServiceItem> pageServiceItem = serviceItemService.queryServiceItemAll(true);//查询在使用的服务类型
		int maxPage = recordService.queryMaxPage(page,size);//最大页数
		m.addAttribute("maxPage", maxPage);//保存最大页数
		m.addAttribute("page", page);//当前页数
		m.addAttribute("size", size);//当前每页显示的条数
		m.addAttribute("balance", session.getAttribute("balance1"));
		addAttribute(m, "pageRecord", pageRecord, true);//前端遍历的Record的记录
		m.addAttribute("pageServiceItem",pageServiceItem);//多选框遍历所有的服务类型
		m.addAttribute("count", count);//记录总条数
		m.addAttribute("remind", session.getAttribute("remind"));//提示信息
		m.addAttribute("managerNumber", session.getAttribute("managerNumber"));//当前登陆的
		m.addAttribute("discount", session.getAttribute("discount"));
		m.addAttribute("sum", session.getAttribute("sum"));
		m.addAttribute("sumPayavle",session.getAttribute("sumPayavle"));//应付的总额
		session.setAttribute("remind", "");//清空提示信息
		session.setAttribute("sumPayavle",0.00);//清空应付总额
		session.setAttribute("discount",0.00);
		session.setAttribute("sum", 0.00);
		session.setAttribute("balance1","余额：" + 0.00);
		return "service";}catch (Exception e) {
			// TODO: handle exception
			return "service";
		}
		
	}
	/**
	 * 添加消费记录
	 * @param record 表单绑定的表
	 * @param serviceIemId 服务类型的id 与多选框传入的值绑定
	 * */
	/**
	 * @param record
	 * @param session
	 * @param serviceIemId
	 * @return
	 */
	@RequestMapping(value="/insertRecord",method={RequestMethod.POST})
	public String insertRecord(@ModelAttribute(name="record") Record record,
			HttpSession session,@RequestParam(name="serviceIemId") int[] serviceIemId,
			@RequestParam(name="number") String [] number){
		session.setAttribute("remind", recordService.insertRecord(record,serviceIemId,number,session));
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
