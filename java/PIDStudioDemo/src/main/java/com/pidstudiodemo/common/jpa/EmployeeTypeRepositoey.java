 package com.pidstudiodemo.common.jpa;

import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.EmployeeType;
@Repository
@Table(name="employeeType")
@Qualifier("EmployeeTypeRepositoey")
public interface EmployeeTypeRepositoey extends JpaRepository<EmployeeType, Integer>{
	//根据状态查询
	List<EmployeeType> findByStatus(int status);
	//根据id查询
	EmployeeType findById(int id);
	//根据状态分页查询
	List<EmployeeType> findByStatus(int status, Pageable pageable);
	//多字段模糊查询
	@Query(value=("SELECT et  FROM  EmployeeType et WHERE"+
			" concat(IFNULL(et.name,''))"
			+ "like %:conditions% and status = 1"))
	List<EmployeeType> queryConditions(@Param("conditions") String conditions);
}
