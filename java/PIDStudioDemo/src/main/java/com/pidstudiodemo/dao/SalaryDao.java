package com.pidstudiodemo.dao;

import java.util.List;

import com.pidstudiodemo.model.Salary;
import com.pidstudiodemo.view.model.EmployeeManageSum;
import com.pidstudiodemo.view.model.IncomeAndSpending;

public interface SalaryDao {
	// 根据员工和月份存储
	public EmployeeManageSum querySalary(String number, int dateMonth);

	// 存储每个月的工资
	public void insertSalary(Salary s);

	// 模糊查询
	public List<EmployeeManageSum> queryConditions(String conditions);

	// 有哪几年的数据
	public List<Integer> querYear();

	// 按月份查询收入
	public List<IncomeAndSpending> queryMonthIncome(int year);

	// 按月份查询支出
	public List<IncomeAndSpending> queryMonthSpending(int year);

	// 几月到几月查询收入
	public List<IncomeAndSpending> queryMinToMaxIncome(int year, int minMonth, int maxMonth);

	// 几月到几月查询支出
	public List<IncomeAndSpending> queryMinToMaxSpending(int year, int minMonth, int maxMonth);

	// 某一服务项目每月收入
	public List<IncomeAndSpending> querySINameIncome(int year, String name);

	// 某一服务项目几月到几月收入
	public List<IncomeAndSpending> queryMinToMaxSINameIncome(int year, String name, int minMonth, int maxMonth);

	// 每一月份的员工业绩和工资变化
	public List<IncomeAndSpending> queryNumberIncome(int year, String number);

	// 几月到几月的员工业绩和工资变化
	public List<IncomeAndSpending> queryNumberIncomeTo(int year, String number, int minMonth, int maxMonth);

}
