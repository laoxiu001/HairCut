package com.pidstudiodemo.service;

import javax.servlet.http.HttpSession;

/**
 * 登录Service
 * **/
public interface LoginService {
	//判断密码是否正确
	public String loginSelect(String number,String password);
	//判断是否登录成功
	public String  loginJudge(String result);
	//修改密码
	public String changePassword(String number,String password,String newPassword_1,String newPassword_2);
	//短信验证修改密码
	public String sMSVC(String code ,String smg,String phoneNumber,String newPassword_1,String newPassword_2);
	//发送短信
	public String sendMsg(String phoneNumber,HttpSession session);
	//注销登录
	public String cancellation(HttpSession session);
	//密码修改结果
	public String forgotPasswordResult(String result);
}
