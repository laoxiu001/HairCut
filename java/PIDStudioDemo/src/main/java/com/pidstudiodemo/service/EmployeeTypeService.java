package com.pidstudiodemo.service;

import java.util.List;

import com.pidstudiodemo.model.EmployeeType;
/**
 * 员工类型管理Service
 * **/
public interface EmployeeTypeService {
//总体查询
public List<EmployeeType> queryEmployeeType(int i);
//分页查询
public List<EmployeeType> queryEmployeeTypeF(int page, int size, int i);
//修改
public String updateEmployeeType(EmployeeType employeeType);
//删除
public String deleteEmployeeType(int id);
//增加
public String insertEmployeeType(EmployeeType employeeType);
//
public int countEmployeeType();
//查询最大页数
public int queryMaxPage(int page, int size);
//多字段模糊查询
public List<EmployeeType> queryConditions(String conditions);
}
