package com.pidstudiodemo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 服务项目管理表
 * **/
@Entity
@Table(name="ServiceItem")
public class ServiceItem implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name ="id")
private int id;//服务项目编号
@Column(name ="name",columnDefinition="varchar(128)  default null COMMENT '服务项目名称'")
private String name;//服务项目名称
@Column(name ="price",columnDefinition="double default 0 COMMENT '服务项目价格'",nullable=false)
private double price;//服务项目价格
@Column(name ="introduction",columnDefinition="varchar(128)  default null COMMENT '服务项目简介'")
private String introduction;//服务项目简介
@Column(name ="type",columnDefinition="int  default null COMMENT '可提供该服务的员工类型'")
private int type;//可提供该服务的员工类型（可多选）
@Column(name="status",columnDefinition="boolean  default true COMMENT '判断服务是否在使用'")
private boolean status  ;//判断服务是否在使用
@OneToMany( mappedBy="serviceItem",cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
private Set<Record> setRecord = new HashSet<Record>();
public Set<Record> getSetRecord() {
	return setRecord;
}
public void setSetRecord(Set<Record> setRecord) {
	this.setRecord = setRecord;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}

public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public void setName(String name) {
	this.name = name;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getIntroduction() {
	return introduction;
}
public void setIntroduction(String introduction) {
	this.introduction = introduction;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
}
