package com.pidstudiodemo.service.imp;

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
			return "redirect:/employeeType/";
		}else{
		return "login";
				}
	}
	@Transactional
	public String changePassword(String number, String password,
			String newPassword_1, String newPassword_2) {
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
		return result;
	}
	public String sMSVC(String code,String smg,String phoneNumber,String newPassword_1, String newPassword_2) {
		// TODO Auto-generated method stub
		//提示语句
		String result = smg;
			if(result.equals(code)){
				if(newPassword_1 !=null && newPassword_2 !=null){
					if(newPassword_1.equals(newPassword_2)){
					loginDao.changePassword(phoneNumber,newPassword_1);
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
		// TODO Auto-generated method stub
		String code = null;
		String 	result;
		try {
			EmployeeManage em;
			em =loginDao.queryNumber(phoneNumber);
			//判断验证的手机号是否为店长绑定的手机
			if(em.getEmployeeType().getId()==5){
				code=aliyunMessageUtil.sendMsg(phoneNumber);	
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
	public String forgotPasswordResult(String result) {
		// TODO Auto-generated method stub
		if(result=="密码修改成功"){
			return "redirect:/login/loginAction";
		}else{
			return "forget";
		}
		
	}
		

}
