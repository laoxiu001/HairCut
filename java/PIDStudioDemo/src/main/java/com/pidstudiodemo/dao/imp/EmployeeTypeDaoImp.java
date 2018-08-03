package com.pidstudiodemo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.common.jpa.EmployeeTypeRepositoey;
import com.pidstudiodemo.dao.EmployeeTypeDao;
import com.pidstudiodemo.model.EmployeeType;
@Repository(value="employeeTypeDao")
public class EmployeeTypeDaoImp implements EmployeeTypeDao {
	@Autowired
	private EmployeeTypeRepositoey employeeTypeRepositoey;
	public List<EmployeeType> queryEmployeeType(int status) {
		// TODO Auto-generated method stub
		List<EmployeeType> list = new ArrayList<EmployeeType>();
		list = (List<EmployeeType>) employeeTypeRepositoey.findByStatus(status);
		return list;
	}
	public void updateEmployeeType(EmployeeType employeeType) {
		// TODO Auto-generated method stub
		employeeTypeRepositoey.saveAndFlush(employeeType);
	}
	public void insertEmployeeType(EmployeeType employeeType) {
		// TODO Auto-generated method stub
		employeeTypeRepositoey.save(employeeType);
	}
	public int countEmployeeType(int status) {
		// TODO Auto-generated method stub
		return (int)employeeTypeRepositoey.findByStatus(status).size();
	}
	public EmployeeType queryEmployeeTypeId(int id) {
		// TODO Auto-generated method stub
		return employeeTypeRepositoey.findById(id);
	}
	public List<EmployeeType> queryEmployeeType(int status, Pageable pageable) {
		// TODO Auto-generated method stub
		List<EmployeeType> list = new ArrayList<EmployeeType>();
		list = (List<EmployeeType>) employeeTypeRepositoey.findByStatus(status,pageable);
		return list;
	}
	public List<EmployeeType> queryConditions(String conditions) {
		// TODO Auto-generated method stub
		return employeeTypeRepositoey.queryConditions(conditions);
	}
}
