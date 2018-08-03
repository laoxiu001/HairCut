package com.pidstudiodemo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pidstudiodemo.model.ServiceItem;

public interface ServiceItemDao {
	//id查询对应的记录
	public ServiceItem queryServiceItem(int id);
	//查询所有记录(根据boolean)
	public List<ServiceItem> queryServiceItemAll(boolean status);
	//添加服务项目
	public void insertServiceItem(ServiceItem serviceItem);
	//删除服务项目
	public void updateStatus(ServiceItem serviceItem);
	//查询所有记录
	public List<ServiceItem> queryServiceItemA();
	//查询总的服务条数
	public int countServiceItem(boolean status);
	//分页查询
	public List<ServiceItem> queryServiceItemAllF(boolean status, Pageable pageable);
	//多字段模糊查询
	public List<ServiceItem> queryConditions(String conditions, String status);

}
