package com.pidstudiodemo.service;

import org.springframework.data.domain.Page;

import com.pidstudiodemo.model.PayList;

/**
 * 用户管理Service
 * **/
public interface PayListService {
	//查询消费记录
	public Page<PayList> queryPayList(int page, int size);
	//插入消费记录
	public String insertPayList(PayList payList);
	//获取充值总条数
	public int countPayList();

}
