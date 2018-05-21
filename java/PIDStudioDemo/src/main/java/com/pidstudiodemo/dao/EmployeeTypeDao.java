package com.pidstudiodemo.dao;


import java.util.List;

import com.pidstudiodemo.model.EmployeeType;
/**
 * 员工类型管理管理Dao
 * **/
public interface EmployeeTypeDao {
	//查询
	public List<EmployeeType> queryEmployeeType();
	//修改
	public void updateEmployeeType(EmployeeType employeeType);
	//删除
	public void deleteEmployeeType(int id);
	//增加
	public void insertEmployeeType(EmployeeType employeeType);
	//员工总类型
	public int countEmployeeType();
}
