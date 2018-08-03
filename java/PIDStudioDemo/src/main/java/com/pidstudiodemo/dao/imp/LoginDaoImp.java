package com.pidstudiodemo.dao.imp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.common.jpa.LoginRepository;
import com.pidstudiodemo.dao.LoginDao;
import com.pidstudiodemo.model.EmployeeManage;
@Repository(value="loginDao")
public class LoginDaoImp implements LoginDao {
	@Autowired
	private LoginRepository loginRepository;
	public EmployeeManage login(String number,String password ){	
		EmployeeManage employeeManage = loginRepository.findByStuNumber(number,password);
	return employeeManage;
	}

	public int changePassword(String number, String password) {
		// TODO Auto-generated method stub
		return loginRepository.updatePassword(number, password);
	}
	public EmployeeManage queryPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		EmployeeManage em = loginRepository.findByPhone(phoneNumber);
		return em;
	}
	public void forgotPassword(String phoneNumber, String newPassword_1) {
		// TODO Auto-generated method stub
		loginRepository.update(phoneNumber, newPassword_1);
	}
}
