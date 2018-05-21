package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pidstudiodemo.PIDApplication;
import com.pidstudiodemo.common.jpa.RecordRepositoey;
import com.pidstudiodemo.model.Record;
import com.pidstudiodemo.service.RecordService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PIDApplication.class)
public class RecordServiceTest {
	@Autowired
	@Qualifier(value="recordService")
	private RecordService rS;
	@Autowired
	private RecordRepositoey rR;
	@Test
	public void test(){
		int [] serviceIemId = {1,2,4};
		String [] s = {"0002","0001","0001"};
		Record record = new Record();
		record.setName("老胡");
		record.setUsername("1");
		System.out.println(rS.insertRecord(record, serviceIemId,s));
	}	
}
