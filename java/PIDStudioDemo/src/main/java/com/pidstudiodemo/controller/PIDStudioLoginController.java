package com.pidstudiodemo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.pidstudiodemo.service.LoginService;

@Controller
@RequestMapping(value="/login")
/**
 * 登录**/
public class PIDStudioLoginController {
	@Autowired
	@Qualifier(value="loginService")
	private LoginService loginService;


	@RequestMapping(value="/loginAction",method={RequestMethod.GET})
	public String dl(HttpSession session,Model m){
		String result = (String) session.getAttribute("result");
		m.addAttribute("result", result);
		session.setAttribute("result", "");
		//return "NewFile";
		return "login";
	}
	/**
	 * 店长登录判断
	 * login
	 * @author tanyongchao
	 * @param number 用户名 
	 * @param password 密码 
	 * @return resulthudge 返回的地址Service判断
	 * 
	 * */
	@RequestMapping(value="/loginAction",method={RequestMethod.POST})
	public String login(@RequestParam(value="number")String number,
		@RequestParam(value="password") String password,HttpSession session,
		Model m){
	String result;//存储账号密码验证的结果
	String resultJudge;//存储结果判断后返回的地址;
	result = loginService.loginSelect(number, password);//验证
	resultJudge=loginService.loginJudge(result);//返回地址
	session.setAttribute("managerNumber", number);//将员工工号存储到sesson
	session.setAttribute("result", result);
	session.setMaxInactiveInterval(-1);//将sesson时间设置为永久
	m.addAttribute("result", result);
	return resultJudge;
}
/**
 * 修改密码
 * @author tanyongchao
 * @param password 旧密码
 * @param newPassword_1新输入的密码
 * @param newPassword_2确定新密码 
 * @param number session获取店长的工号
 * @return
**/
@RequestMapping(value="/changePassword",method={RequestMethod.POST})
public String doChangePassword(String number,
		@RequestParam("password") String password, 
		@RequestParam("newPassword_1") String newPassword_1,
		@RequestParam("newPassword_2") String newPassword_2,
		HttpSession session,Model m){
	//将用户名储存到session
	number=(String) session.getAttribute("managerNumber");
	//修改密码
	String result = loginService.changePassword(number,password,newPassword_1,newPassword_2,session);
	m.addAttribute("result", "密码修改成功请重新登陆");
	return loginService.forgotPasswordResult(result,session);
}
/**
 * 发送短信
 * phoneNumber 电话号码
 * **/
@RequestMapping(value="/sendMsg",method={RequestMethod.POST})
//@ResponseBody//返回值为字符串
@ResponseBody
public String sendMsg(@RequestParam(name="phoneNumber")String phoneNumber,
		HttpSession session,Model m){
	try {
		
		//返回获取的验证码
		String result=loginService.sendMsg(phoneNumber,session);
		//session控制验证码生效的时间
		m.addAttribute("result", result);
		m.addAttribute("phoneNumber", phoneNumber);
		return result;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "forget";
	}
}
@RequestMapping(value="/forgotPassword",method={RequestMethod.GET})
public String toforgotPassword(Model m){
	String result="";
	m.addAttribute("result",result);
	return "forget";
	}
/**
 * 忘记密码
 * @author tanyongchao
 * @param msg 短息的验证码
 * @param phoneNumber 用户的手机号
 * @param newPassword_1 用户的新密码
 * @param newPassword_2 用户的新密码
 * @return
 * @throws Exception 
**/
@RequestMapping(value="/forgotPassword",method={RequestMethod.POST})
@ResponseBody
public String doforgotPassword(Model m,@RequestParam(name="msg")String msg,
		@RequestParam("newPassword_1") String newPassword_1,
		@RequestParam("newPassword_2") String newPassword_2,
		String number,HttpSession session){
	String result;//返回的结果
	//获取session中存储的验证码
	String code = (String) session.getAttribute("code");
	//获取电话
	System.out.println(code);
	String phoneNumber = (String) session.getAttribute("phoneNumber");
	//短息验证修改密码
	result=loginService.sMSVC(code,msg,phoneNumber,newPassword_1, newPassword_2);
	session.setAttribute("result", result);
	m.addAttribute("result", result);
	m.addAttribute("phoneNumber", phoneNumber);
	return result;
}
/**
 * 注销用户
 * */
	@RequestMapping(value="/cancellation",method={RequestMethod.GET})
	public String cancellation(HttpSession session,Model m){
		String result = loginService.cancellation(session);
		m.addAttribute("result", result);
		return "login";
	}

}
