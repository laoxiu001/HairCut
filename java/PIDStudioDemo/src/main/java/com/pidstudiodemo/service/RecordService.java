package com.pidstudiodemo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;

import com.pidstudiodemo.model.EmployeeManage;
import com.pidstudiodemo.model.PayList;
import com.pidstudiodemo.model.Record;

/**
 * 用户消费记录Service
 * **/
public interface RecordService {
	//查询
	public Page<Record> queryRecord(int page, int size);
	//添加记录
	public String insertRecord(Record record, int[] serviceIemId,String[] number ,HttpSession session);
	//消费记录总条数
	public int countRecord();
	//最大页数
	public int queryMaxPage(int page, int size);
	//多字段模糊查询
	public List<Record> queryConditions(String conditions);
	//查询余额
	public double queryDalance(String userName);
	//查询使用的服务类型
	public List<String> queryService();
	//查询员工工号
	public String queryNumber();
	//查询该服务可用员工
	public List<EmployeeManage> queryEmployee(int typrId);

}
