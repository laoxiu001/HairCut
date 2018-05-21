package com.pidstudiodemo.service;

import org.springframework.data.domain.Page;

import com.pidstudiodemo.model.Record;

/**
 * 用户消费记录Service
 * **/
public interface RecordService {
	//查询
	public Page<Record> queryRecord(int page, int size);
	//添加记录
	public String insertRecord(Record record, int[] serviceIemId,String[] number );
	//消费记录总条数
	public int countRecord();

}
