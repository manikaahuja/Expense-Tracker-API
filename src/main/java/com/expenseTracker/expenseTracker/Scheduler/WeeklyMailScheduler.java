package com.expenseTracker.expenseTracker.Scheduler;

import com.expenseTracker.expenseTracker.Entity.Enums.ExpenseCategory;
import com.expenseTracker.expenseTracker.Entity.ExpenseTracker;
import com.expenseTracker.expenseTracker.Entity.UserDetails;
import com.expenseTracker.expenseTracker.Service.EmailService;
import com.expenseTracker.expenseTracker.Service.ExpenseTrackerService;
import com.expenseTracker.expenseTracker.Service.UserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    // @Scheduled(cron = "0 * * * * *") every minute for testing
    @Scheduled(cron = "0 0 9 ? * MON")
    public void sendWeeklyExpenseEmail() {

        log.info("Weekly expense scheduler started");

        List<UserDetails> users = userDetailsService.getAllUserDetails();

        for (UserDetails user : users) {
            try {
                processUserWeekly(user);
            } catch (Exception ex) {
                log.error("Failed to send weekly email for user {}", user.getUserId(), ex);
            }
        }
    }

    private void processUserWeekly(UserDetails user) {

        LocalDate today = LocalDate.now();

        // Previous full week: Monday to Sunday
        LocalDate startDate = today.minusWeeks(1).with(DayOfWeek.MONDAY);
        LocalDate endDate = startDate.plusDays(6);

        // Get expenses between Monday and Sunday of that week
        List<ExpenseTracker> expenses =
                expenseTrackerService.getAllExpensesFromUserIdAndDate(
                        user.getUserId(),
                        startDate,
                        endDate
                );

        if (expenses.isEmpty()) {
            log.info("No expenses found for user {} for week {} to {}",
                    user.getUserId(), startDate, endDate);
            return;
        }

        String emailBody = buildWeeklySummary(expenses);

        emailService.sendMail(
                user.getUserMail(),
                "Your Weekly Expense Summary (" + startDate + " to " + endDate + ")",
                emailBody
        );

        // Update last_sent_mail for user
        userDetailsService.updateLastSentDate(user.getUserId());

        log.info("Weekly email sent to user {}", user.getUserId());
    }

    private String buildWeeklySummary(List<ExpenseTracker> expenses) {

        // Create a Map of category and expenseAmount
        // Example: FOOD, 500
        Map<ExpenseCategory, Long> categoryTotals =
                expenses.stream()
                        .collect(Collectors.groupingBy(
                                ExpenseTracker::getExpenseCategory,
                                Collectors.summingLong(ExpenseTracker::getExpenseAmount)
                        ));

        // Get total expense amount
        long grandTotal = categoryTotals.values()
                .stream()
                .mapToLong(Long::longValue)
                .sum();

        StringBuilder body = new StringBuilder();
        body.append("Your expense summary for last week:\n\n");

        // Loop through Map and build expense by category
        for (Map.Entry<ExpenseCategory, Long> entry : categoryTotals.entrySet()) {
            body.append(entry.getKey())
                    .append(": ₹")
                    .append(entry.getValue())
                    .append("\n");
        }

        body.append("\nTotal: ₹").append(grandTotal);

        return body.toString();
    }

}
