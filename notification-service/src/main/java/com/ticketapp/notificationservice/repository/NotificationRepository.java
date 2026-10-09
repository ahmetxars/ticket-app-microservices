package com.ticketapp.notificationservice.repository;

import com.ticketapp.notificationservice.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {


}
