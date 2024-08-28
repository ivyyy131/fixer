package com.egt.gateway.service;

import com.egt.gateway.model.RequestLog;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    public void sendRequestLog(RequestLog log) {
        amqpTemplate.convertAndSend(exchangeName, "request.log", log);
    }
}

