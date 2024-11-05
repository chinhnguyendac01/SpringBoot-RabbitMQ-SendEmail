package org.example.rabbitmqapplication.producer;

import org.example.rabbitmqapplication.config.RabbitMQConfig;
import org.example.rabbitmqapplication.model.EmailDetails;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEmailMessage(EmailDetails emailDetails) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_EXCHANGE, RabbitMQConfig.ROUTING_KEY, emailDetails);
    }
}
