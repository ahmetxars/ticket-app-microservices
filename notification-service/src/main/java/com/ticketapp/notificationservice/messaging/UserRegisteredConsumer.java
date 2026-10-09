package com.ticketapp.notificationservice.messaging;


import com.ticketapp.notificationservice.config.RabbitMqConfig;
import com.ticketapp.notificationservice.event.UserRegisteredEvent;
import com.ticketapp.notificationservice.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredConsumer {

    private final NotificationService notificationService;

    public UserRegisteredConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    // "Bu method sürekli USER_REGISTERED_QUEUE kuyruğunu dinlesin"
    @RabbitListener(queues = RabbitMqConfig.USER_REGISTERED_QUEUE)
    public void handleUserRegistered(UserRegisteredEvent event) {
        notificationService.createRegistrationNotification(event);
        System.out.println("Received UserRegistered event: " + event.getEmail());
    }
}
