package com.pidstudiodemo.common.jpa;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.EmployeePositionBackup;
@Repository
@Table(name="employeePositionBackup")
@Qualifier("EmployeePositionBackupR")
public interface EmployeePositionBackupR extends JpaRepository<EmployeePositionBackup, Integer> {

}
