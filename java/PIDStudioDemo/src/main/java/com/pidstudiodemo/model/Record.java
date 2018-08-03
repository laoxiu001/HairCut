package com.pidstudiodemo.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户消费记录表
 * */
@Entity
@Table(name="Record")
public class Record implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name ="id")
	private int id ;
	@Column(name ="userName",columnDefinition="varchar(128)  default null COMMENT '消费客户账号'")
	private String username;//消费客户账户(对应customer表的客户账号)
	@Column(name ="name",columnDefinition="varchar(128)  default null COMMENT '消费的姓名'")
	private String name;//消费的姓名
	@Column(name ="payavle",columnDefinition="double default 0 COMMENT '应付金额'",nullable=false)
	private double payavle;//应付金额
	@Column(name ="actuallyPaid",columnDefinition="double default 0 COMMENT '实付金额'",nullable=false)
	private double actuallyPaid;//实付金额
	@Column(name ="date",columnDefinition="timestamp NULL default CURRENT_TIMESTAMP COMMENT '消费时间'",nullable=false)
	private Date date;//消费时间
	@Column(name="number",updatable=true,insertable=true)
	private String number;
	@Column(name="service",updatable=true,insertable=true)
	private int service;
	@ManyToOne(cascade =CascadeType.MERGE)
	@JoinColumn(name="number",referencedColumnName="number",updatable=false,insertable=false)//创建number字段与表employee_manage主键关联（外键）
	private EmployeeManage employeeManage;//关联变量 员工管理表
	@ManyToOne(cascade =CascadeType.MERGE)
	@JoinColumn(name="service",updatable=false,insertable=false)
	private ServiceItem serviceItem;//项目管理表
	@ManyToOne(cascade =CascadeType.MERGE)
	@JoinColumn(name="userName",referencedColumnName="userName", updatable=false,insertable=false)
	private Customer customer;//用户表
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getService() {
		return service;
	}
	public void setService(int service) {
		this.service = service;
	}
	public ServiceItem getServiceItem() {
		return serviceItem;
	}
	public void setServiceItem(ServiceItem serviceItem) {
		this.serviceItem = serviceItem;
	}
	public EmployeeManage getEmployeeManage() {
		return employeeManage;
	}
	public void setEmployeeManage(EmployeeManage employeeManage) {
		this.employeeManage = employeeManage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPayavle() {
		return payavle;
	}
	public void setPayavle(double payavle) {
		this.payavle = payavle;
	}
	public double getActuallyPaid() {
		return actuallyPaid;
	}
	public void setActuallyPaid(double actuallyPaid) {
		this.actuallyPaid = actuallyPaid;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}

	

}
