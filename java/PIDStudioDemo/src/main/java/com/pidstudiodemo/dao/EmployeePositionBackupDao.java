package com.pidstudiodemo.dao;

import java.util.List;

import com.pidstudiodemo.model.EmployeePositionBackup;

public interface EmployeePositionBackupDao {
	//添加
	void insert(EmployeePositionBackup ems);
	//查询所有记录
	List<EmployeePositionBackup> query();
	//删除全部
	void delete();
	//删除对应ID的记录
	void delete(int typeId);

}
