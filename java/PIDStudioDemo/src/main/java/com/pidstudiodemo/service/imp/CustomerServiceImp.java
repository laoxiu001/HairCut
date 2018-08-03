package com.pidstudiodemo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pidstudiodemo.dao.CustomerDao;
import com.pidstudiodemo.dao.PayListDao;
import com.pidstudiodemo.dao.RecordDao;
import com.pidstudiodemo.model.Customer;
import com.pidstudiodemo.model.PayList;
import com.pidstudiodemo.model.Record;
import com.pidstudiodemo.service.CustomerService;
@Service(value="customerService")
public class CustomerServiceImp implements CustomerService {
	@Autowired
	@Qualifier(value="customerDao")
	private CustomerDao customerDao;
	@Autowired
	@Qualifier(value="recordDao")
	private RecordDao recordDao;
	@Autowired
	@Qualifier(value="payListDao")
	private PayListDao payListDao;
	//分页查询
	@Transactional
	public Page<Customer> queryCustomer(int page, int size) {
		// TODO Auto-generated method stub
		try {
			int count;
			count = customerDao.countCustomer();
			//判断page的值
			if(page<0){page = 0;}
			if(page<=(count/size)&&count%size!=0){page=page+0;}
				else{if(count%size==0){page=count/size-1;}
				else{page = count/size;}}
			Pageable pageable = new PageRequest(page,size,Sort.Direction.ASC,"id");
			return customerDao.queryCustomer(pageable);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
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
	@Transactional
	public String updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
			if(customer.getId()!=0){
			Customer c1 = customerDao.quserId(customer.getId());
			String username = c1.getUserName();
			String username1 = customer.getUserName();
			Customer c = customerDao.quserCUserName(username);
			//查询出消费记录中对应的用户号账号并修改
			List<Record> listr = recordDao.queryUsername(username);
			for(Record r:listr){
				r.setUsername(username1);
				recordDao.insertRecord(r);
			}
			//查询出充值记录中对应的用户号账号并修改
			List<PayList> listp = payListDao.queryUsername(username);
			for(PayList pl:listp){
				pl.setUserName(username1);
				payListDao.insertPayList(pl);
			}
			customer.setBalance(c.getBalance());
			customer.setDiscount(c.getDiscount());
			customerDao.updateCustomer(customer);
			System.out.println("修改成功");
			return "修改成功";}else{	
				customer.setDiscount(1);
				return insertCustomer(customer);
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
	public int queryMaxPage(int page, int size) {
		// TODO Auto-generated method stub
		int count;
		count = customerDao.countCustomer();
		if(count%size==0){page=count/size-1;}
		else{page = count/size;}
		return page;
	}
	public List<Customer> queryConditions(String conditions) {
		// TODO Auto-generated method stub
		return customerDao.queryConditions(conditions);
	}
}
