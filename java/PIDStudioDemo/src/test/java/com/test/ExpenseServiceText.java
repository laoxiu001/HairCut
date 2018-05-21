package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pidstudiodemo.PIDApplication;
import com.pidstudiodemo.model.Expense;
import com.pidstudiodemo.service.ExpenseService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PIDApplication.class)
public class ExpenseServiceText {
	@Autowired
	@Qualifier(value="expenseService")
	private ExpenseService expenseService;
	@Test
	public void text(){
		Expense expense = new Expense();
		expense.setName("嫖娼");
		expense.setRemark("老胡和老春去嫖娼");
		expenseService.insertExpense(expense);
	}
}
