package com.exschoolapp.registrationservice.dto;

import lombok.Data;

@Data
public class RestResponse {
    private Object status;
    private Object message;
    private Object data;
}
