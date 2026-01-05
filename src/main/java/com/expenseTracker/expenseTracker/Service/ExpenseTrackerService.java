package com.expenseTracker.expenseTracker.Service;

import com.expenseTracker.expenseTracker.Entity.ExpenseTracker;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseTrackerService {
    List<ExpenseTracker> getAllExpensesFromUserIdAndDate(Long userId, LocalDate fromDate, LocalDate toDate);
    void saveExpense(ExpenseTracker expenseTracker);
}
