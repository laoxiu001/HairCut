package com.pidstudiodemo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pidstudiodemo.model.Expense;

public interface ExpenseDao {
	//查询消费记录总数
	public int countExpense();
	//分页查询消费记录
	public Page<Expense> queryExpense(Pageable pageable);
	//添加支出记录
	public void inserExpense(Expense expense);
	//查询总的支出
	public double querySumMoney();
	//多字段模糊查询
	public List<Expense> queryConditions(String conditions);

}
