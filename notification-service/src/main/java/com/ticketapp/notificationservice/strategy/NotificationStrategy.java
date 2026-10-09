package com.ticketapp.notificationservice.strategy;

import com.ticketapp.notificationservice.domain.Notification;
import com.ticketapp.notificationservice.domain.NotificationType;

public interface NotificationStrategy {

    NotificationType getNotificationType();

    void send(Notification notification);
}