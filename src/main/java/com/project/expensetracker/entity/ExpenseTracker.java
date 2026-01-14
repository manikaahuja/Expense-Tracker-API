package com.project.expensetracker.entity;

import com.project.expensetracker.entity.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "expense_tracker")
@Entity
@Data
public class ExpenseTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="expense_category")
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    @Column(name="expense_amount")
    private Long expenseAmount;

    @Column(name="created_date")
    private Timestamp createdDate;

    @Column(name="updated_date")
    private Timestamp updatedDate;

}
