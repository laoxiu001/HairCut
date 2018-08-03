package com.pidstudiodemo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pidstudiodemo.model.Customer;
import com.pidstudiodemo.model.EmployeeManage;

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
	//模糊查询
	public List<Customer> queryConditions(String conditions);
	//id查询用户
	public Customer quserId(int id);

}
