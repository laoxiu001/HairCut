package com.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pidstudiodemo.PIDApplication;
import com.pidstudiodemo.common.jpa.EmployeeManageRepositoey;
import com.pidstudiodemo.common.jpa.RecordRepositoey;
import com.pidstudiodemo.service.SalaryService;
import com.pidstudiodemo.view.model.EmployeeManageSum;
import com.pidstudiodemo.view.model.IncomeAndSpending;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PIDApplication.class)
public class SalaryTest {
	@Autowired
	private EmployeeManageRepositoey cr;
	@Autowired
	private RecordRepositoey rr;
	@Autowired
	@Qualifier(value="salaryService")
	private SalaryService salaryService;
	@Test
	public void test(){
	List<EmployeeManageSum> listCS = new ArrayList<EmployeeManageSum>();
		//获取当前时间
	/*	Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("MM");
		long newFileName=Integer.parseInt(sdf.format(now));
		String s;
		System.out.println("时间"+newFileName);
		List<String> list = rr.queryNumber();
		for(String l:list){
			EmployeeManageSum cs = cr.querySalary(l,(int)newFileName);
		listCS.add(cs);
		}
		for(EmployeeManageSum cs : listCS){
			s=sdf.format(cs.getRecord().getDate());
			System.out.println(s);
		}*/
		//salaryService.insertSalary();
		//查询年份
		List<Integer> list = salaryService.querYear();
		for(Integer i : list){
			System.out.println("年份"+i);
			List<IncomeAndSpending> list1 = salaryService.queryMonthIncome(i);
			List<IncomeAndSpending> list2 = salaryService.queryMonthSpending(i);
			List<IncomeAndSpending> list3 = salaryService.queryMinToMaxSpending(i,4,7);
			List<IncomeAndSpending> list4 = salaryService.querySINameIncome(i, "剪头");
			List<IncomeAndSpending> list5 = salaryService.queryMinToMaxSINameIncome(i, "剪头", 4, 5);
			List<IncomeAndSpending> list6 = salaryService.queryNumberIncome(i, "0001");
			List<IncomeAndSpending> list7 = salaryService.queryNumberIncomeTo(i, "0001", 4, 5);
			for(IncomeAndSpending ir : list1){
				System.out.println("收入资金 "+ir.getSum()+" 月份" + ir.getMonth());
				for(IncomeAndSpending ir1 : list2)
				System.out.println("支出资金 "+ir1.getSum()+" 月份" + ir1.getMonth());
			}
				for(IncomeAndSpending ir2 : list3){
					System.out.println("4-7月"+"支出资金"+ir2.getSum()+" 月份" + ir2.getMonth());
				}
				for(IncomeAndSpending ir2 : list4){
					System.out.println("剪头项目 "+"收入资金"+ir2.getSum()+" 月份" + ir2.getMonth()+" 使用的次数"+ir2.getCount());
				}
				for(IncomeAndSpending ir2 : list5){
					System.out.println("4-5月 剪头项目 "+"收入资金"+ir2.getSum()+" 月份" + ir2.getMonth()+" 使用的次数"+ir2.getCount());
				}
				for(IncomeAndSpending ir2 : list6){
					System.out.println("0001员工 "+"工资"+ir2.getSum()+" 月份" + ir2.getMonth()+" 使用的次数"+ir2.getCount());
				}
				for(IncomeAndSpending ir2 : list7){
					System.out.println("4-5月 0001员工 "+"工资"+ir2.getSum()+" 月份" + ir2.getMonth()+" 使用的次数"+ir2.getCount());
				}
		}
	}
}
