package com.pidstudiodemo.model;

import java.io.Serializable;
import java.util.Date;

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
 * 用户充值记录表
 * **/
@Entity
@Table(name="PayList")
public class PayList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id")
	private int id;//充值记录编号
	@Column(name= "goid",columnDefinition="double default 0 COMMENT '充值金额'",nullable=false)
	private double goid;//充值金额
	@Column(name= "date",columnDefinition="timestamp NULL default CURRENT_TIMESTAMP COMMENT '充值时间'",nullable=false)
	private Date date;//充值时间
	@Column(name="type",columnDefinition="varchar(128) default null COMMENT '充值类型'")
	private String type;//充值类型
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	//referencedColumnName指定userName对应（其他）表的外键字段
	@JoinColumn(name="userName",referencedColumnName="userName",insertable=false,updatable=false)//充值客户账号
	private Customer customer;
	@Column(name="userName",updatable=true,insertable=true)
	private String userName;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public double getGold() {
		return goid;
	}
	public void setGold(double gold) {
		this.goid = gold;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
