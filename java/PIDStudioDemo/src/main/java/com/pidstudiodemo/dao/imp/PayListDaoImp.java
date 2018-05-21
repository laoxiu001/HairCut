package com.pidstudiodemo.dao.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pidstudiodemo.common.jpa.PayListRepositoey;
import com.pidstudiodemo.dao.PayListDao;
import com.pidstudiodemo.model.PayList;
@Repository(value="payListDao")
public class PayListDaoImp implements PayListDao {
	@Autowired
	PayListRepositoey payListRepositoey;
	public Page<PayList> queryPayList(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<PayList> pagePayList = payListRepositoey.findAll(pageable);
		return pagePayList;
	}
	//添加
	@Transactional
	public void insertPayList(PayList paylist) {
		// TODO Auto-generated method stub
		payListRepositoey.save(paylist);
	}
	//统计总条数
	public int PayList() {
		// TODO Auto-generated method stub
		return (int)payListRepositoey.count();
	}

}
