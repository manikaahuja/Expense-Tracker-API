package com.expenseTracker.expenseTracker.Dao;

import com.expenseTracker.expenseTracker.Entity.ExpenseTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseTrackerDao extends JpaRepository<ExpenseTracker,Long> {

}
