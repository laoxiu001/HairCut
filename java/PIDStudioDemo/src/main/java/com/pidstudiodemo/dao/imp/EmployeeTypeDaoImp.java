package com.pidstudiodemo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.common.jpa.EmployeeTypeRepositoey;
import com.pidstudiodemo.dao.EmployeeTypeDao;
import com.pidstudiodemo.model.EmployeeType;
@Repository(value="employeeTypeDao")
public class EmployeeTypeDaoImp implements EmployeeTypeDao {
	@Autowired
	private EmployeeTypeRepositoey employeeTypeRepositoey;
	public List<EmployeeType> queryEmployeeType() {
		// TODO Auto-generated method stub
		List<EmployeeType> list = new ArrayList<EmployeeType>();
		list = (List<EmployeeType>) employeeTypeRepositoey.findAll();
		return list;
	}
	public void updateEmployeeType(EmployeeType employeeType) {
		// TODO Auto-generated method stub
		employeeTypeRepositoey.saveAndFlush(employeeType);
	}
	public void deleteEmployeeType(int id) {
		// TODO Auto-generated method stub
		employeeTypeRepositoey.delete(id);
	}
	public void insertEmployeeType(EmployeeType employeeType) {
		// TODO Auto-generated method stub
		employeeTypeRepositoey.save(employeeType);
	}
	public int countEmployeeType() {
		// TODO Auto-generated method stub
		return (int)employeeTypeRepositoey.count();
	}
}
