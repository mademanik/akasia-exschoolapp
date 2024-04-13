package com.exschoolapp.notificationservice.mapper;

import com.exschoolapp.notificationservice.dto.NotificationRequest;
import com.exschoolapp.notificationservice.dto.NotificationResponse;
import com.exschoolapp.notificationservice.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Notification mapToNotification(NotificationRequest notificationRequest);

    NotificationResponse mapToNotificationResponse(Notification notification);

    void mapUpdateNotification(NotificationRequest notificationRequest, @MappingTarget Notification notification);
}
