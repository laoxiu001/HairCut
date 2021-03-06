package com.pidstudiodemo.common.jpa;

import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.ServiceItem;

@Repository
@Table(name="ServiceItem")
@Qualifier(value="serviceItemRepositoey")
public interface ServiceItemRepositoey extends JpaRepository<ServiceItem, Integer> {
	//根据id查询返回对应记录
	public ServiceItem findById(int id);
	//查询在用的服务
	public List<ServiceItem> findByStatus(boolean Status);
	//分页查询
	public List<ServiceItem> findByStatus(boolean status, Pageable pageable);
	//迷糊查询
	@Query(value="SELECT si FROM  ServiceItem si WHERE"+
" name like %:conditions% and si.status = :status")
	public List<ServiceItem> queryConditions(@Param("conditions")String conditions,@Param("status") boolean status);

}
