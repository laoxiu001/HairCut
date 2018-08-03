package com.pidstudiodemo.dao;

import com.pidstudiodemo.model.EmployeeManage;

/**
 * 员工类型管理Dao
 * **/

public interface LoginDao {
	//验证密码
	public EmployeeManage login(String number,String password);
	//修改密码
	public int changePassword(String number, String password);
	//根据手机号查询
	public EmployeeManage queryPhoneNumber(String phoneNumber);
	//忘记密码
	public void forgotPassword(String phoneNumber, String newPassword_1);
}
