package com.pidstudiodemo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="salary")
public class Salary {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name ="id")
	private int id ;
	@Column(name= "number",columnDefinition="varchar(128)  default null COMMENT '员工工号'")
	private String number;//员工工号
	@Column(name= "name",columnDefinition="varchar(128)  default null COMMENT '员工姓名'")
	private String name;//员工姓名
	@Column(name="count",columnDefinition="int default null COMMENT '服务数'")
	private int count;//服务数量
	@Column(name = "basicSalary",columnDefinition="double default 0 COMMENT '基本工资'",nullable=false)
	private double basicSalary;//基本工资
	@Column(name = "commissionRate",columnDefinition="double default 0 COMMENT '提成比例'",nullable=false)
	private double commissionRate;//提成比例
	@Column(name = "WeightingFactor",columnDefinition="double default 0 COMMENT '加权系数'",nullable=false)
	private double WeightingFactor;//加权系数
	@Column(name="salary",columnDefinition="double default 0 COMMENT '应付工资'",nullable=false)
	private double salary;
	@Column(name= "date")
	@DateTimeFormat(pattern="yyyy-MM")
	private Date date;//记录产生时间
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}
	public double getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}
	public double getWeightingFactor() {
		return WeightingFactor;
	}
	public void setWeightingFactor(double weightingFactor) {
		WeightingFactor = weightingFactor;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}
