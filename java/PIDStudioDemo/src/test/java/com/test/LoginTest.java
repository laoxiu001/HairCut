package com.test;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pidstudiodemo.PIDApplication;
import com.pidstudiodemo.dao.LoginDao;
import com.pidstudiodemo.model.EmployeeManage;
import com.pidstudiodemo.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PIDApplication.class)
public class LoginTest {
	@Autowired
	@Qualifier(value="loginService")
	private LoginService loginService;
	@Autowired
	@Qualifier(value="loginDao")
	private LoginDao loginDao;
	@Test
	public void test(){
		//HttpSession session = null ;
		EmployeeManage em =  loginDao.login("0001", "2"); 
		//System.out.println(loginService.sendMsg("18315170177",session));
	}
}
