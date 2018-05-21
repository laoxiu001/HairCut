package com.pidstudiodemo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

}
