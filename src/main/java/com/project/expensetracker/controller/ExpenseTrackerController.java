package com.project.expensetracker.controller;

import com.project.expensetracker.entity.enums.ExpenseCategory;
import com.project.expensetracker.entity.ExpenseTracker;
import com.project.expensetracker.service.ExpenseTrackerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseTrackerController {
    private final ExpenseTrackerService expenseTrackerService;

    public ExpenseTrackerController(ExpenseTrackerService expenseTrackerService) {
        this.expenseTrackerService = expenseTrackerService;
    }

    @PostMapping("/addExpense")
    public void createUser(@RequestBody ExpenseTracker expenseTracker) {
        expenseTrackerService.saveExpense(expenseTracker);
    }

    @GetMapping("/{userId}/expense")
    public List<ExpenseTracker> getAllExpensesFromUserId(@PathVariable Long userId) {
        return expenseTrackerService.getAllExpensesFromUserId(userId);
    }

    @GetMapping("/{userId}/{category}/expense")
    public List<ExpenseTracker> getAllExpensesFromCategory(@PathVariable Long userId, @PathVariable ExpenseCategory category) {
        return expenseTrackerService.getAllExpensesFromUserIdAndExpenseCategory(userId, category);
    }

    @GetMapping("/{userId}/{category}/expense/amount")
    public Long getExpenseAmountByUserIdAndCategory(@PathVariable Long userId, @PathVariable ExpenseCategory category) {
        return expenseTrackerService.getExpenseAmountByUserIdAndCategory(userId, category);
    }

    @DeleteMapping("/{id}/delete")
    public String deleteExpense(@PathVariable Long id) {
        return expenseTrackerService.deleteExpenseDetails(id);
    }
}
