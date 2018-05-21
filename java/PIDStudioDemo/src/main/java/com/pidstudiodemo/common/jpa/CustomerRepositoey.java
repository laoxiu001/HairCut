package com.pidstudiodemo.common.jpa;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.Customer;
@Repository
@Table(name="customer")
@Qualifier("CustomerRepositoey")
public interface CustomerRepositoey extends JpaRepository<Customer, Integer> {
	public Customer findByUserName(String userName);
}
