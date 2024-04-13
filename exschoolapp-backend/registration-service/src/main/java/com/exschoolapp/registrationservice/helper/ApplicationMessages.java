package com.exschoolapp.registrationservice.helper;

import lombok.Getter;

@Getter
public enum ApplicationMessages {
    SYSTEM_ERROR("Sorry, something bad has occurred : %s"),
    REGISTRATION_CREATED("Registration successfully created"),
    REGISTRATION_RETRIEVED("Data Registration successfully retrieved"),
    REGISTRATION_DATA_EMPTY("Data Registration is empty"),
    REGISTRATION_NOT_FOUND("Data Registration not found"),
    REGISTRATION_DELETED("Data Registration successfully deleted"),
    REGISTRATION_UPDATED("Data Registration successfully updated"),
    FEIGN_ERROR("%s");

    private final String message;

    ApplicationMessages(String message) {
        this.message = message;
    }

    public String getValue(String additionalMessage) {
        return String.format(message, additionalMessage);
    }
}