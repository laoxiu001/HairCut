package com.pidstudiodemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 员工职位备份
 * */
@Entity//扫描器可烧苗到的标志
@Table(name="EmployeePositionBackup")//配置映射类所对应的表
public class EmployeePositionBackup {
	@Id  //主键字段
	@GeneratedValue(strategy=GenerationType.AUTO)////@GeneratedValue注解的作用是配置该主键字段为自增字段
	@Column(name="id")
	private int id;//客户编号自增长
	@Column(name="employeeId")
	int employeeId;//员工id
	@Column(name="positionId")
	int positionId;//员工职位Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	
}
