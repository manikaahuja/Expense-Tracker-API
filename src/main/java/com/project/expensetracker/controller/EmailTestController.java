package com.project.expensetracker.controller;

import com.project.expensetracker.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
public class EmailTestController {

    @Value(("${test.receiver.mail}"))
    private String receiverMail;

    private final EmailService emailService;

    public EmailTestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send")
    public String sendTestEmail() {

        emailService.sendMail(
                receiverMail,
                "Spring Boot Email Test",
                "If you received this email, SMTP setup is successful."
        );

        return "Email trigger executed successfully";
    }
}

