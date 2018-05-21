package com.pidstudiodemo.dao.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Repository;


import com.pidstudiodemo.common.jpa.RecordRepositoey;
import com.pidstudiodemo.dao.RecordDao;
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

}
