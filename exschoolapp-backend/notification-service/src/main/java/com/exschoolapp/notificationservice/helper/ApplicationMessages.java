package com.exschoolapp.notificationservice.helper;

import lombok.Getter;

@Getter
public enum ApplicationMessages {
    SYSTEM_ERROR("Sorry, something bad has occurred : %s"),
    NOTIFICATION_CREATED("Data notification successfully created"),
    NOTIFICATION_RETRIEVED("Data notification successfully retrieved"),
    NOTIFICATION_DELETED("Data notification successfully deleted"),
    NOTIFICATION_UPDATED("Data notification successfully updated"),
    NOTIFICATION_DATA_EMPTY("Data notification is empty"),
    NOTIFICATION_NOT_FOUND("Data notification not found with id : %s");

    private final String message;

    ApplicationMessages(String message) {
        this.message = message;
    }

    public String getValue(String additionalMessage) {
        return String.format(message, additionalMessage);
    }
}