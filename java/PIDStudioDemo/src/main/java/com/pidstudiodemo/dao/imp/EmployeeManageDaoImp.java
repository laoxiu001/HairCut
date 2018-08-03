package com.pidstudiodemo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.common.jpa.EmployeeManageRepositoey;
import com.pidstudiodemo.dao.EmployeeManageDao;
import com.pidstudiodemo.model.EmployeeManage;
@Repository(value="employeeManageDao")
public class EmployeeManageDaoImp implements EmployeeManageDao {
	@Autowired
	private EmployeeManageRepositoey employeeManageRepositoey;

	public List<EmployeeManage> queryEmployeeManage(String status,Pageable pageable) {
		// TODO Auto-generated method stub
		List<EmployeeManage> list = new ArrayList<EmployeeManage>();
		list = employeeManageRepositoey.findByStatus(status,pageable);
		return list;
	}
	
	public List<EmployeeManage> queryDetailed(int id) {
		// TODO Auto-generated method stub
		List<EmployeeManage> list = new ArrayList<EmployeeManage>();
		list = employeeManageRepositoey.queryDetailed(id);
		return list;
	}
	
	public void insertEmployee(EmployeeManage em) {
		// TODO Auto-generated method stub
		try{
		employeeManageRepositoey.save(em);}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void updateEmployee(EmployeeManage em) {
		// TODO Auto-generated method stub
		employeeManageRepositoey.saveAndFlush(em);
	}
	public int countEmployee(){
		return (int) employeeManageRepositoey.count();
	}

	public EmployeeManage queryNumber(String number) {
		// TODO Auto-generated method stub
		return employeeManageRepositoey.findByNumber(number);
	}

	public int countEmployee(String stasus) {
		// TODO Auto-generated method stub
		return employeeManageRepositoey.findByStatus(stasus).size();
	}

	public List<EmployeeManage> queryConditions(String conditions,String status) {
		// TODO Auto-generated method stub
		return employeeManageRepositoey.queryConditions(conditions,status);
	}

	public List<EmployeeManage> queryEmployeeId(int id) {
		// TODO Auto-generated method stub
		return employeeManageRepositoey.findByTypeIdAndStatus(id,"在职");
	}



}
