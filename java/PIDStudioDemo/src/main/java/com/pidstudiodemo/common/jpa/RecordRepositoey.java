package com.pidstudiodemo.common.jpa;

import java.util.List;

import javax.persistence.Table;

import org.hibernate.annotations.Parent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.PayList;
import com.pidstudiodemo.model.Record;

@Repository
@Table(name="Record")
@Qualifier("recordRepositoey")
public interface RecordRepositoey extends JpaRepository<Record, Integer> {
	//查询查询上月有服务记录的员工工号
	@Query("SELECT r.number FROM Record r WHERE MONTH(r.date) =  :dateMonth GROUP BY r.number")
	public List<String> queryNumber(Pageable pageable,@Param("dateMonth") int dateMonth);
	//查询详细信息
	@Query("FROM Record r WHERE MONTH(r.date) =  :dateMonth AND r.number = :number")
	public List<Record> qNumber(@Param("number") String number,@Param("dateMonth") int dateMonth);
	//查询员工工号
	@Query("SELECT r.number FROM Record r GROUP BY r.number")
	public List<String> queryNumber();
	//分页查询员工服务
	@Query("FROM Record r WHERE MONTH(r.date) =  :dateMonth AND r.number = :number")
	public List<Record> pageDetails(@Param("number") String number,@Param("dateMonth") int dateMonth, Pageable pageable);
	//多字段模糊查询
	@Query(value=("SELECT r  FROM  Record r WHERE"+
			" concat(IFNULL(r.username,''),IFNULL(r.serviceItem.name,''),IFNULL(r.customer.name,''),IFNULL(r.number,'') ,IFNULL(r.date,''))"
			+ "like %:conditions%"))
	public List<Record> queryConditions(@Param("conditions") String conditions);
	//查询余额
	@Query("SELECT c.balance FROM Customer c WHERE userName=:userName")
	public double queryDalance(@Param("userName")String userName);
	//查询使用的服务
	@Query("SELECT r.serviceItem.name FROM Record r Group by r.service")
	public List<String> queryService();
	//根据用户查询消费记录
	public List<Record> findByUsername(String username);
	
}
