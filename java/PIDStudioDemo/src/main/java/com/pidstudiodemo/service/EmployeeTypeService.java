package com.pidstudiodemo.service;

import java.util.List;

import com.pidstudiodemo.model.EmployeeType;
/**
 * 员工类型管理Service
 * **/
public interface EmployeeTypeService {
//查询
public List<EmployeeType> queryEmployeeType();
//修改
public String updateEmployeeType(EmployeeType employeeType);
//删除
public String deleteEmployeeType(int id);
//增加
public String insertEmployeeType(EmployeeType employeeType);
//
public int countEmployeeType();
}
