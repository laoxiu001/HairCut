package com.pidstudiodemo.service.imp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pidstudiodemo.dao.CustomerDao;
import com.pidstudiodemo.model.Customer;
import com.pidstudiodemo.service.CustomerService;
@Service(value="customerService")
public class CustomerServiceImp implements CustomerService {
	@Autowired
	@Qualifier(value="customerDao")
	private CustomerDao customerDao;
	//分页查询
	@Transactional
	public Page<Customer> queryCustomer(int page, int size) {
		// TODO Auto-generated method stub
		int count;
		count = customerDao.countCustomer();
		//判断page的值
		if(page<(count/size)){page=page+0;}
			else{if(count%size==0){page = count/size;}
			else{page = count/size+1;}}
		Pageable pageable = new PageRequest(page,size,Sort.Direction.ASC,"id");
		return customerDao.queryCustomer(pageable);
	}
	//按userName查询
	public Customer quserCUserName(String userName) {
		// TODO Auto-generated method stub
		try {
			Customer customer=customerDao.quserCUserName(userName);
			return customer;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
	//修改用户信息
	public String updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		try {
			customerDao.updateCustomer(customer);
			return "修改成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "修改失败";
		}

	}
	//添加新的用户
	public String insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		try {
			customerDao.insertCustomer(customer);
			 return "添加成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "添加失败,请检查是否用户名已存在";
		}

	}
	//获取用户总数
	public int countCustomer() {
		// TODO Auto-generated method stub
		return customerDao.countCustomer();
	}
}
