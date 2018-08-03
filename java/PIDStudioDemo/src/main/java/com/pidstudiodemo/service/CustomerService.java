package com.pidstudiodemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pidstudiodemo.model.Customer;
import com.pidstudiodemo.model.EmployeeManage;

public interface CustomerService {
	//分页查询
	public Page<Customer> queryCustomer(int page, int size);
	//按userName查询
	public Customer quserCUserName(String userName);
	//修改用户信息
	public String updateCustomer(Customer customer);
	//添加新的用户
	public String insertCustomer(Customer customer);
	//获取用户总数
	public int countCustomer();
	//返回最大页数
	public int queryMaxPage(int page, int size);
	//模糊查询
	public List<Customer> queryConditions(String conditions);

}
