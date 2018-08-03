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
 * 员工类型管理表
 * */
@Entity
@Table(name ="EmployeeType")
public class EmployeeType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id ")
	private int id ;//员工类型编号
	@Column(name = "name ",columnDefinition="varchar(128)  default null COMMENT '类型名称'")
	private String name;//类型名称
	@Column(name = "basicSalary",columnDefinition="double default 0 COMMENT '基本工资'",nullable=false)
	private double basicSalary;//基本工资
	@Column(name = "commissionRate",columnDefinition="double default 0 COMMENT '提成比例'",nullable=false)
	private double commissionRate;//提成比例
	@Column(name = "WeightingFactor",columnDefinition="double default 0 COMMENT '加权系数'",nullable=false)
	private double WeightingFactor;//加权系数
	@Column(name ="status",columnDefinition="int default 1 COMMENT '状态'",nullable=false)
	private int status;
	//mappedBy指多方的外键字段对应成员变量(类变量)，cascade配置相关对象的级联关系，fetch指定加载策略（急加载还是懒加载）
	@OneToMany( mappedBy="employeeType",cascade={CascadeType.ALL,CascadeType.REMOVE} , fetch = FetchType.LAZY)
	private Set<EmployeeManage> setEmployeeManage = new HashSet<EmployeeManage>();//一对多：关联查询查询setEmployeeManage
	public Set<EmployeeManage> getSetEmployeeManage() {
		return setEmployeeManage;
	}
	public void setSetEmployeeManage(Set<EmployeeManage> setEmployeeManage) {
		this.setEmployeeManage = setEmployeeManage;
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
	public void setName(String name) {
		this.name = name;
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
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getWeightingFactor() {
		return WeightingFactor;
	}
	public void setWeightingFactor(double weightingFactor) {
		WeightingFactor = weightingFactor;
	}


}
