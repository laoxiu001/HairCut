package com.pidstudiodemo.service.imp;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pidstudiodemo.dao.ExpenseDao;
import com.pidstudiodemo.dao.RecordDao;
import com.pidstudiodemo.dao.SalaryDao;
import com.pidstudiodemo.model.Expense;
import com.pidstudiodemo.model.Record;
import com.pidstudiodemo.model.Salary;
import com.pidstudiodemo.service.SalaryService;
import com.pidstudiodemo.view.model.EmployeeManageSum;
import com.pidstudiodemo.view.model.IncomeAndSpending;

@Service(value = "salaryService")
public class SalaryServiceImp implements SalaryService {
	@Autowired
	@Qualifier(value = "salaryDao")
	private SalaryDao salaryDao;
	@Autowired
	@Qualifier(value = "recordDao")
	private RecordDao recordDao;
	@Autowired
	@Qualifier(value = "expenseDao")
	private ExpenseDao expenseDao;
	@Transactional
	public List<EmployeeManageSum> querySalary(int page, int size,int month,HttpSession session) {
		// TODO Auto-generated method stub
		try {
			DecimalFormat df = new DecimalFormat("#0.00");
			int count;
			count = recordDao.countNumber();
			// 判断page的值
			if (page <= (count / size)) {
				page = page + 0;
			} else {
				if (count % size == 0) {
					page = count / size;
				} else {
					page = count / size + 1;
				}
			}
			Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "id");
			List<EmployeeManageSum> listCS = new ArrayList<EmployeeManageSum>();
			int dateMonth;
			if(month==0){
			dateMonth = nowMonth();}else{
				dateMonth = month;
			}// 将月份字段转化为类型}
			session.setAttribute("dateMonth", dateMonth);
			List<String> list = recordDao.queryNumber(pageable, dateMonth);
			for (String l : list) {
				EmployeeManageSum cs = salaryDao.querySalary(l, dateMonth);// 获取当月的员工工资信息
				cs.setSumPayavle(Double.valueOf(df.format(cs.getSumPayavle())));
				cs.setSumSalary(Double.valueOf(df.format(cs.getSumSalary())));
				listCS.add(cs);// 将员工工资信息存入List<EmployeeManageSum>集合
			}
			return listCS;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public List<Record> querySalaryDetails(String number) {
		// TODO Auto-generated method stub
		try {
			int dateMonth;
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM");// 获取月份字段
			dateMonth = Integer.parseInt(sdf.format(date)) - 1;// 将月份字段转化为类型
			return recordDao.querySalaryDetails(number, dateMonth);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public List<Record> querySalaryDetails(int page, int size, String number,int month) {
		// TODO Auto-generated method stub
		try {
			int count;
			int dateMonth;
			if(month==0){
			dateMonth = nowMonth();}else{// 将月份字段转化为类型
				dateMonth = month;
			}
			count = recordDao.countSalaryDetails(number,dateMonth);
			// 判断page的值
			if (page < 0) {
				page = 0;
			}
			if (page <= (count / size) && count % size != 0) {
				page = page + 0;
			} else {
				if (count % size == 0) {
					page = count / size - 1;
				} else {
					page = count / size;
				}
			}
			Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
			return recordDao.querySalaryDetails(number, pageable,dateMonth);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Transactional
	public void insertSalary() {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("#0.00");
		List<String> list = recordDao.queryNumber();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");// 获取月份字段
		SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy");// 获取年份字段
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");// 将String类型转换为时间类型类型
		int dateMonth = Integer.parseInt(sdf.format(date)) - 1;// 将月份字段转化为类型
		int onDateMonth;// 获取是哪一个月的工资
		Date date_1;
		String dateYears = sdf_1.format(date);// 存储年份
		if (dateMonth != 1) {
			onDateMonth = dateMonth - 1;
		} else {
			onDateMonth = 12;
		}
		String nowDate = dateYears + "-" + onDateMonth;// 时间
		try {
			date_1 = dateFormat.parse(nowDate);// 将Sring类型转换为Date类型
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			date_1 = null;
		}
		//总工资
		double sum=0;
		for (String l : list) {
			EmployeeManageSum cs =  salaryDao.querySalary(l, dateMonth);// 获取当月的员工工资信息
			System.out.println("进入1");
			if (cs != null) {
				Salary s = new Salary();
				s.setName(cs.getEmployeeManage().getName());
				s.setDate(date_1);
				s.setCount((int) cs.getCount());
				s.setNumber(cs.getEmployeeManage().getNumber());
				s.setWeightingFactor(cs.getEmployeeType().getWeightingFactor());
				s.setSalary(Double.valueOf(df.format(cs.getSumSalary())));
				s.setBasicSalary(cs.getEmployeeType().getBasicSalary());
				s.setCommissionRate(cs.getEmployeeType().getCommissionRate());
				sum = sum+Double.valueOf(df.format(cs.getSumSalary()));
				salaryDao.insertSalary(s);
			}
		}
		Expense e = new Expense();
		e.setDate(date);
		e.setMoney(Double.valueOf(df.format(sum)));
		e.setName("员工工资");
		e.setRemark("员工工资支出");
		expenseDao.inserExpense(e);
		
	}

	public int queryMaxPage(int page, int size) {
		// TODO Auto-generated method stub
		int count;
		count = recordDao.countNumber();
		if (count % size == 0) {
			page = count / size - 1;
		} else {
			page = count / size;
		}
		return page;
	}

	public int queryMaxPage(int page, int size, String number,int month) {
		// TODO Auto-generated method stub
		int count;
		int dateMonth;
		if(month==0){
		dateMonth = nowMonth();}// 将月份字段转化为类型
		else{
			dateMonth = month;
		}
		count = recordDao.countSalaryDetails(number,dateMonth);
		if (count % size == 0) {
			page = count / size - 1;
		} else {
			page = count / size;
		}
		return page;
	}
	//获取当前月份
	public int nowMonth(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");// 获取月份字段
		return Integer.parseInt(sdf.format(date))-1;
		
	}
	//获取当前年份
		public int nowYear(){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");// 获取月份字段
			return Integer.parseInt(sdf.format(date));
			
		}
//模糊查询
	public List<EmployeeManageSum> queryConditions(String conditions) {
		// TODO Auto-generated method stub
		return salaryDao.queryConditions(conditions);
	}
// 有那几个月份的数据
	public List<Integer> querYear() {
		// TODO Auto-generated method stub
		return salaryDao.querYear();
	}
	//	支出
	public List<IncomeAndSpending> queryMonthIncome(int year) {
		// TODO Auto-generated method stub
		if(year==0){
			year = nowYear();
		}
		return salaryDao.queryMonthIncome(year);
	}
//	收入
	public List<IncomeAndSpending> queryMonthSpending(int year) {
		// TODO Auto-generated method stub
		if(year==0){
			year = nowYear();
		}
		return salaryDao.queryMonthSpending(year);
	}

	public List<IncomeAndSpending> queryMinToMaxIncome(int year, int minMonth, int maxMonth) {
		// TODO Auto-generated method stub
		if(year==0){
			year = nowYear();
		}
		if(minMonth==0 || maxMonth == 0){
			minMonth = 1;
			maxMonth = nowMonth()+1;
		}
		return salaryDao.queryMinToMaxIncome(year,minMonth,maxMonth);
	}

	public List<IncomeAndSpending> queryMinToMaxSpending(int year, int minMonth, int maxMonth) {
		// TODO Auto-generated method stub
		if(year==0){
			year = nowYear();
		}
		if(minMonth==0 || maxMonth == 0){
			minMonth = 1;
			maxMonth = nowMonth()+1;
		}
		return salaryDao.queryMinToMaxSpending(year,minMonth,maxMonth);
	}

	public List<IncomeAndSpending> querySINameIncome(int year, String name) {
		// TODO Auto-generated method stub
		if(year==0){
			year = nowYear();
		}
		if(name.equals("") || name.equals(null)){
			List<String> lst = recordDao.queryNumber();
			for(String l : lst){
				name = l;
				break;
			}
		}
		return salaryDao.querySINameIncome(year,name);
	}

	public List<IncomeAndSpending> queryMinToMaxSINameIncome(int year, String name, int minMonth, int maxMonth) {
		// TODO Auto-generated method stub
		if(year==0){
			year = nowYear();
		}
		if(name.equals("") || name.equals(null)){
			List<String> list = recordDao.queryService();
				for(String l : list){
					name = l;
				}
		}
		if(minMonth==0 || maxMonth == 0){
			minMonth = 1;
			maxMonth = nowMonth()+1;
		}
		return salaryDao.queryMinToMaxSINameIncome(year,name,minMonth,maxMonth);
	}

	public List<IncomeAndSpending> queryNumberIncome(int year, String number) {
		// TODO Auto-generated method stub
		if(year==0){
			year = nowYear();
		}
		if(number.equals("") || number.equals(null)){
			number = "0001";
		}
		return salaryDao.queryNumberIncome(year,number);
	}

	public List<IncomeAndSpending> queryNumberIncomeTo(int year, String number, int minMonth, int maxMonth) {
		// TODO Auto-generated method stub
		if(year==0){
			year = nowYear();
		}
		if(number.equals("") || number.equals(null)){
			List<String> lst = recordDao.queryNumber();
			for(String l : lst){
				number = l;
				break;
			}
		}
		if(minMonth==0 || maxMonth == 0){
			minMonth = 1;
			maxMonth = nowMonth()+1;
		}
		return salaryDao.queryNumberIncomeTo(year,number,minMonth, maxMonth);
	}
}
