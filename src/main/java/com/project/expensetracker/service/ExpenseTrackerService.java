package com.project.expensetracker.service;

import com.project.expensetracker.entity.enums.ExpenseCategory;
import com.project.expensetracker.entity.ExpenseTracker;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseTrackerService {
    List<ExpenseTracker> getAllExpensesFromUserIdAndDate(Long userId, LocalDate fromDate, LocalDate toDate);
    void saveExpense(ExpenseTracker expenseTracker);
    List<ExpenseTracker> getAllExpensesFromUserId(Long userId);
    List<ExpenseTracker> getAllExpensesFromUserIdAndExpenseCategory(Long userId, ExpenseCategory expenseCategory);
    Long getExpenseAmountByUserIdAndCategory(Long userId, ExpenseCategory expenseCategory);
    String deleteExpenseDetails(Long id);
}
