package com.pidstudiodemo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pidstudiodemo.dao.ServiceItemDao;
import com.pidstudiodemo.model.ServiceItem;
import com.pidstudiodemo.service.ServiceItemService;
@Service(value="serviceItemService")
public class ServiceItemServiceImp implements ServiceItemService {
	@Autowired
	@Qualifier(value="serviceItemDao")
	private ServiceItemDao serviceItemDao;
	//查询id
	public ServiceItem queryServiceItem(int id) {
		// TODO Auto-generated method stub
		try {
			return serviceItemDao.queryServiceItem(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	public List<ServiceItem> queryServiceItemAll(boolean status) {
		// TODO Auto-generated method stub
		return serviceItemDao.queryServiceItemAll(status);
	}
	
	public String insertServiceItem(ServiceItem serviceItem) {
		// TODO Auto-generated method stub
		try {
			serviceItem.setStatus(true);
			serviceItemDao.insertServiceItem(serviceItem);
			return "添加成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "添加失败";
		}
		
	}

	public String updateStatus(ServiceItem serviceItem) {
		// TODO Auto-generated method stub
		try{
		boolean status = serviceItem.isStatus();
		if(status){
			status = false;
		}else{
			status = true;
		}
		System.out.println(status);
		serviceItem.setStatus(status);
		serviceItemDao.updateStatus(serviceItem);
		return "修改成功";}catch(Exception e){
			return "修改失败";
		}
	}
}
