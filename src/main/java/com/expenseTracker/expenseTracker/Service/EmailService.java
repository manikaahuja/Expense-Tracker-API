package com.expenseTracker.expenseTracker.Service;

public interface EmailService {
    void sendMail(String to, String subject, String body);
}
