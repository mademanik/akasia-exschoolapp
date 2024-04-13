package com.exschoolapp.studentservice.helper;

import lombok.Getter;

@Getter
public enum ValidationErrors {
    SYSTEM_ERROR("Sorry, system error(s) occurred"),
    STUDENT_NOT_FOUND("Data student not found");

    private final String defaultMessage;

    ValidationErrors(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
