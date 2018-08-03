package com.pidstudiodemo.common.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pidstudiodemo.model.Salary;
import com.pidstudiodemo.view.model.EmployeeManageSum;
import com.pidstudiodemo.view.model.IncomeAndSpending;

public interface SalaryRepositoey extends JpaRepository<Salary, Integer> {
//	模糊查询
	@Query("SELECT NEW com.pidstudiodemo.view.model.EmployeeManageSum(sr.employeeManage,"
			+ "sr.employeeManage.employeeType,sr,sum(sr.payavle),count(sr.number),"
			+ "(sum(sr.payavle)*sr.employeeManage.employeeType.commissionRate+"
			+ "sr.employeeManage.employeeType.basicSalary)) FROM  Record sr WHERE concat(IFNULL(sr.employeeManage.name,''),IFNULL(sr.number,'')) like %:conditions% group by MONTH(sr.date),sr.number")
	List<EmployeeManageSum> queryConditions(@Param("conditions") String conditions);
	//查询年份
	@Query("SELECT YEAR(r.date) FROM Record r group by YEAR(r.date)")
	List<Integer> querYear();
	//按月份查询收入
	@Query("SELECT NEW com.pidstudiodemo.view.model.IncomeAndSpending(sum(r.payavle),month(r.date)) FROM Record r where year(r.date)=:year group by Month(r.date)")
	List<IncomeAndSpending> queryMonthIncome(@Param("year")int year);
	//按月份查询支出
	@Query("SELECT NEW com.pidstudiodemo.view.model.IncomeAndSpending(sum(e.money),month(e.date)) FROM Expense e where year(e.date)=:year group by Month(e.date)")
	List<IncomeAndSpending> queryMonthSpending(@Param("year")int year);
	//几月到几月的收入
	@Query("SELECT NEW com.pidstudiodemo.view.model.IncomeAndSpending(sum(r.payavle),month(r.date)) FROM Record r Where year(r.date)=:year and Month(r.date)>=:minMonth and  Month(r.date)<=:maxMonth group by Month(r.date)")
	List<IncomeAndSpending> queryMinToMaxIncome(@Param("year")int year, @Param("minMonth")int minMonth, @Param("maxMonth") int maxMonth);
	//	几月到几月支出
	@Query("SELECT NEW com.pidstudiodemo.view.model.IncomeAndSpending(sum(e.money),month(e.date)) FROM Expense e Where year(e.date)=:year and Month(e.date)>=:minMonth and  Month(e.date)<=:maxMonth group by Month(e.date)")
	List<IncomeAndSpending> queryMinToMaxSpending(@Param("year")int year, @Param("minMonth")int minMonth, @Param("maxMonth") int maxMonth);
	//某一服务项目每月创造的业绩和工资变化
	@Query("SELECT NEW com.pidstudiodemo.view.model.IncomeAndSpending(sum(r.payavle),month(r.date)) FROM Record r Where year(r.date)=:year and r.serviceItem.name=:name group by Month(r.date)")
	List<IncomeAndSpending> querySINameIncome(@Param("year")int year,@Param("name") String name);
//	几月到几月支出某一服务项目每月创造的业绩和工资变化
	@Query("SELECT NEW com.pidstudiodemo.view.model.IncomeAndSpending(sum(r.payavle),month(r.date)) FROM Record r Where year(r.date)=:year and Month(r.date)>=:minMonth and  Month(r.date)<=:maxMonth and r.serviceItem.name=:name group by Month(r.date)")
	List<IncomeAndSpending> queryMinToMaxSINameIncome(@Param("year")int year, @Param("name")String name,@Param("minMonth") int minMonth, @Param("maxMonth")int maxMonth);
	//某一员工每月创造的业绩和工资变化
	@Query("SELECT NEW com.pidstudiodemo.view.model.IncomeAndSpending((sum(r.payavle)*r.employeeManage.employeeType.commissionRate+"
			+ "r.employeeManage.employeeType.basicSalary),month(r.date),sum(r.payavle)) FROM Record r Where year(r.date)=:year and r.number=:number group by Month(r.date)")
	List<IncomeAndSpending> queryNumberIncome(@Param("year")int year, @Param("number")String number);
	//
	@Query("SELECT NEW com.pidstudiodemo.view.model.IncomeAndSpending((sum(r.payavle)*r.employeeManage.employeeType.commissionRate+"
			+ "r.employeeManage.employeeType.basicSalary),month(r.date),sum(r.payavle)) FROM Record r Where year(r.date)=:year and r.number=:number and  Month(r.date)>=:minMonth and  Month(r.date)<=:maxMonth group by Month(r.date)")
	List<IncomeAndSpending> queryNumberIncomeTo(@Param("year")int year,@Param("number") String number,@Param("minMonth") int minMonth,@Param("maxMonth") int maxMonth);
	

}
