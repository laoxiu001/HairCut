package com.pidstudiodemo.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pidstudiodemo.dao.EmployeeTypeDao;
import com.pidstudiodemo.dao.ServiceItemDao;
import com.pidstudiodemo.model.EmployeeType;
import com.pidstudiodemo.model.ServiceItem;
import com.pidstudiodemo.service.EmployeeTypeService;
@Service(value="employeeTypeService")
public class EmployeeTypeServiceImp implements EmployeeTypeService{
	@Autowired
	@Qualifier(value="employeeTypeDao")
	private EmployeeTypeDao employeeTypeDao;
	@Autowired
	@Qualifier(value="serviceItemDao")
	private ServiceItemDao serviceItemDao;
	@Transactional
	public List<EmployeeType> queryEmployeeType(int status) {
		// TODO Auto-generated method stub
		List<EmployeeType> list = new ArrayList<EmployeeType>();
		list = employeeTypeDao.queryEmployeeType(status);
		return list;
	}
	@Transactional
	public List<EmployeeType> queryEmployeeTypeF(int page,int size, int status) {
		// TODO Auto-generated method stub
		try{int count;
		count = employeeTypeDao.countEmployeeType(status);
		if(page<0){page = 0;}
		if(page<=(count/size)&&count%size!=0){page=page+0;}
			else{if(count%size==0){page=count/size-1;}
			else{page = count/size;}}
		Pageable pageable = new PageRequest(page,size,Sort.Direction.ASC,"id");
		List<EmployeeType> list = new ArrayList<EmployeeType>();
		list = employeeTypeDao.queryEmployeeType(status,pageable);
		return list;}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public String updateEmployeeType(EmployeeType employeeType) {
		// TODO Auto-generated method stub
		try {
			employeeType.setStatus(1);
			employeeTypeDao.updateEmployeeType(employeeType);
			return "执行操作成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "执行操作失败";
		}
		
	}
	@Transactional
	public String deleteEmployeeType(int id) {
		// TODO Auto-generated method stub
		try {
		   EmployeeType employeeType =  employeeTypeDao.queryEmployeeTypeId(id);
		   employeeType.setStatus(0);
		   employeeType.setName(employeeType.getName()+"（员工类型已删除请尽快修改）");
		   employeeTypeDao.updateEmployeeType(employeeType);
		   
		   List<ServiceItem> list = serviceItemDao.queryServiceItemA();
		   for(ServiceItem s : list ){
			   String [] arr = s.getType().split(",");
			   String [] data = new String[arr.length];
			   for(int i = 0 ; i < arr.length;i++){
				   if(Integer.parseInt(arr[i])==id){
					   data[i] = "delete";//将删除的员工id标记为delete
					} else{
						data[i] =arr[i];
					}
			   }
			   String typeName ="";
				for(int i = 0; i<data.length;i++){//多选框传入的值 转换为String类型
					if(data[i]=="delete"){}else{
					typeName =typeName+data[i];//字符串拼接
					if(i!=data.length-1){
						typeName= typeName +",";
					}
					}
					}
			   s.setType(typeName);//更改过后的type值
			   serviceItemDao.insertServiceItem(s);//修改serviceTtem的数据
		   }
			return "删除成功(请尽快修改员工类型)";
		} catch (Exception e) {
			// TODO: handle exception
			return "请注意是否有员工是该类型";
		}
		
	}

	public String insertEmployeeType(EmployeeType employeeType) {
		// TODO Auto-generated method stub
		try {
			employeeTypeDao.insertEmployeeType(employeeType);
			return "添加成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "添加失败";
		}
	}

	public int countEmployeeType() {
		// TODO Auto-generated method stub
		return employeeTypeDao.countEmployeeType(1);//在用的员工类型总数量
	}
	//返回最大页数
	public int queryMaxPage(int page, int size) {
		// TODO Auto-generated method stub
		int count;
		count = employeeTypeDao.countEmployeeType(1);
		if(count%size==0){page=count/size-1;}
		else{page = count/size;}
		return page;
	}
	public List<EmployeeType> queryConditions(String conditions) {
		// TODO Auto-generated method stub
		return employeeTypeDao.queryConditions(conditions);
	}
}
