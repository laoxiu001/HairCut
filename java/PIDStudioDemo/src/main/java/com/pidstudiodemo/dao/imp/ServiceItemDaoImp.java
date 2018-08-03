package com.pidstudiodemo.dao.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.common.jpa.ServiceItemRepositoey;
import com.pidstudiodemo.dao.ServiceItemDao;
import com.pidstudiodemo.model.ServiceItem;
@Repository(value="serviceItemDao")
public class ServiceItemDaoImp implements ServiceItemDao {
	@Autowired
	private ServiceItemRepositoey serviceItemRepositoey;
	//id查询对应的记录
	public ServiceItem queryServiceItem(int id) {
		// TODO Auto-generated method stub
		return serviceItemRepositoey.findById(id);
	}
	public List<ServiceItem> queryServiceItemAll(boolean status) {
		// TODO Auto-generated method stub
		return serviceItemRepositoey.findByStatus(status);
	}
	public void insertServiceItem(ServiceItem serviceItem) {
		// TODO Auto-generated method stub
		serviceItemRepositoey.save(serviceItem);
	}
	public void updateStatus(ServiceItem serviceItem) {
		// TODO Auto-generated method stub
		serviceItemRepositoey.saveAndFlush(serviceItem);
	}
	public List<ServiceItem> queryServiceItemA() {
		// TODO Auto-generated method stub
		return serviceItemRepositoey.findAll();
	}
	public int countServiceItem(boolean status) {
		// TODO Auto-generated method stub
		return serviceItemRepositoey.findByStatus(true).size();
	}
	public List<ServiceItem> queryServiceItemAllF(boolean status, Pageable pageable) {
		// TODO Auto-generated method stub
		return serviceItemRepositoey.findByStatus(status,pageable);
	}
	public List<ServiceItem> queryConditions(String conditions,String status) {
		// TODO Auto-generated method stub
		boolean status1 =Boolean.parseBoolean(status);
		return serviceItemRepositoey.queryConditions(conditions,status1);
	}

}
