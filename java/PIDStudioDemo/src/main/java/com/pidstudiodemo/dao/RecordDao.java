package com.pidstudiodemo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pidstudiodemo.model.PayList;
import com.pidstudiodemo.model.Record;
/**
 * 消费记录*/
public interface RecordDao {
	//分页查询
	public Page<Record> queryRecord(Pageable pageable);
	//添加用户消费记录
	public void insertRecord(Record record);
	//统计消费记录总条数
	public int countRecord();
	//查询所有有服务记录的员工工号
	public List<String> queryNumber();
	//分页查询服务记录员工的工号
	public List<String> queryNumber(Pageable pageable,int dateMonth);
	//查询员工的详细服务信息
	public List<Record> querySalaryDetails(String number,int dateMonth);
	//员工工号总数
	public int countNumber();
	//员工服务的总数
	public int countSalaryDetails(String number,int dateMonth);
	//分页查询员工详情
	public List<Record> querySalaryDetails(String number, Pageable pageable,int dateMonth);
	//多字段模糊查询
	public List<Record> queryConditions(String conditions);
	//查询余额
	public double queryDalance(String userName);
	//查询使用的服务项目
	public List<String> queryService();
	//查询对应客户的消费记录
	public List<Record> queryUsername(String username);

}
