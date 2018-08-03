package com.pidstudiodemo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pidstudiodemo.model.EmployeeManage;
/**
 * 员工管理Service
 * **/

public interface EmployeeManageServic {
	//条件分页查询
	public List<EmployeeManage> queryEmployeeManage(int page,int size,String status);
	//个人信息查询
	public List<EmployeeManage> queryDetailed(int id);
	//上传文件
	public String uploadFile(MultipartFile file);
	//添加员工
	public String insertEmployee(EmployeeManage em);
	//修改员工信息
	public String updateEmployee(EmployeeManage em);
	//获取员工总数
	public int countEmployee();
	//根据员工编号查询
	public EmployeeManage queryNumber(String number);
	//最大页数
	public int queryMaxPage(int page, int size, String string);
	//多字段模糊查询
	public List<EmployeeManage> queryConditions(String conditions, String status);
	//查询员工类型对应的员工
	public List<EmployeeManage> queryEmployeeId(int string);
}
