package com.pidstudiodemo.common.jpa;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.Record;

@Repository
@Table(name="Record")
@Qualifier("recordRepositoey")
public interface RecordRepositoey extends JpaRepository<Record, Integer> {

}
