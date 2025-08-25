package com.transactionmgmt.users.ms_users.integration.rabbitmq.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${app.exchange}")
    private String exchangeName;

    @Bean
    public DirectExchange accountsDirect() {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
