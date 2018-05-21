package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pidstudiodemo.PIDApplication;
import com.pidstudiodemo.model.Customer;
import com.pidstudiodemo.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PIDApplication.class)
public class CustomerServiceTest {
@Autowired
@Qualifier(value="customerService")
private CustomerService customerService;
@Test
public void test(){
	Customer customer =customerService.quserCUserName("1");
	System.out.println(customer.getId());
}
}
