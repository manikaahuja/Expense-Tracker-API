package com.project.expensetracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Table(name = "user_details")
@Entity
@Data
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_birthdate")
    private LocalDate userBirthDate;

    @Column(name="user_mail")
    private String userMail;

    @Column(name="created_date")
    private Timestamp createdDate;

    @Column(name="last_sent_mail")
    private Timestamp lastSentMail;
}
