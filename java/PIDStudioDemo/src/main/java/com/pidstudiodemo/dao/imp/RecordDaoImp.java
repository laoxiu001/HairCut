package com.pidstudiodemo.dao.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;




import com.pidstudiodemo.common.jpa.RecordRepositoey;
import com.pidstudiodemo.dao.RecordDao;
import com.pidstudiodemo.model.PayList;
import com.pidstudiodemo.model.Record;
@Repository(value="recordDao")
public class RecordDaoImp implements RecordDao {
	@Autowired
	private RecordRepositoey recordRepositoey;

	public Page<Record> queryRecord(Pageable pageable) {
		// TODO Auto-generated method stub
		return recordRepositoey.findAll(pageable);
	}
	
	public void insertRecord(Record record) {
		// TODO Auto-generated method stub
		recordRepositoey.save(record);
	}

	public int countRecord() {
		// TODO Auto-generated method stub
		return (int)recordRepositoey.count();
	}

	public List<String> queryNumber(Pageable pageable,int dateMonth) {
		// TODO Auto-generated method stub
		return recordRepositoey.queryNumber(pageable,dateMonth);
	}

	public List<Record> querySalaryDetails(String number,int dateMonth) {
		// TODO Auto-generated method stub
		return recordRepositoey.qNumber(number,dateMonth);
	}

	public int countNumber() {
		// TODO Auto-generated method stub
		return recordRepositoey.queryNumber().size();
	}

	public List<String> queryNumber() {
		// TODO Auto-generated method stub
		return recordRepositoey.queryNumber();
	}

	public int countSalaryDetails(String number,int dateMonth) {
		// TODO Auto-generated method stub
		return recordRepositoey.qNumber(number,dateMonth).size();
	}

	public List<Record> querySalaryDetails(String number, Pageable pageable, int dateMonth) {
		// TODO Auto-generated method stub
		return recordRepositoey.pageDetails(number,dateMonth,pageable);
	}

	public List<Record> queryConditions(String conditions) {
		// TODO Auto-generated method stub
		return recordRepositoey.queryConditions(conditions);
	}

	public double queryDalance(String userName) {
		// TODO Auto-generated method stub
		return recordRepositoey.queryDalance(userName);
	}
	//查询使用的服务项目
	public List<String> queryService() {
		// TODO Auto-generated method stub
		return recordRepositoey.queryService();
	}

	public List<Record> queryUsername(String username) {
		// TODO Auto-generated method stub
		return recordRepositoey.findByUsername( username);
	}

}
