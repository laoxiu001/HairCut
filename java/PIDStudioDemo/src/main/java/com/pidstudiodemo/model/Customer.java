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

import org.springframework.data.annotation.Transient;
/**
 * 客户信息表
 * */
@Entity//扫描器可烧苗到的标志
@Table(name="Customer")//配置映射类所对应的表
public class Customer implements Serializable{	
/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
@Id  //主键字段
@GeneratedValue(strategy=GenerationType.AUTO)////@GeneratedValue注解的作用是配置该主键字段为自增字段
@Column(name="id")
private int id;//客户编号自增长
@Column(name = "userName",columnDefinition="varchar(128) default null COMMENT '客户账号'")
private String userName;//客户账号
@Column(name = "phone",columnDefinition="varchar(128) default null COMMENT '客户手机号'")
private String phone;//客户手机号
@Column(name = "name",columnDefinition="varchar(128) default null COMMENT '客户姓名'")
private String name;//客户姓名
@Column(name = "discount",columnDefinition="double default 1 COMMENT '客户折扣系数'")
private double discount;//客户折扣系数
@Column(name = "balance",columnDefinition="double default 0 COMMENT '余额'")
private double balance;//余额
@Column(name = "status",columnDefinition="varchar(128) default '激活' COMMENT '客户状态'",nullable=false)
private String status;//客户状态
//mappedBy指多方的外键字段对应成员变量(类变量)，cascade配置相关对象的级联关系，fetch指定加载策略（急加载还是懒加载）
@OneToMany( mappedBy="customer",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
private Set<PayList> setPayList = new HashSet<PayList>();//一对多：关联查询查询Record

public Set<PayList> getSetPayList() {
	return setPayList;
}
public void setSetPayList(Set<PayList> setPayList) {
	this.setPayList = setPayList;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getDiscount() {
	return discount;
}
public void setDiscount(double discount) {
	this.discount = discount;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}

}
