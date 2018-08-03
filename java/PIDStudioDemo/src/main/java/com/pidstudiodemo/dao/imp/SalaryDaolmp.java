package com.pidstudiodemo.dao.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.common.jpa.EmployeeManageRepositoey;
import com.pidstudiodemo.common.jpa.SalaryRepositoey;
import com.pidstudiodemo.dao.SalaryDao;
import com.pidstudiodemo.model.Salary;
import com.pidstudiodemo.view.model.EmployeeManageSum;
import com.pidstudiodemo.view.model.IncomeAndSpending;
@Repository(value="salaryDao")
public class SalaryDaolmp implements SalaryDao {
	@Autowired
	private EmployeeManageRepositoey employeeManageRepositoey;
	@Autowired
	private SalaryRepositoey salaryRepositoey;
	public EmployeeManageSum querySalary(String number,int dateMonth) {
		// TODO Auto-generated method stub
		try{
		return employeeManageRepositoey.querySalary(number,dateMonth);}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public void insertSalary(Salary s) {
		// TODO Auto-generated method stub
		salaryRepositoey.save(s);
	}

	public List<EmployeeManageSum> queryConditions(String conditions) {
		// TODO Auto-generated method stub
		return salaryRepositoey.queryConditions(conditions);
	}

	public List<Integer> querYear() {
		// TODO Auto-generated method stub
		return salaryRepositoey.querYear() ;
	}

	public List<IncomeAndSpending> queryMonthIncome(int year) {
		// TODO Auto-generated method stub
		return salaryRepositoey.queryMonthIncome(year);
	}

	public List<IncomeAndSpending> queryMonthSpending(int year) {
		// TODO Auto-generated method stub
		return salaryRepositoey.queryMonthSpending(year);
	}
	//	几月到几月收入
	public List<IncomeAndSpending> queryMinToMaxIncome(int year, int minMonth, int maxMonth) {
		// TODO Auto-generated method stub
		return salaryRepositoey.queryMinToMaxIncome(year,minMonth,maxMonth);
	}

	public List<IncomeAndSpending> queryMinToMaxSpending(int year, int minMonth, int maxMonth) {
		// TODO Auto-generated method stub
		return salaryRepositoey.queryMinToMaxSpending(year,minMonth,maxMonth);
	}
	//某一服务项目每月创造的业绩和工资变化
	public List<IncomeAndSpending> querySINameIncome(int year, String name) {
		// TODO Auto-generated method stub
		return salaryRepositoey.querySINameIncome(year,name);
	}

	public List<IncomeAndSpending> queryMinToMaxSINameIncome(int year, String name, int minMonth, int maxMonth) {
		// TODO Auto-generated method stub
		return salaryRepositoey.queryMinToMaxSINameIncome(year,name,minMonth,maxMonth);
	}

	public List<IncomeAndSpending> queryNumberIncome(int year, String number) {
		// TODO Auto-generated method stub
		return salaryRepositoey.queryNumberIncome(year,number);
	}

	public List<IncomeAndSpending> queryNumberIncomeTo(int year, String number, int minMonth, int maxMonth) {
		// TODO Auto-generated method stub
		return salaryRepositoey.queryNumberIncomeTo(year,number,minMonth,maxMonth);
	}
	
}
