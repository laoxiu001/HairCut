package com.pidstudiodemo.common.jpa;


import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.Customer;
import com.pidstudiodemo.model.EmployeeManage;
@Repository
@Table(name="customer")
@Qualifier("CustomerRepositoey")
public interface CustomerRepositoey extends JpaRepository<Customer, Integer> {
	//findBy+实体类的变量名首字母大写+连接语句（and 或者or）+变量名首字母大写
	public Customer findByUserNameOrPhone(String userName,String phone);
	//多字段模糊查询
	@Query(value=("SELECT c  FROM  Customer c WHERE"+
			" concat(IFNULL(c.name,''),IFNULL(c.phone,''),IFNULL(c.status,''),IFNULL(c.userName,'') ,IFNULL(c.status,''))"
			+ "like %:conditions%"))
	List<Customer> queryConditions(@Param("conditions")String conditions);
	//id查询用户
	public Customer findById(int id);
}
