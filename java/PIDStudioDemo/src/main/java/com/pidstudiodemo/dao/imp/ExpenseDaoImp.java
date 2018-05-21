package com.pidstudiodemo.dao.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.common.jpa.ExpenseRepositoey;
import com.pidstudiodemo.dao.ExpenseDao;
import com.pidstudiodemo.model.Expense;
@Repository(value="expenseDao")
public class ExpenseDaoImp implements ExpenseDao {
	@Autowired
	private ExpenseRepositoey expenseRepositoey;

	public int countExpense() {
		// TODO Auto-generated method stub
		return (int) expenseRepositoey.count();
	}

	public Page<Expense> queryExpense(Pageable pageable) {
		// TODO Auto-generated method stub
		return expenseRepositoey.findAll(pageable);
	}

	public void inserExpense(Expense expense) {
		// TODO Auto-generated method stub
		expenseRepositoey.save(expense);
	}
}
