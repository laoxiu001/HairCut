package com.pidstudiodemo.common.jpa;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.PayList;
@Repository
@Table(name="Paylist")
@Qualifier("paylistRepositoey")
public interface PayListRepositoey extends JpaRepository<PayList, Integer> {

}
