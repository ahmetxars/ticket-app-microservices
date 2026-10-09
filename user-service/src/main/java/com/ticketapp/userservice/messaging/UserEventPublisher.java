package com.ticketapp.userservice.messaging;


import com.ticketapp.userservice.config.RabbitMqConfig;
import com.ticketapp.userservice.event.UserRegisteredEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {
    // bu değerler UserEventPublisher nesnesine değil, sınıfın kendisine ait. Bu yüzden statik olarak tanımladık

    private final RabbitTemplate rabbitTemplate;

    public UserEventPublisher(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }
    public void publishUserRegistered(UserRegisteredEvent event){
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.USER_EXCHANGE,
                RabbitMqConfig.USER_REGISTERED_ROUTING_KEY,
                event

        );

    }

}
