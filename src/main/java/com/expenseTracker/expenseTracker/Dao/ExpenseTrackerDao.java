package com.expenseTracker.expenseTracker.Dao;

import com.expenseTracker.expenseTracker.Entity.Enums.ExpenseCategory;
import com.expenseTracker.expenseTracker.Entity.ExpenseTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ExpenseTrackerDao extends JpaRepository<ExpenseTracker,Long> {

    List<ExpenseTracker> findByUserId(Long userId);
    List<ExpenseTracker> findByUserIdAndExpenseCategory(Long userId, ExpenseCategory expenseCategory);

    @Query("select sum(e.expenseAmount) from ExpenseTracker e where e.userId = :userId and e.expenseCategory = :category")
    Long findExpenseAmountByUserIdAndExpenseCategory(Long userId, ExpenseCategory category);

    List<ExpenseTracker> findByUserIdAndCreatedDateBetween(Long userId, Timestamp fromDate, Timestamp toDate);
}
