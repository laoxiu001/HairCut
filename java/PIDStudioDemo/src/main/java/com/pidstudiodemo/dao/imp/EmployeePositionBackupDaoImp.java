package com.pidstudiodemo.dao.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.common.jpa.EmployeePositionBackupR;
import com.pidstudiodemo.dao.EmployeePositionBackupDao;
import com.pidstudiodemo.model.EmployeePositionBackup;
@Repository(value="employeePositionBackupDao")
public class EmployeePositionBackupDaoImp implements EmployeePositionBackupDao{
	@Autowired
	private EmployeePositionBackupR epbr;
	public void insert(EmployeePositionBackup ems) {
		// TODO Auto-generated method stub
		epbr.save(ems);
	}
	public List<EmployeePositionBackup> query() {
		// TODO Auto-generated method stub
		return epbr.findAll();
	}
	public void delete() {
		// TODO Auto-generated method stub
		epbr.deleteAll();
	}
	public void delete(int typeId) {
		// TODO Auto-generated method stub
		epbr.delete(typeId);
	}

}
