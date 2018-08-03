package com.pidstudiodemo.service.imp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pidstudiodemo.dao.ExpenseDao;
import com.pidstudiodemo.model.Expense;
import com.pidstudiodemo.service.ExpenseService;
@Service(value="expenseService")
public class ExpenseServiceImp implements ExpenseService {
	@Autowired
	@Qualifier(value="expenseDao")
	private ExpenseDao expenseDao;
	public Page<Expense> queryExpense(int page, int size) {
		// TODO Auto-generated method stubint count;
		try{int count = expenseDao.countExpense();
		//判断page的值
		if(page<=(count/size)){page=page+0;}
			else{if(count%size==0){page = count/size;}
			else{page = count/size+1;}}
		Pageable pageable = new PageRequest(page,size,Sort.Direction.DESC,"id");
		return expenseDao.queryExpense(pageable);}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public int countExpense() {
		// TODO Auto-generated method stub
		return expenseDao.countExpense();
	}

	public String insertExpense(Expense expense) {
		// TODO Auto-generated method stub
		try{
		expenseDao.inserExpense(expense);
		return "操作成功";}catch (Exception e) {
			// TODO: handle exception
			return "操作失败";
		}
	}
	public int queryMaxPage(int page, int size) {
		// TODO Auto-generated method stub
		int count;
		count = expenseDao.countExpense();
		if(count%size==0){page=count/size-1;}
		else{page = count/size;}
		return page;
	}

	public double querySumMoney() {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("#0.00");
		return Double.valueOf(df.format(expenseDao.querySumMoney()));
	}

	public List<Expense> queryConditions(String conditions) {
		// TODO Auto-generated method stub
		return expenseDao.queryConditions(conditions);
	}
}
