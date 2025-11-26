package com.library.catalog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.catalog.config.RabbitMQConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Object data, String routingKey) {
        var messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(data);
            var message = new Message(bytes, messageProperties);
            rabbitTemplate.convertAndSend(RabbitMQConnection.EXCHANGE, routingKey, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
