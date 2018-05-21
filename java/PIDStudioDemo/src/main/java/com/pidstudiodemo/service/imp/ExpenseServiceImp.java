package com.pidstudiodemo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
		int count = expenseDao.countExpense();
		//判断page的值
		if(page<(count/size)){page=page+0;}
			else{if(count%size==0){page = count/size;}
			else{page = count/size+1;}}
		Pageable pageable = new PageRequest(page,size,Sort.Direction.ASC,"id");
		return expenseDao.queryExpense(pageable);
	}

	public int countExpense() {
		// TODO Auto-generated method stub
		return expenseDao.countExpense();
	}

	public String insertExpense(Expense expense) {
		// TODO Auto-generated method stub
		expenseDao.inserExpense(expense);
		return null;
	}
}
