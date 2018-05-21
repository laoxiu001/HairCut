package com.pidstudiodemo.service;

import org.springframework.data.domain.Page;

import com.pidstudiodemo.model.Expense;

public interface ExpenseService {
	//查询费用
	public Page<Expense> queryExpense(int page, int size);
	//查询费用总条数
	public int countExpense();
	//添加支出记录
	public String insertExpense(Expense expense);

}
