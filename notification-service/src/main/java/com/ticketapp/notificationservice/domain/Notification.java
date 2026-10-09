package com.ticketapp.notificationservice.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
    private String message;
    private String recipient;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();


}
