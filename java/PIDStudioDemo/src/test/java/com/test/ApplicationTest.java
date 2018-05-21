package com.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pidstudiodemo.PIDApplication;
import com.pidstudiodemo.common.jpa.EmployeeManageRepositoey;
import com.pidstudiodemo.common.jpa.EmployeeTypeRepositoey;
import com.pidstudiodemo.common.jpa.LoginRepository;
import com.pidstudiodemo.model.EmployeeManage;
import com.pidstudiodemo.service.EmployeeManageServic;
import com.pidstudiodemo.service.PayListService;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PIDApplication.class)
public class ApplicationTest {
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private EmployeeTypeRepositoey etr;
	@Autowired
	EmployeeManageRepositoey employeeManageRepositoey;
	@Autowired
	private EmployeeManageServic employeeManageServic;
	@Autowired
	private EmployeeManageRepositoey emr;
	@Test
    public void test() throws Exception {

		/*em.setNumber("s");
		em.setName("s");
		em.setPassword("");
		em.setPhoto("s");
		em.setSex("s");
		em.setTypeId(1);
		loginRepository.save(em);*/
		//et.delete(1);
		/*em.setPhoto("s");
		em.setNumber("0002");
		em.setId(2);
		em.setTypeId(1);
		loginRepository.saveAndFlush(em);*/
		/*EmployeeType et = new EmployeeType();
		et.setName("qqq");
		etr.save(et);
		*/
		/*List<EmployeeManage> list = new ArrayList<EmployeeManage>();
		//list =employeeManageServic.queryEmployeeManageService(0,1,"在职");
		//list = employeeManageServic.queryDetailedService(1);
		list = emr.queryConditions("男");
		System.out.println(list);
		//list = emr.queryDetailed(1);
		for(EmployeeManage em : list){
			System.out.println(em.getEmployeeType().getName());
			System.out.println(em.getStatus());
		}*/
		/*EmployeeManage em = new EmployeeManage();
		em.setNumber("s");
		em.setTypeId(2);
		employeeManageServic.insertEmployeeService(em);*/
		System.out.println(employeeManageRepositoey.count());
		employeeManageRepositoey.queryDetailed(1);
    }
}
