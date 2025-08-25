package com.transactionmgmt.users.ms_users.integration.rabbitmq;


import com.transactionmgmt.users.ms_users.integration.rabbitmq.command.CreateAccountCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandPublisherImpl implements CommandPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.exchange}")
    private String exchangeName;

    @Value("${app.routing}")
    private String routingKey;
    

    public void sendCreateAccount(CreateAccountCommand evt) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, evt);
    }
}
