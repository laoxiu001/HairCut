package com.pidstudiodemo.model;
/**
 * 工作室支出项目表
 * **/
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="Expense")
public class Expense implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;//支出编号
	@Column(name="name",columnDefinition="varchar(128)  default null COMMENT '支出项目名'")
	private String name;//支出项目名称
	@Column(name="date",columnDefinition="timestamp NULL default CURRENT_TIMESTAMP COMMENT '支出时间'",nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")//传入后台的时间格式为yyyy-MM-dd
	@Temporal(TemporalType.DATE)//改变传入前端的日期类型为yyyy-MM-dd
	private Date date;//支出时间
	@Column(name="remark",columnDefinition="varchar(128)  default null COMMENT '备注'")
	private String remark;//备注
	@Column(name = "money",columnDefinition="double default 0 COMMENT '支出金额'",nullable=false)
	private double money;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
}
