package com.exschoolapp.notificationservice.controller;

import com.exschoolapp.notificationservice.dto.NotificationResponse;
import com.exschoolapp.notificationservice.helper.ApplicationMessages;
import com.exschoolapp.notificationservice.helper.ResponseHandler;
import com.exschoolapp.notificationservice.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationServiceImpl notificationService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() {
        List<NotificationResponse> responses = notificationService.findAllNotifications();
        return ResponseHandler.generateResponse(!responses.isEmpty() ? ApplicationMessages.NOTIFICATION_RETRIEVED.getMessage() :
                ApplicationMessages.NOTIFICATION_DATA_EMPTY.getMessage(), HttpStatus.OK, responses);
    }
}
