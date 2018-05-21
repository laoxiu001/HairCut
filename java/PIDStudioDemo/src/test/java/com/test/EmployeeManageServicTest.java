package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pidstudiodemo.PIDApplication;
import com.pidstudiodemo.common.jpa.EmployeeManageRepositoey;
import com.pidstudiodemo.service.EmployeeManageServic;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PIDApplication.class)
public class EmployeeManageServicTest {
@Autowired
@Qualifier(value="employeeManageServic")
private EmployeeManageServic employeeManageServic;
@Autowired
EmployeeManageRepositoey employeeManageRepositoey;
@Test
public void test(){
	/*EmployeeManage em = new EmployeeManage();
	em.setNumber("0004");
	em.setTypeId(6);
	em.setName("ssss");
	System.out.println(employeeManageServic.insertEmployee(em));*/
	//employeeManageServic.queryNumber("0002");
	employeeManageRepositoey.queryDetailed(1);
}
}
