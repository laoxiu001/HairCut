package com.pidstudiodemo.common.jpa;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.Expense;
@Repository
@Table(name="expense")
@Qualifier("expenseRepositoey")
public interface ExpenseRepositoey extends JpaRepository<Expense, Integer> {

}
