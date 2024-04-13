package com.exschoolapp.notificationservice.service;

import com.exschoolapp.notificationservice.NotificationRepository;
import com.exschoolapp.notificationservice.constant.AppConstants;
import com.exschoolapp.notificationservice.dto.NotificationRequest;
import com.exschoolapp.notificationservice.dto.NotificationResponse;
import com.exschoolapp.notificationservice.mapper.NotificationMapper;
import com.exschoolapp.notificationservice.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationMapper notificationMapper;

    @KafkaListener(topics = AppConstants.TOPIC_NAME)
    public void init(NotificationRequest notificationRequest) {
        log.info("Get incoming messages from producer with topic {}", AppConstants.TOPIC_NAME);
        Notification notification = Notification.builder()
                .message(notificationRequest.getMessage())
                .serviceName(notificationRequest.getServiceName())
                .toEmail(notificationRequest.getToEmail())
                .createdAt(notificationRequest.getCreatedAt())
                .build();
        notificationRepository.save(notification);
        //send email
        this.sendEmail(notification.getToEmail(),
                "Extracurricular Registration Successfully", notification.getMessage());
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("maniksmtp@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Override
    public List<NotificationResponse> findAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(notification -> notificationMapper.mapToNotificationResponse(notification))
                .collect(Collectors.toList());
    }
}
