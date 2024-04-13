package com.exschoolapp.notificationservice.dto;

import com.exschoolapp.notificationservice.dto.base.BaseResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponse extends BaseResponse {
    private String message;
    private String serviceName;
    private String toEmail;
    private LocalDateTime createdAt;

    public NotificationResponse() {
        super();
    }
}
