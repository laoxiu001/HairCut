package com.pidstudiodemo.common.jpa;

import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pidstudiodemo.model.Expense;
@Repository
@Table(name="expense")
@Qualifier("expenseRepositoey")
public interface ExpenseRepositoey extends JpaRepository<Expense, Integer> {
	@Query(value="select Sum(e.money) from Expense e")
	double querySumMoney();
	//多字段模糊查询
	@Query(value=("SELECT e  FROM  Expense e WHERE"+
			" concat(IFNULL(e.name,''),IFNULL(e.date,''))"
			+ "like %:conditions%"))
	List<Expense> queryConditions(@Param("conditions")String conditions);

}
