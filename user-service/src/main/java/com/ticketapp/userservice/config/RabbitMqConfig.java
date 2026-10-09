package com.ticketapp.userservice.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMqConfig {

    public static final String USER_EXCHANGE="user.events";
    public static final String USER_REGISTERED_ROUTING_KEY="user.registered";
    @Bean
    public DirectExchange userExchange(){
        return new DirectExchange(USER_EXCHANGE);
    }
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new JacksonJsonMessageConverter();

    }



}
