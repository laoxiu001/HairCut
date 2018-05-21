package com.pidstudiodemo.service;

import org.springframework.data.domain.Page;

import com.pidstudiodemo.model.Customer;

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

}
