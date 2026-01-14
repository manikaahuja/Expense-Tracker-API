package com.project.expensetracker.entity.enums;

import lombok.Getter;

@Getter
public enum ExpenseCategory {
    FOOD("Food"),
    TRANSPORT("Transport"),
    RENT("Rent"),
    UTILITIES("Utilities"),
    ENTERTAINMENT("Entertainment"),
    SHOPPING("Shopping"),
    HEALTH("Health"),
    TRAVEL("Travel"),
    EDUCATION("Education"),
    OTHER("Other");

    private final String displayName;

    ExpenseCategory(String displayName) {
        this.displayName = displayName;
    }

}
