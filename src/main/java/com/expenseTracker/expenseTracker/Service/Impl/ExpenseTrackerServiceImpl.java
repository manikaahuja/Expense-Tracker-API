package com.expenseTracker.expenseTracker.Service.Impl;

import com.expenseTracker.expenseTracker.Dao.ExpenseTrackerDao;
import com.expenseTracker.expenseTracker.Dao.UserDetailsDao;
import com.expenseTracker.expenseTracker.Entity.Enums.ExpenseCategory;
import com.expenseTracker.expenseTracker.Entity.ExpenseTracker;
import com.expenseTracker.expenseTracker.Service.ExpenseTrackerService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseTrackerServiceImpl implements ExpenseTrackerService {

    private final ExpenseTrackerDao expenseTrackerDao;
    private final UserDetailsDao userDetailsDao;

    public ExpenseTrackerServiceImpl(ExpenseTrackerDao expenseTrackerDao, UserDetailsDao userDetailsDao) {
        this.expenseTrackerDao = expenseTrackerDao;
        this.userDetailsDao = userDetailsDao;
    }

    @Override
    public List<ExpenseTracker> getAllExpensesFromUserIdAndDate(Long userId, LocalDate fromDate, LocalDate toDate) {
        Optional<ExpenseTracker> expenseTrackerList = expenseTrackerDao.findById(userId);
        Timestamp timestamp = userDetailsDao.findById(userId).get().getLastSentMail();
        LocalDate today = LocalDate.now();
        LocalDate lastSentDate = timestamp
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        if(expenseTrackerList.isPresent()) {
            List<ExpenseTracker> filteredExpenses = expenseTrackerList.stream().filter(expense -> {
                        LocalDate createdDate = expense.getCreatedDate()
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();

                        return (createdDate.isAfter(lastSentDate)
                                && !createdDate.isAfter(today));
                    })
                    .toList();
            return filteredExpenses;
        }
        return List.of();
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
}
