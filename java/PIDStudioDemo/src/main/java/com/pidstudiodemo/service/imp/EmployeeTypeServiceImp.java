package com.pidstudiodemo.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pidstudiodemo.dao.EmployeeTypeDao;
import com.pidstudiodemo.model.EmployeeType;
import com.pidstudiodemo.service.EmployeeTypeService;
@Service(value="employeeTypeService")
public class EmployeeTypeServiceImp implements EmployeeTypeService{
	@Autowired
	@Qualifier(value="employeeTypeDao")
	private EmployeeTypeDao employeeTypeDao;
	@Transactional
	public List<EmployeeType> queryEmployeeType() {
		// TODO Auto-generated method stub
		List<EmployeeType> list = new ArrayList<EmployeeType>();
		list = employeeTypeDao.queryEmployeeType();
		return list;
	}
	
	public String updateEmployeeType(EmployeeType employeeType) {
		// TODO Auto-generated method stub
		try {
			employeeTypeDao.updateEmployeeType(employeeType);
			return "修改成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "修改失败";
		}
		
	}

	public String deleteEmployeeType(int id) {
		// TODO Auto-generated method stub
		try {
			employeeTypeDao.deleteEmployeeType(id);
			return "删除成功";
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
		return employeeTypeDao.countEmployeeType();
	}

}
