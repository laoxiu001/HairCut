package com.pidstudiodemo.common.jpa;

import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.Customer;
import com.pidstudiodemo.model.PayList;
@Repository
@Table(name="Paylist")
@Qualifier("paylistRepositoey")
public interface PayListRepositoey extends JpaRepository<PayList, Integer> {
	@Query(value=("SELECT p  FROM  PayList p WHERE"+
			" concat(IFNULL(p.userName,''),IFNULL(p.type,''),IFNULL(p.date,''))"
			+ "like %:conditions%"))
	List<PayList> queryConditions(@Param("conditions")String conditions);

	List<com.pidstudiodemo.model.PayList> findByUserName(String username);

}
