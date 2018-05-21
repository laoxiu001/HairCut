package com.pidstudiodemo.dao;

import com.pidstudiodemo.model.EmployeeManage;

/**
 * 员工类型管理Dao
 * **/

public interface LoginDao {
	//验证密码
	public EmployeeManage login(String number,String password);
	//修改密码
	public int changePassword(String phoneNumber, String password);
	//查询工号
	public EmployeeManage queryNumber(String phoneNumber);
}
