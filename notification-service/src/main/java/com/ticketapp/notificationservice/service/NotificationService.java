package com.ticketapp.notificationservice.service;


import com.ticketapp.notificationservice.domain.Notification;
import com.ticketapp.notificationservice.domain.NotificationStatus;
import com.ticketapp.notificationservice.domain.NotificationType;
import com.ticketapp.notificationservice.event.UserRegisteredEvent;
import com.ticketapp.notificationservice.repository.NotificationRepository;
import com.ticketapp.notificationservice.strategy.NotificationStrategy;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final List<NotificationStrategy> strategies;

    public NotificationService(NotificationRepository notificationRepository,  List<NotificationStrategy> strategies) {
        this.notificationRepository = notificationRepository;
        this.strategies = strategies;
    }

    public void createRegistrationNotification(UserRegisteredEvent event){

            sendNotification(
                    event.getEmail(),
                    NotificationType.EMAIL,
                    "Welcome "+event.getFirstName()
            );

    }

    public void sendNotification(String recipient,
                                 NotificationType type,
                                 String message) {

        boolean strategyFound = false;

        Notification notification = Notification.builder()
                .recipient(recipient)
                .type(type)
                .status(NotificationStatus.PENDING)
                .message(message)
                .build();

        // burada type'a uygun strategy'yi bulacağız


        for (NotificationStrategy strategy : strategies) {

            if(strategy.getNotificationType()== type){
                strategyFound = true;

                try{
                    strategy.send(notification);
                    notification.setStatus(NotificationStatus.SENT);

                } catch (Exception e) {
                    notification.setStatus(NotificationStatus.FAILED);
                }

                break;

            }


        }

        if(!strategyFound){
            notification.setStatus(NotificationStatus.FAILED);
        }
        notificationRepository.save(notification);



    }
}
