package com.expenseTracker.expenseTracker.Controller;

import com.expenseTracker.expenseTracker.Entity.ExpenseTracker;
import com.expenseTracker.expenseTracker.Service.ExpenseTrackerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseTrackerController {
    private final ExpenseTrackerService expenseTrackerService;

    public ExpenseTrackerController(ExpenseTrackerService expenseTrackerService) {
        this.expenseTrackerService = expenseTrackerService;
    }

    @GetMapping("/test")
    public String test() {
        return "App is working";
    }

    @PostMapping("/expense")
    public void createUser(@RequestBody ExpenseTracker expenseTracker) {
        expenseTrackerService.saveExpense(expenseTracker);
    }

}
