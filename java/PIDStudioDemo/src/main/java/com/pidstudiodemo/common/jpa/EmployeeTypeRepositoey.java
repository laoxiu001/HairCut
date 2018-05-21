 package com.pidstudiodemo.common.jpa;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.EmployeeType;
@Repository
@Table(name="employeeType")
@Qualifier("EmployeeTypeRepositoey")
public interface EmployeeTypeRepositoey extends JpaRepository<EmployeeType, Integer>{
}
