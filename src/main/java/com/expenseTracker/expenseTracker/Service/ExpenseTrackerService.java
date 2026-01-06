package com.expenseTracker.expenseTracker.Service;

import com.expenseTracker.expenseTracker.Entity.Enums.ExpenseCategory;
import com.expenseTracker.expenseTracker.Entity.ExpenseTracker;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseTrackerService {
    List<ExpenseTracker> getAllExpensesFromUserIdAndDate(Long userId, LocalDate fromDate, LocalDate toDate);
    void saveExpense(ExpenseTracker expenseTracker);
    List<ExpenseTracker> getAllExpensesFromUserId(Long userId);
    List<ExpenseTracker> getAllExpensesFromUserIdAndExpenseCategory(Long userId, ExpenseCategory expenseCategory);
    Long getExpenseAmountByUserIdAndCategory(Long userId, ExpenseCategory expenseCategory);
}
