package com.pidstudiodemo.view.model;

import com.pidstudiodemo.model.EmployeeManage;
import com.pidstudiodemo.model.EmployeeType;
import com.pidstudiodemo.model.Record;

public class EmployeeManageSum {
	private EmployeeManage employeeManage;
	private EmployeeType employeeType;
	private Record record;
	private double sumPayavle;//实付金额总和
	private long count;
	private double sumSalary;//工资总和
	public EmployeeManage getEmployeeManage() {
		return employeeManage;
	}
	public void setEmployeeManage(EmployeeManage employeeManage) {
		this.employeeManage = employeeManage;
	}
	public EmployeeType getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}
	public Record getRecord() {
		return record;
	}
	public void setRecord(Record record) {
		this.record = record;
	}
	public double getSumPayavle() {
		return sumPayavle;
	}
	public void setSumPayavle(double sumPayavle) {
		this.sumPayavle = sumPayavle;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public double getSumSalary() {
		return sumSalary;
	}
	public void setSumSalary(double sumSalary) {
		this.sumSalary = sumSalary;
	}
	public EmployeeManageSum(EmployeeManage employeeManage,
			EmployeeType employeeType, Record record, double sumPayavle,
			long count, double sumSalary) {
		super();
		this.employeeManage = employeeManage;
		this.employeeType = employeeType;
		this.record = record;
		this.sumPayavle = sumPayavle;
		this.count = count;
		this.sumSalary = sumSalary;
	}
	
	
}
