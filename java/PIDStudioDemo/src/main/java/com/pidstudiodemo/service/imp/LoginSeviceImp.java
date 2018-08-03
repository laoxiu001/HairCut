package com.pidstudiodemo.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.TableGenerator;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;










import com.aliyuncs.exceptions.ClientException;
import com.pidstudiodemo.Util.AliyunMessageUtil;
import com.pidstudiodemo.dao.LoginDao;
import com.pidstudiodemo.model.EmployeeManage;
import com.pidstudiodemo.service.LoginService;
@Service(value="loginService")
public class LoginSeviceImp implements LoginService {
@Autowired
@Qualifier(value="loginDao")
private LoginDao loginDao;
@Autowired
@Qualifier(value="aliyunMessageUtil")
private AliyunMessageUtil aliyunMessageUtil;
	//判断是否登录成功 并返回
	public String loginSelect(String number,String password) {
		// TODO Auto-generated method stub
		//返回的结果
		String result;
		if(StringUtils.hasText(number)){
			if(StringUtils.hasText(password)){
		if(loginDao.login(number, password)!= null){
			result = "登录成功";
			System.out.println(result);
		}
	   else{
		   result="密码错误或账号不存在";
		}
		}else{
			result="密码不能为空";
		}
		}else{
			result="账号不能为空";
		}
		return result;
	}
	//判断登录返回的地址
	public String loginJudge(String result) {
		// TODO Auto-generated method stub
		if(result.equals("登录成功")){
			return "redirect:/customer/";
		}else{
		return "login";
				}
	}
	@Transactional
	public String changePassword(String number, String password,
			String newPassword_1, String newPassword_2,HttpSession session) {
		// TODO Auto-generated method stub
		//返回的结果
		String result;
		if(StringUtils.hasText(password)){
			if(newPassword_1 !=null && newPassword_2 !=null){
				if(newPassword_1.equals(newPassword_2)){
			if(loginDao.login(number, password)!= null){
				loginDao.changePassword(number,newPassword_1);
				result ="密码修改成功";
			}else{
				result="密码输入错误";
			}
			}else{
				result="两次密码输入不一致";
			}
			}else{
			   result="新密码不能为空";
			}
			}else{
				result="旧密码不能为空";
			}
		session.setAttribute("remind", result);
		return result;
	}
	@Transactional
	public String sMSVC(String code,String smg,String phoneNumber,String newPassword_1, String newPassword_2) {
		// TODO Auto-generated method stub
		//提示语句
		String result = smg;
			if(result.equals(code)){
				if(newPassword_1 !=null && newPassword_2 !=null){
					if(newPassword_1.equals(newPassword_2)){
					loginDao.forgotPassword(phoneNumber,newPassword_1);
					result ="密码修改成功";
					return result;
				}else{
					result="两次密码输入不一致";
					return result;
				}
				}else{
				   result="新密码不能为空";
				   return result;
				}
				}else{
					result ="验证码错误";
					return result;
				}
	}
	//送验证码
	public String sendMsg(String phoneNumber,HttpSession session) {
		System.out.println("进入sendMsg");
		// TODO Auto-generated method stub
		String code = null;
		String 	result;
		try {
			EmployeeManage em;
			em =loginDao.queryPhoneNumber(phoneNumber);
			//判断phoneNumber session是否存在，和是否和以前的手机号相等
			if(session.getAttribute("phoneNumber")==null && session.getAttribute("phoneNumber")!=phoneNumber){
				Long  a = data();
				String name = phoneNumber+"";
				session.setAttribute(name, a);
			}else{
				String name = phoneNumber+"";
				Long  a = data();
				Long  b = (Long) session.getAttribute(name);
				if(a-b<=60){
					return "发送请求过于平凡请耐心等待";
				}
			}
			//判断验证的手机号是否为店长绑定的手机
			if(em.getEmployeeType().getName().equals("店长") && em.getStatus().equals("在职")){
				code=aliyunMessageUtil.sendMsg(phoneNumber);
				session.setAttribute("phoneNumber", phoneNumber);
			}else{
				code = "你的手机号是否已绑定";
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "你的手机号是否已绑定";
		}
		if(code == "你的手机号是否已绑定"){//手机号未在数据库中绑定
			result = "你的手机号是否已绑定";
		code=",/.l;sdoasd./.,";//向code中输入乱码
		session.setAttribute("code",code);//向session中存入code
		return result;
		}else{
		session.setAttribute("code",code);
		session.setMaxInactiveInterval(60);
		return "发送成功";}
	}
	public String cancellation(HttpSession session) {
		// TODO Auto-generated method stub
		session.invalidate();  
		return "退出登录成功";
	}
	public String forgotPasswordResult(String result,HttpSession session) {
		// TODO Auto-generated method stub
		if(result=="密码修改成功"){
			cancellation(session);
			return "login";
		}else{
			return "redirect:/customer/";
		}
		
	}
	//获取当前时间	
	private Long data(){
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		Long  a = Long.parseLong(s.format(date));
		return a;
	}

}
