package com.project.expensetracker.service.impl;

import com.project.expensetracker.dao.ExpenseTrackerDao;
import com.project.expensetracker.entity.enums.ExpenseCategory;
import com.project.expensetracker.entity.ExpenseTracker;
import com.project.expensetracker.service.ExpenseTrackerService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseTrackerServiceImpl implements ExpenseTrackerService {

    private final ExpenseTrackerDao expenseTrackerDao;

    public ExpenseTrackerServiceImpl(ExpenseTrackerDao expenseTrackerDao) {
        this.expenseTrackerDao = expenseTrackerDao;
    }

    @Override
    public List<ExpenseTracker> getAllExpensesFromUserIdAndDate(
            Long userId,
            LocalDate fromDate,
            LocalDate toDate) {

        Timestamp fromTs = Timestamp.valueOf(fromDate.atStartOfDay());
        Timestamp toTs = Timestamp.valueOf(toDate.plusDays(1).atStartOfDay());

        return expenseTrackerDao.findByUserIdAndCreatedDateBetween(
                userId,
                fromTs,
                toTs
        );
    }


    @Override
    public void saveExpense(ExpenseTracker expenseTracker) {
        expenseTrackerDao.save(expenseTracker);
    }

    @Override
    public List<ExpenseTracker> getAllExpensesFromUserId(Long userId) {
        return expenseTrackerDao.findByUserId(userId);
    }

    @Override
    public List<ExpenseTracker> getAllExpensesFromUserIdAndExpenseCategory(Long userId, ExpenseCategory expenseCategory) {
        return expenseTrackerDao.findByUserIdAndExpenseCategory(userId, expenseCategory);
    }

    @Override
    public Long getExpenseAmountByUserIdAndCategory(Long userId, ExpenseCategory expenseCategory) {
        return expenseTrackerDao.findExpenseAmountByUserIdAndExpenseCategory(userId, expenseCategory);
    }

    @Override
    public String deleteExpenseDetails(Long id) {
        expenseTrackerDao.deleteById(id);
        return "Expense with id : " + id + " was deleted successfully";
    }
}
