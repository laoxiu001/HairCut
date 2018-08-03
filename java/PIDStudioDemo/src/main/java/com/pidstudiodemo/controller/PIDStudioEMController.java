package com.pidstudiodemo.controller;

import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pidstudiodemo.model.EmployeeManage;
import com.pidstudiodemo.model.EmployeeType;
import com.pidstudiodemo.service.EmployeeManageServic;
import com.pidstudiodemo.service.EmployeeTypeService;


@Controller
@RequestMapping(value="/employeeManage")
/**
 * 员工管理**/
public class PIDStudioEMController {
	@Autowired
	@Qualifier(value="employeeManageServic")
	private EmployeeManageServic employeeManageServic;
	@Autowired
	@Qualifier(value="employeeTypeService")
	private EmployeeTypeService employeeTypeService;
	
/**
 * ajax测试
 * **/
	@RequestMapping(value="/ssss",method={RequestMethod.POST})
	@ResponseBody
	public String ajaxTest(HttpServletRequest req){
		int page = Integer.parseInt( req.getParameter("page"));
		int size =Integer.parseInt( req.getParameter("size"));
		String status = req.getParameter("status");
		List<EmployeeManage> list = employeeManageServic.queryEmployeeManage(page, size, status);
		int maxPage = employeeManageServic.queryMaxPage(page, size, status);
		 JSONArray json = new JSONArray();
         for (EmployeeManage em : list) {
             JSONObject jo = new JSONObject();
             jo.put("name",em.getName()); 
             jo.put("id",em.getId()); 
             jo.put("number",em.getNumber());
             jo.put("phone",em.getPhone()); 
             jo.put("type", em.getEmployeeType().getName());
             jo.put("status", em.getStatus());
             json.add(jo);
        }
         JSONObject jo1 = new JSONObject();
         jo1.put("maxPage",maxPage); 
         json.add(jo1);
        return json.toString();
	}
/**
 * ajax多字段模糊查询
 * */
	@RequestMapping(value="/conditions",method={RequestMethod.POST})
	@ResponseBody
	public String queryConditions(HttpServletRequest req){
		String conditions =req.getParameter("conditions");
		String status = req.getParameter("status");
		List<EmployeeManage> list = employeeManageServic.queryConditions(conditions,status);
		 JSONArray json = new JSONArray();
         for (EmployeeManage em : list) {
             JSONObject jo = new JSONObject();
             jo.put("name",em.getName()); 
             jo.put("id",em.getId()); 
             jo.put("number",em.getNumber());
             jo.put("phone",em.getPhone()); 
             jo.put("status",em.getStatus()); 
             jo.put("type", em.getEmployeeType().getName());
             json.add(jo);
        }
        return json.toString();
	}
/**
 * 查询
 * 查询EmployeeManage表中的所有数据存入attribute 名为employeeListOn在职
 * employeeListOff 离职
 * employeecount 员工总数
 * @param employeePage 页数
 * @param employeeSize 每页显示的条数
 * @param session 存值
 * **/
	@RequestMapping(value="/",method={RequestMethod.GET})
	public String toEmployeeManage(Model m,@RequestParam(name="page",required=false,defaultValue="0")int page,
			@RequestParam(name="pageFalse",required=false,defaultValue="0")int pageFalse,
			@RequestParam(name="size",required=false,defaultValue="10") int size,
			HttpSession session){
		//查询在职员工
		try{List<EmployeeManage> listOn = employeeManageServic.queryEmployeeManage(page,size,"在职");
		//查询离职员工
		List<EmployeeManage> listOff = employeeManageServic.queryEmployeeManage(pageFalse,size,"离职");
		//查询记录总数
		int count = employeeManageServic.countEmployee();
		int maxPageOn = employeeManageServic.queryMaxPage(page,size,"在职");
		int maxPageOff = employeeManageServic.queryMaxPage(page,size,"离职");
		m.addAttribute("maxPageOn", maxPageOn);
		m.addAttribute("maxPageOff", maxPageOff);
		m.addAttribute("pageFalse", pageFalse);//Attribute 存入page 与页面数据交互
		m.addAttribute("page", page);//Attribute 存入page 与页面数据交互
		m.addAttribute("size", size);//Attribute 存入size 与页面数据交互
		addAttribute(m, "employeecount",count, true);//Attribute 存入count(员工总个数) 与页面数据交互
		addAttribute(m, "employeeListOn", listOn, true);//Attribute 存入listOn（在职的员工） 与页面数据交互
		addAttribute(m, "employeeListOff", listOff, true);//Attribute 存入listOn（离职的员工） 与页面数据交互
		m.addAttribute("remind", session.getAttribute("remind"));//获取session中的提示信息
		session.setAttribute("remind", "");//清空session中的remind的数据
		m.addAttribute("managerNumber", session.getAttribute("managerNumber"));
		return "employeeManage";
		}catch (Exception e) {
			// TODO: handle exception
			return "employeeManage";
		}
	}
	/**
	 * 查询员工详细信息
	 * Attribute detailedList
	 * @param id 员工的 id
	 * **/
	@RequestMapping(value="/detailedInformation")
	public String detailedInformation(@RequestParam(name="id")int id,Model m){
		List<EmployeeManage> listDetailed = new ArrayList<EmployeeManage>();
		//id查询员工信息
		listDetailed = employeeManageServic.queryDetailed(id);
		//Attribute 存入listDetailed（对应id员工的详细信息） 与页面数据交互
		addAttribute(m, "detailedList",listDetailed, true);
		return "employeeDetails";
	}
	/**
	 * update
	 * 修改员工资料
	 * @param session 储存
	 * @param employeeManage 表单对应绑定的表
	 * **/
	@RequestMapping(value="/updateEmployeeManage",method={RequestMethod.GET})
	public String updateEmployeeManage(
			@ModelAttribute(name="employeeManage") EmployeeManage em,Model m){
		String remind = employeeManageServic.updateEmployee(em);
		m.addAttribute("remind", remind);
		return "redirect:/employeeManage/";
	}
	/**
	 * insert
	 * 添加员工
	 * <form id="employeeManage" enctype="multipart/form-data"> </form>
	 * 需要有选择文件上传的<input tybe="file" name="file">按钮
	 * @param file 文件
	 * @param employeeManage 表单绑定的数据库的表
	 * @param session 存值
	 * **/
	@RequestMapping(value="/insertEmployeeManage",method={RequestMethod.POST})
	public String insertEmployeeManage(@RequestParam(name="file") MultipartFile file,
		@ModelAttribute(name="employeeManage") EmployeeManage em,Model m,HttpSession session){
		try{
			String photoURL = employeeManageServic.uploadFile(file);//储存图片返回图片地址
		em.setPhoto(photoURL);//将图片的存入em对象
		String remind = employeeManageServic.insertEmployee(em);//添加返回是否添加成功
		session.setAttribute("remind", remind);//存入提示信息
		return "redirect:/employeeManage/";}catch (Exception e) {
			// TODO: handle exception
			session.setAttribute("remind", "添加失败，输入信息不全");
			return "redirect:/employeeManage/";
		}
	}
	
	@RequestMapping(value="/employeeDetails",method={RequestMethod.GET})
	public String toEmployeeDryails(@RequestParam(name="id") int id,Model m){
		List<EmployeeManage> listem = employeeManageServic.queryDetailed(id);
		List<EmployeeType> listet = employeeTypeService.queryEmployeeType(1);
		m.addAttribute("listem", listem);
		m.addAttribute("listet", listet);
		return "employeeDetails";
	}
	@RequestMapping(value="/insertEmployeeDetails",method={RequestMethod.GET})
	public String doEmployeeDryails(Model m){
		EmployeeManage employeeManage = new EmployeeManage();
		EmployeeType employeeType = new EmployeeType();
		List<EmployeeManage> listem = new ArrayList<EmployeeManage>();
		employeeManage.setEmployeeType(employeeType);
		listem.add(employeeManage);
		List<EmployeeType> listet = employeeTypeService.queryEmployeeType(1);
		m.addAttribute("listem", listem);
		m.addAttribute("listet", listet);
		return "insertEmployeeDetails";
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