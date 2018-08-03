package com.pidstudiodemo.common.jpa;

import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.EmployeeManage;
import com.pidstudiodemo.view.model.EmployeeManageSum;
@Repository
@Table(name="employeeManage")
@Qualifier("EmployeeManageRepositoey")
public interface EmployeeManageRepositoey extends JpaRepository<EmployeeManage, Integer> {
//分页查询在职员工 或离职员工
List<EmployeeManage> findByStatus(String status,Pageable pageable);
//查询详细员工信息
@Query(value="SELECT em FROM  EmployeeManage em WHERE em.id=:id")
List<EmployeeManage> queryDetailed(@Param("id")int id);
//多字段模糊查询
@Query(value=("SELECT em FROM  EmployeeManage em WHERE"+
" concat(IFNULL(em.name,''),IFNULL(em.phone,''),IFNULL(em.employeeType.name,''),IFNULL(em.number,''),IFNULL(em.sex,'')) "+
"like %:conditions% and em.status = :status"))
List<EmployeeManage> queryConditions(@Param("conditions")String conditions,@Param("status") String status);
//员工编号查询记录
EmployeeManage findByNumber(String number);
@Query("SELECT NEW com.pidstudiodemo.view.model.EmployeeManageSum(sr.employeeManage,"
		+ "sr.employeeManage.employeeType,sr,sum(sr.payavle),count(sr.number),"
		+ "(sum(sr.payavle)*sr.employeeManage.employeeType.commissionRate+"
		+ "sr.employeeManage.employeeType.basicSalary)) FROM  Record sr WHERE sr.number = :number and MONTH(sr.date) = :dateMonth")
EmployeeManageSum querySalary(@Param("number") String number,@Param("dateMonth") int dateMonth);
List<EmployeeManage> findByStatus(String status);
//	根据员工类型查询员工
List<EmployeeManage> findByTypeIdAndStatus(int id, String string);
}
