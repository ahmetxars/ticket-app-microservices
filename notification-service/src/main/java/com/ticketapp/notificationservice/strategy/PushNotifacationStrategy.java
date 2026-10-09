package com.ticketapp.notificationservice.strategy;


import com.ticketapp.notificationservice.domain.Notification;
import com.ticketapp.notificationservice.domain.NotificationType;
import org.springframework.stereotype.Component;

@Component
public class PushNotifacationStrategy implements NotificationStrategy {
    @Override
    public NotificationType getNotificationType() {
        return NotificationType.PUSH;
    }

    @Override
    public void send(Notification notification) {

        System.out.println("Sending PUSH to: " + notification.getRecipient());

    }
}
