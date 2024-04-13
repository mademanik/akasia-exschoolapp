package com.exschoolapp.registrationservice.helper;

import lombok.Getter;

@Getter
public enum ValidationErrors {
    SYSTEM_ERROR("Sorry, system error(s) occurred"),
    STUDENT_NOT_FOUND("Data student not found"),
    EXTRACURRICULAR_NOT_FOUND("Data extracurricular not found"),
    REGISTRATION_NOT_STARTED("Registration date has not yet started at this time"),
    REGISTRATION_CLOSED("Registration has closed at this time"),
    REGISTRATION_QUOTA_FULL("Sorry, the member registration quota is full"),
    REGISTRATION_NOT_FOUND("Data registration not found"),
    REGISTRATION_ALREADY_EXIST("Data registration already exists");

    private final String defaultMessage;

    ValidationErrors(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
