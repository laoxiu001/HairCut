package com.pidstudiodemo.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

/**
 * 员工管理表
 * */
@Entity
@Table(name="EmployeeManage")
public class EmployeeManage implements Serializable{
/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name= "id")
private int id;//员工编号
@Column(name= "number",columnDefinition="varchar(128)  default null COMMENT '员工工号'")
private String number;//员工工号
@Column(name= "name",columnDefinition="varchar(128)  default null COMMENT '员工姓名'")
private String name;//员工姓名
@Column(name= "sex",columnDefinition="varchar(128)  default null COMMENT '性别'")
private String sex;//员工性别
@Column(name ="password",columnDefinition="varchar(128)  default null COMMENT '密码'")
private String password;//密码
@Column(name= "photo",columnDefinition="varchar(128)  default null COMMENT '照片'")
private String photo;//照片
@Column(name ="phone",columnDefinition="varchar(128)  default null COMMENT '员工电话'")
private String phone;
@Column(name ="status",columnDefinition="varchar(128)  default '在职' COMMENT '状态'",nullable=false)
private String status = "在职";//状态
@Column(name="typeId",updatable=true,insertable=true)
private int typeId;//员工编号
/*
 * 外键typeId
 * **/
@ManyToOne(cascade ={CascadeType.MERGE})
@JoinColumn(name="typeId",updatable=false,insertable=false)//创建type_id字段与表employee_type主键关联（外键）
private EmployeeType employeeType;//多对一表：用于多表关联查询查询employee_type
//@Transient //注释忽略该标注修饰的变量
/*@OneToMany( mappedBy="employeeManage",cascade={CascadeType.ALL,CascadeType.REMOVE} , fetch = FetchType.EAGER)
private Set<Record> setRecord = new HashSet<Record>();//一对多：关联查询查询setEmployeeManage
public Set<Record> getSetRecord() {
	return setRecord;
}
public void setSetRecord(Set<Record> setRecord) {
	this.setRecord = setRecord;
}*/
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public int getTypeId() {
	return typeId;
}
public void setTypeId(int typeId) {
	this.typeId = typeId;
}
public EmployeeType getEmployeeType() {
	return employeeType;
}
public void setEmployeeType(EmployeeType employeeType) {
	this.employeeType = employeeType;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
}
