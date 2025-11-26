package com.library.catalog.service;

import com.library.catalog.config.RabbitMQConnection;
import com.library.catalog.domain.dto.ElectionMessage;
import com.library.catalog.domain.dto.ElectionMessageType;
import com.library.catalog.util.JSONUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElectionConsumer {
    private final ElectionService service;

    @RabbitListener(queues = RabbitMQConnection.Queues.ELECTION)
    public void consumeElection(Message message) {
        var key = message.getMessageProperties().getReceivedRoutingKey();
        var id = service.fetchInstanceId();
        if (!key.contains("all") && !key.contains(String.valueOf(id))) {
            return;
        }

        var type = ElectionMessageType.from(key);
        var payload = JSONUtils.transform(message.getBody(), ElectionMessage.class);
        service.processMessage(id, type, payload);
    }
}
