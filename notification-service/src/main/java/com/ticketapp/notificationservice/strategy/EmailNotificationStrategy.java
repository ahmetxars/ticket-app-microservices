package com.ticketapp.notificationservice.strategy;

import com.ticketapp.notificationservice.domain.Notification;
import com.ticketapp.notificationservice.domain.NotificationType;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationStrategy implements NotificationStrategy {

    @Override
    public NotificationType getNotificationType() {
        return NotificationType.EMAIL;
    }

    @Override
    public void send(Notification notification) {
        System.out.println("Sending EMAIL to: " + notification.getRecipient());
    }
}