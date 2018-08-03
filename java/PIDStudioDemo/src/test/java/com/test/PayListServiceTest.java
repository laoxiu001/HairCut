package com.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pidstudiodemo.PIDApplication;
import com.pidstudiodemo.common.jpa.PayListRepositoey;
import com.pidstudiodemo.common.jpa.RecordRepositoey;
import com.pidstudiodemo.model.PayList;
import com.pidstudiodemo.service.PayListService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PIDApplication.class)
public class PayListServiceTest {
	@Autowired
	@Qualifier(value="payListService")
	private PayListService payListService;
	@Autowired
	private PayListRepositoey pLR;
	@Autowired
	private RecordRepositoey rR;
	@Test
	public void test(){
	/*Page<PayList>  list = payListService.queryPayList(0,2);
	for (PayList pl : list){
		System.out.println(pl.getGold());
		System.out.println(pl.getUserName());
	}*/
	PayList payList = new PayList();
	payList.setUserName("1");
	payList.setGold(300);
	payList.setType("支付宝");
	//System.out.println(payListService.insertPayList(payList));
	}
}
