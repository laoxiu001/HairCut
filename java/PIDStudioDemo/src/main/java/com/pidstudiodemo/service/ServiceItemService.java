package com.pidstudiodemo.service;

import java.util.List;

import com.pidstudiodemo.model.ServiceItem;

public interface ServiceItemService {
	//根据id查询服务表的数据
	public ServiceItem queryServiceItem(int id);
	//查询所有数据
	public List<ServiceItem> queryServiceItemAll(boolean status);
	//添加服务项目
	public String insertServiceItem(ServiceItem serviceItem);
	//删除服务项目
	public String updateStatus(ServiceItem serviceItem);
}
