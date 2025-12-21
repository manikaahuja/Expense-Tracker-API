package com.expenseTracker.expenseTracker.Entity;

import com.expenseTracker.expenseTracker.Entity.Enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "expense_tracker")
@Entity
@Data
public class ExpenseTracker {

    @Id
    @Column(name = "id")
    private Long Id;

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
