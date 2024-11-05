package org.example.rabbitmqapplication.consumer;

import org.example.rabbitmqapplication.config.RabbitMQConfig;
import org.example.rabbitmqapplication.model.EmailDetails;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    @Autowired
    private JavaMailSender mailSender;

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void handleMessage(EmailDetails emailDetails) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailDetails.getRecipient());
            message.setSubject(emailDetails.getSubject());
            message.setText(emailDetails.getMessageBody());

            mailSender.send(message);
            System.out.println("Email sent to " + emailDetails.getRecipient());
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
