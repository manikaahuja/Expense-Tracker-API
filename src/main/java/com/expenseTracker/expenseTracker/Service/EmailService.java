package com.expenseTracker.expenseTracker.Service;

public interface EmailService {
    public void sendMail(String to, String subject, String body);
}
