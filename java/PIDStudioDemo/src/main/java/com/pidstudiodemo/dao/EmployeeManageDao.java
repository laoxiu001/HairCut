package com.pidstudiodemo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pidstudiodemo.model.EmployeeManage;
/**
 * 员工管理Dao
 * **/
public interface EmployeeManageDao {
	//分页查询
	public List<EmployeeManage> queryEmployeeManage(String status,Pageable pageable);
	//员工详细信息
	public List<EmployeeManage> queryDetailed(int id);
	//添加员工
	public void insertEmployee(EmployeeManage em);
	//修改员工
	public void updateEmployee(EmployeeManage em);
	//员工总数
	public int countEmployee();
	//员工编号查询当条记录
	public EmployeeManage queryNumber(String number);
	//员工分开查询
	public int countEmployee(String stasus);
	//多字段模糊查询
	public List<EmployeeManage> queryConditions(String conditions, String status);
	//根据员工类型查询员工
	public List<EmployeeManage> queryEmployeeId(int id);

}
