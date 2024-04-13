package com.exschoolapp.studentservice.helper;

import lombok.Getter;

@Getter
public enum ApplicationMessages {
    SYSTEM_ERROR("Sorry, something bad has occurred : %s"),
    STUDENT_CREATED("Data student successfully created"),
    STUDENT_RETRIEVED("Data student successfully retrieved"),
    STUDENT_DELETED("Data student successfully deleted"),
    STUDENT_UPDATED("Data student successfully updated"),
    STUDENT_DATA_EMPTY("Data student is empty"),
    STUDENT_NOT_FOUND("Data student not found with id : %s");

    private final String message;

    ApplicationMessages(String message) {
        this.message = message;
    }

    public String getValue(String additionalMessage) {
        return String.format(message, additionalMessage);
    }
}