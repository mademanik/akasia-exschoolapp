package com.exschoolapp.notificationservice.helper;

import lombok.Getter;

@Getter
public enum ValidationErrors {
    SYSTEM_ERROR("Sorry, system error(s) occurred"),
    NOTIFICATION_NOT_FOUND("Data notification not found");

    private final String defaultMessage;

    ValidationErrors(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
