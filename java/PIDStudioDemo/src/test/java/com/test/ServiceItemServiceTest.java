package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pidstudiodemo.PIDApplication;
import com.pidstudiodemo.model.ServiceItem;
import com.pidstudiodemo.service.ServiceItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PIDApplication.class)
public class ServiceItemServiceTest {
	@Autowired
	@Qualifier(value="serviceItemService")
	private ServiceItemService serviceItemService;
	@Test
	public void test(){
		/*ServiceItem serviceItem = new ServiceItem();
		serviceItem.setName("何");
		System.out.println(serviceItemService.insertServiceItem(serviceItem));*/
		//System.out.println(serviceItemService.updateStatus(serviceItem));
		/*serviceItemService.deleteServiceItem(4);*/
		List<ServiceItem> list = serviceItemService.queryServiceItemAll(true);
		for(ServiceItem s : list){
			System.out.println(s.getPrice());
			System.out.println(s.getStatus());
			System.out.println(s.getType());
		}
	}
}
