package com.pidstudiodemo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pidstudiodemo.model.Customer;

public interface CustomerDao {
	//分页查询
	public Page<Customer> queryCustomer(Pageable pageable);
	//userName查询
	public Customer quserCUserName(String userName);
	//修改表
	public void updateCustomer(Customer customer);
	//添加用户
	public void insertCustomer(Customer customer);
	//查询用户总数
	public int countCustomer();

}
