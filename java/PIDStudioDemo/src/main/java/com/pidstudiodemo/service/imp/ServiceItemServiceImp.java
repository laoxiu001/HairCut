package com.pidstudiodemo.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.pidstudiodemo.dao.EmployeeTypeDao;
import com.pidstudiodemo.dao.ServiceItemDao;
import com.pidstudiodemo.model.EmployeeType;
import com.pidstudiodemo.model.ServiceItem;
import com.pidstudiodemo.service.ServiceItemService;
@Service(value="serviceItemService")
public class ServiceItemServiceImp implements ServiceItemService {
	@Autowired
	@Qualifier(value="serviceItemDao")
	private ServiceItemDao serviceItemDao;
	@Autowired
	@Qualifier(value="employeeTypeDao")
	private EmployeeTypeDao employeeTypeDao;
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
	public List<EmployeeType> queryEmployeeType(){
		try{
		return employeeTypeDao.queryEmployeeType(1);}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public List<ServiceItem> queryServiceItemAll(boolean status) {
		// TODO Auto-generated method stub
		try{
		List<ServiceItem> siList = serviceItemDao.queryServiceItemAll(status);//得到siList对象 存储数据库中的服务类型记录
		List<ServiceItem> list = new ArrayList<ServiceItem>();//新的list存储在页面显示的数据
		List<EmployeeType> etList = employeeTypeDao.queryEmployeeType(1);//获取employeeType表中的数据
		String typeName;
		for(ServiceItem si : siList){
			if(si.getType().equals("")){typeName ="没有该服务类型的员工";}else{
			String [] typeId = si.getType().split(",");//截取type字段储存为数组形式
			 typeName = "";//新的type为服务的名组成 以前存储好的是服务的id
			if(typeId.length!=0){
			for(EmployeeType et : etList)//遍历silist 中的数据
			for(int i = 0; i<typeId.length;i++){//遍历数组
				if(et.getId()==Integer.parseInt(typeId[i])){//判断数组中的值和et对象的id值相等
					typeName =typeName +  et.getName();//字符串拼接得到新的type
					if(i!=typeId.length-1){//除最后一个外得到的字符后面加一个逗号
						typeName= typeName +",";
					}
					continue;//跳出当前此次循环
				}
			}
			}
			}
		si.setType(typeName);
		list.add(si);
		}
		return list;}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	public String insertServiceItem(ServiceItem serviceItem,int [] typeId) {
		// TODO Auto-generated method stub
		try {
			String typeName ="";
			for(int i = 0; i<typeId.length;i++){//多选框传入的值 转换为String类型
				typeName =typeName+typeId[i];//字符串拼接
				if(i!=typeId.length-1){
					typeName= typeName +",";
				}
				}
			System.out.println("串拼接"+typeName);
			serviceItem.setType(typeName);
			serviceItem.setStatus(true);
			serviceItemDao.insertServiceItem(serviceItem);
			return "执行操作成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "执行操作失败";
		}
		
	}

	public String updateStatus(ServiceItem serviceItem) {
		// TODO Auto-generated method stub
		try{
			serviceItem = serviceItemDao.queryServiceItem(serviceItem.getId());
			boolean status = serviceItem.getStatus();
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
	//分页查询
	public List<ServiceItem> queryServiceItemAllF(int page, int size, boolean status) {
		// TODO Auto-generated method stub
		try {
			
		
		int count;
		count = serviceItemDao.countServiceItem(status);
		//判断page的值
		if(page<=(count/size)){page=page+0;}
			else{if(count%size==0){page = count/size;}
			else{page = count/size+1;}}
		Pageable pageable = new  PageRequest(page,size,Sort.Direction.DESC, "id");
		List<ServiceItem> siList = serviceItemDao.queryServiceItemAllF(status,pageable);//得到siList对象 存储数据库中的服务类型记录
		List<ServiceItem> list = new ArrayList<ServiceItem>();//新的list存储在页面显示的数据
		List<EmployeeType> etList = employeeTypeDao.queryEmployeeType(1);//获取employeeType表中的数据
		String typeName;
		for(ServiceItem si : siList){
			if(si.getType().equals("")){typeName ="没有该服务类型的员工";}else{
			String [] typeId = si.getType().split(",");//截取type字段储存为数组形式
			 typeName = "";//新的type为服务的名组成 以前存储好的是服务的id
			if(typeId.length!=0){
			for(EmployeeType et : etList)//遍历silist 中的数据
			for(int i = 0; i<typeId.length;i++){//遍历数组
				if(et.getId()==Integer.parseInt(typeId[i])){//判断数组中的值和et对象的id值相等
					typeName =typeName +  et.getName();//字符串拼接得到新的type
					if(i!=typeId.length-1){//除最后一个外得到的字符后面加一个逗号
						typeName= typeName +",";
					}
					continue;//跳出当前此次循环
				}
			}
			}
			}
		si.setType(typeName);
		list.add(si);
		}
		return list;} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	public int queryMaxPage(int page, int size,boolean status) {
		// TODO Auto-generated method stub
		int count;
		count = serviceItemDao.countServiceItem(status);
		if(count%size==0){page=count/size-1;}
		else{page = count/size;}
		return page;
	}
	public List<ServiceItem> queryConditions(String conditions,String status) {
		// TODO Auto-generated method stub
		List<ServiceItem> siList = serviceItemDao.queryConditions(conditions,status);
		List<ServiceItem> list = new ArrayList<ServiceItem>();//新的list存储在页面显示的数据
		List<EmployeeType> etList = employeeTypeDao.queryEmployeeType(1);//获取employeeType表中的数据
		String typeName;
		for(ServiceItem si : siList){
			if(si.getType().equals("")){typeName ="没有该服务类型的员工";}else{
			String [] typeId = si.getType().split(",");//截取type字段储存为数组形式
			 typeName = "";//新的type为服务的名组成 以前存储好的是服务的id
			if(typeId.length!=0){
			for(EmployeeType et : etList)//遍历silist 中的数据
			for(int i = 0; i<typeId.length;i++){//遍历数组
				if(et.getId()==Integer.parseInt(typeId[i])){//判断数组中的值和et对象的id值相等
					typeName =typeName +  et.getName();//字符串拼接得到新的type
					if(i!=typeId.length-1){//除最后一个外得到的字符后面加一个逗号
						typeName= typeName +",";
					}
					continue;//跳出当前此次循环
				}
			}
			}
			}
		si.setType(typeName);
		list.add(si);
		}
		return list;
	}
}
