package com.exschoolapp.notificationservice.service;

import com.exschoolapp.notificationservice.dto.NotificationResponse;

import java.util.List;

public interface NotificationService {
    public List<NotificationResponse> findAllNotifications();
}
