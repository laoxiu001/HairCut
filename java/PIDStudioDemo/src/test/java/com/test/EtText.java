package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pidstudiodemo.PIDApplication;
import com.pidstudiodemo.dao.EmployeeTypeDao;
import com.pidstudiodemo.model.EmployeeType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PIDApplication.class)
public class EtText {
	@Autowired
	@Qualifier(value="employeeTypeDao")
	private EmployeeTypeDao employeeTypeDao;
	@Test
	public void  test(){
//		  EmployeeType employeeType =  employeeTypeDao.queryEmployeeTypeId(5);
//		  employeeType.setStatus(0);
//		  System.out.println("状态"+employeeType.getStatus()+employeeType.getId());
//		  employeeTypeDao.updateEmployeeType(employeeType);
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		Long  a = Long.parseLong(s.format(date));
		System.out.println(a);
	}
}
