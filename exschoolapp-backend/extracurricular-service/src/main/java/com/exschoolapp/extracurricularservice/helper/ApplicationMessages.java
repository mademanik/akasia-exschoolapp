package com.exschoolapp.extracurricularservice.helper;

import lombok.Getter;

@Getter
public enum ApplicationMessages {
    SYSTEM_ERROR("Sorry, something bad has occurred : %s"),
    EXTRACURRICULAR_CREATED("Data extracurricular successfully created"),
    EXTRACURRICULAR_RETRIEVED("Data extracurricular successfully retrieved"),
    EXTRACURRICULAR_DELETED("Data extracurricular successfully deleted"),
    EXTRACURRICULAR_UPDATED("Data extracurricular successfully updated"),
    EXTRACURRICULAR_DATA_EMPTY("Data extracurricular is empty"),
    EXTRACURRICULAR_NOT_FOUND("Data extracurricular not found with id : %s"),

    MENTOR_CREATED("Data mentor successfully created"),
    MENTOR_RETRIEVED("Data mentor successfully retrieved"),
    MENTOR_DELETED("Data mentor successfully deleted"),
    MENTOR_UPDATED("Data mentor successfully updated"),
    MENTOR_DATA_EMPTY("Data mentor is empty"),
    MENTOR_NOT_FOUND("Data mentor not found with id : %s");

    private final String message;

    ApplicationMessages(String message) {
        this.message = message;
    }

    public String getValue(String additionalMessage) {
        return String.format(message, additionalMessage);
    }
}