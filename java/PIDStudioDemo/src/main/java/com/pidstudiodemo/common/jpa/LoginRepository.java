package com.pidstudiodemo.common.jpa;

import javax.persistence.Table;



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.EmployeeManage;


@Repository
@Table(name="employeemanage")
@Qualifier("LoginRepository")
public interface LoginRepository extends JpaRepository<EmployeeManage, Integer>{
	@Query(value="SELECT em FROM  EmployeeManage em WHERE em.number = :number and em.password =:password and em.employeeType.name='店长'")
	EmployeeManage findByStuNumber(@Param("number")String number,@Param("password")String password);
	//nativeQuery = true使用原生sql 查询
	@Modifying//jpa使用insert，update 必须使用 
	@Query(value="update EmployeeManage em SET em.password =:password where em.phone=:phone")
	int update(@Param("phone")String phone,@Param("password")String password);
	EmployeeManage findByPhone(String phoneNumber);
	
	@Modifying//number修改
	@Query(value="update EmployeeManage em SET em.password =:password where em.number=:number")
	int updatePassword(@Param("number")String number,@Param("password")String password);
}
