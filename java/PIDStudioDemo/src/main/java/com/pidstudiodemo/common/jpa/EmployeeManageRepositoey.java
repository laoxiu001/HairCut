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
@Query(value=("SELECT * FROM  employee_manage em WHERE"+
" concat(IFNULL(name,''),IFNULL(phone,''),IFNULL(number,''),IFNULL(sex,'')) "+
"like %:conditions% "),nativeQuery = true)
List<EmployeeManage> queryConditions(@Param("conditions")String conditions);
//员工编号查询记录
EmployeeManage findByNumber(String number);
}
