package com.pidstudiodemo.dao.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.common.jpa.CustomerRepositoey;
import com.pidstudiodemo.dao.CustomerDao;
import com.pidstudiodemo.model.Customer;
import com.pidstudiodemo.model.EmployeeManage;
@Repository(value="customerDao")
public class CustomerDaoImp implements CustomerDao {
	@Autowired
	private CustomerRepositoey customerRepositoey;
	//分页查询
	public Page<Customer> queryCustomer(Pageable pageable) {
		// TODO Auto-generated method stub
		return customerRepositoey.findAll(pageable);
	}
	//条件查询
	public Customer quserCUserName(String userName) {
		// TODO Auto-generated method stub
		String phone = userName;
		return customerRepositoey.findByUserNameOrPhone(userName,phone);
	}
	//修改用户记录
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepositoey.saveAndFlush(customer);
	}
	//新增用户
	public void insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepositoey.save(customer);
	}
	public int countCustomer() {
		// TODO Auto-generated method stub
		return (int) customerRepositoey.count();
	}
	public List<Customer> queryConditions(String conditions) {
		// TODO Auto-generated method stub
		return customerRepositoey.queryConditions(conditions);
	}
	public Customer quserId(int id) {
		// TODO Auto-generated method stub
		return customerRepositoey.findById(id);
	}

}
