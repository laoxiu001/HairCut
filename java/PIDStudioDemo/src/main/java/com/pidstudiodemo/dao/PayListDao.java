package com.pidstudiodemo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pidstudiodemo.model.PayList;

/**
 * 充值记录Dao
 * **/
public interface PayListDao {
	//查询充值清单
	public Page<PayList> queryPayList(Pageable pageable);
	//充值
	public void insertPayList(PayList paylist);
	//统计充值的总条数
	public int PayList();
	//多字段模糊查询
	public List<PayList> queryConditions(String conditions);
	//根据客户账号查询充值记录
	public List<PayList> queryUsername(String username);

}
