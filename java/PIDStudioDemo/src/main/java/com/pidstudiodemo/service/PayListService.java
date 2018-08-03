package com.pidstudiodemo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;

import com.pidstudiodemo.model.PayList;

/**
 * 用户管理Service
 * **/
public interface PayListService {
	//查询消费记录
	public Page<PayList> queryPayList(int page, int size);
	//插入消费记录
	public String insertPayList(PayList payList, HttpSession session);
	//获取充值总条数
	public int countPayList();
	//最大页数
	public int queryMaxPage(int page, int size);
	//多字段模糊查询
	public List<PayList> queryConditions(String conditions);

}
