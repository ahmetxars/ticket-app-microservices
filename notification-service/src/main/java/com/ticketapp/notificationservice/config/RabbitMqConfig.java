package com.ticketapp.notificationservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMqConfig {

    public static final String USER_EXCHANGE="user.events";
    public static final String USER_REGISTERED_ROUTING_KEY="user.registered";
    public static final String USER_REGISTERED_QUEUE="user.registered.queue";

    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(USER_EXCHANGE);
    }

    @Bean
    public Queue userRegisteredQueue() {
        return new Queue(USER_REGISTERED_QUEUE);

    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public Binding userRegisteredBinding(Queue userRegisteredQueue, DirectExchange userExchange) {

        return BindingBuilder
                .bind(userRegisteredQueue) //Hangi queue bağlanacak?
                .to(userExchange) //hangi exchange e bağlanacak?
                .with(USER_REGISTERED_ROUTING_KEY); //hangi routing key ile bağlanacak?

    }

}
