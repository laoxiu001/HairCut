package com.test;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pidstudiodemo.PIDApplication;
import com.pidstudiodemo.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PIDApplication.class)
public class LoginTest {
	@Autowired
	@Qualifier(value="loginService")
	private LoginService loginService;
	@Test
	public void test(){
		HttpSession session = null ;
		System.out.println(loginService.sendMsg("18315170177",session));
	}
}
