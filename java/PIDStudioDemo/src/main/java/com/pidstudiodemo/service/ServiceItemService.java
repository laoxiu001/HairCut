package com.pidstudiodemo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.pidstudiodemo.model.EmployeeType;
import com.pidstudiodemo.model.ServiceItem;

public interface ServiceItemService {
	//根据id查询服务表的数据
	public ServiceItem queryServiceItem(int id);
	//查询所有数据
	public List<ServiceItem> queryServiceItemAll(boolean status);
	//添加服务项目
	public String insertServiceItem(ServiceItem serviceItem, int[] typeId);
	//删除服务项目
	public String updateStatus(ServiceItem serviceItem);
	//查询员工类型
	public List<EmployeeType> queryEmployeeType();
	//分页查询
	public List<ServiceItem> queryServiceItemAllF(int page, int size, boolean status);
	//最大页数
	public int queryMaxPage(int page, int size,boolean status);
	//模糊查询
	public List<ServiceItem> queryConditions(String conditions, String statys);
}
