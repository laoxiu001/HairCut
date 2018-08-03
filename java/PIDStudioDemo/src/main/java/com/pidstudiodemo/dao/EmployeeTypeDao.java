package com.pidstudiodemo.dao;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pidstudiodemo.model.EmployeeType;
/**
 * 员工类型管理管理Dao
 * **/
public interface EmployeeTypeDao {
	//总体
	public List<EmployeeType> queryEmployeeType(int status);
	//分页查询
	public List<EmployeeType> queryEmployeeType(int status, Pageable pageable);
	//修改
	public void updateEmployeeType(EmployeeType employeeType);
	//增加
	public void insertEmployeeType(EmployeeType employeeType);
	//员工总类型
	public int countEmployeeType(int status);
	//根据id查询出员工类型信息
	public EmployeeType queryEmployeeTypeId(int id);
	//多字段模糊查询
	public List<EmployeeType> queryConditions(String conditions);
}
