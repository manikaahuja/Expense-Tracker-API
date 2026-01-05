package com.expenseTracker.expenseTracker.Scheduler;

import com.expenseTracker.expenseTracker.Entity.ExpenseTracker;
import com.expenseTracker.expenseTracker.Entity.UserDetails;
import com.expenseTracker.expenseTracker.Service.EmailService;
import com.expenseTracker.expenseTracker.Service.ExpenseTrackerService;
import com.expenseTracker.expenseTracker.Service.UserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component
@Slf4j
public class WeeklyMailScheduler {

    private final UserDetailsService userDetailsService;
    private final ExpenseTrackerService expenseTrackerService;
    private final EmailService emailService;

    public WeeklyMailScheduler(
            UserDetailsService userDetailsService,
            ExpenseTrackerService expenseTrackerService,
            EmailService emailService) {
        this.userDetailsService = userDetailsService;
        this.expenseTrackerService = expenseTrackerService;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void sendWeeklyExpenseEmail() {
        log.info("Weekly scheduler started");
        List<UserDetails> users = userDetailsService.getAllUserDetails();

        for (UserDetails user : users) {
            processUser(user);
        }
    }

    private void processUser(UserDetails user) {

        Timestamp lastSent = user.getLastSentMail();
        LocalDate startDate = lastSent == null
                ? LocalDate.now().minusWeeks(1)
                : lastSent.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate endDate = LocalDate.now();

        List<ExpenseTracker> expenses =
                expenseTrackerService.getAllExpensesFromUserIdAndDate(
                        user.getUserId(), startDate, endDate);

        if (expenses.isEmpty()) {
            return;
        }

        String emailBody = buildWeeklySummary(expenses);

        emailService.sendMail(
                user.getUserMail(),
                "Your Weekly Expense Summary",
                emailBody
        );

        userDetailsService.updateLastSentDate(user.getUserId());
    }

    private String buildWeeklySummary(List<ExpenseTracker> expenses) {
        double total = expenses.stream()
                .mapToDouble(ExpenseTracker::getExpenseAmount)
                .sum();

        return "Your total expense this week is ₹" + total;
    }

}
