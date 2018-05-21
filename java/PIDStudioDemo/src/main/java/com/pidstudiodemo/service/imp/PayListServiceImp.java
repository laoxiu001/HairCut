package com.pidstudiodemo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pidstudiodemo.dao.PayListDao;
import com.pidstudiodemo.model.Customer;
import com.pidstudiodemo.model.PayList;
import com.pidstudiodemo.service.CustomerService;
import com.pidstudiodemo.service.PayListService;
@Service(value="payListService")
public class PayListServiceImp implements PayListService {
	@Autowired
	@Qualifier(value="payListDao")
	private PayListDao payListDao;
	
	@Autowired
	@Qualifier(value="customerService")
	private CustomerService cS;//用户管理service
	//查询充值记录
	@Transactional
	public Page<PayList> queryPayList(int page, int size) {
		// TODO Auto-generated method stub
		int count;
		count = payListDao.PayList();
		//判断page的值
		if(page<(count/size)){page=page+0;}
			else{if(count%size==0){page = count/size;}
			else{page = count/size+1;}}
		Pageable pageable = new  PageRequest(page, size,Sort.Direction.DESC, "id");
		payListDao.queryPayList(pageable);
		return payListDao.queryPayList(pageable);
	}
	//充值
	@Transactional
	public String  insertPayList(PayList payList) {
		// TODO Auto-generated method stub
		double gold = payList.getGold();//获取充值金额
		double balance;
		double discount;
		Customer customer = new Customer();
		String userName = payList.getUserName();//获取充值的用户名
		System.out.println(userName);
		customer = cS.quserCUserName(userName);//查询用户信息
		System.out.println(customer);
		if(customer!= null){
		balance = customer.getBalance();//获取余额
		balance = gold+balance;//总的余额
		//判断折扣系数存储到customer对象中
		if(balance<=200.0){
			discount=0.95;
		}else{
			if(balance<=400.0){
				discount=0.85;
			}else{
				if(balance<=600.0){
					discount=0.75;
				}else{
					if(balance<=1000){
						discount=0.65;
					}else{
						discount=0.5;
					}
				}
			}
		}
		customer.setBalance(balance);//将充值后的余额写入customer对象
		System.out.println(discount);
		customer.setDiscount(discount);
		cS.updateCustomer(customer);//修改用户表
		payListDao.insertPayList(payList);//保存消费记录
		return "充值成功";
		}else{
			return "没有该用户";
		}	
	}
	public int countPayList() {
		// TODO Auto-generated method stub
		return payListDao.PayList();
	}

}
