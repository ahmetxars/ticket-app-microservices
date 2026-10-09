package com.ticketapp.notificationservice.strategy;


import com.ticketapp.notificationservice.domain.Notification;
import com.ticketapp.notificationservice.domain.NotificationType;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationStrategy implements NotificationStrategy{
    @Override
    public NotificationType getNotificationType() {
        return NotificationType.SMS;
    }

    @Override
    public void send(Notification notification) {
        System.out.println("Sending SMS to: " + notification.getRecipient());
    }
}
