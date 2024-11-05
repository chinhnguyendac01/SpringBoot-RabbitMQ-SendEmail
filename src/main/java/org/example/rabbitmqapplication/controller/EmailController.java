package org.example.rabbitmqapplication.controller;

import org.example.rabbitmqapplication.model.EmailDetails;
import org.example.rabbitmqapplication.producer.EmailProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailProducer emailProducer;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailDetails emailDetails) {
        emailProducer.sendEmailMessage(emailDetails);
        return "Email request queued successfully!";
    }
}
