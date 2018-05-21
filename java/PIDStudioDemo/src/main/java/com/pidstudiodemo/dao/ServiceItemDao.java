package com.pidstudiodemo.dao;

import java.util.List;

import com.pidstudiodemo.model.ServiceItem;

public interface ServiceItemDao {
	//id查询对应的记录
	public ServiceItem queryServiceItem(int id);
	//查询所有记录
	public List<ServiceItem> queryServiceItemAll(boolean status);
	//添加服务项目
	public void insertServiceItem(ServiceItem serviceItem);
	//删除服务项目
	public void updateStatus(ServiceItem serviceItem);

}
