package com.library.catalog.service;

import com.library.catalog.config.RabbitMQConnection;
import com.library.catalog.domain.dto.ElectionMessage;
import com.library.catalog.domain.dto.ElectionMessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ElectionService {
    private static final AtomicInteger INSTANCE_ID = new AtomicInteger();
    private static final AtomicBoolean COORDINATOR = new AtomicBoolean();

    private final MessageService messageService;

    public int fetchInstanceId() {
        int id = INSTANCE_ID.get();
        if (id == 0) {
            id = Integer.parseInt(System.getenv("ELECTION_ID"));
            INSTANCE_ID.set(id);
        }

        return id;
    }

    public boolean isCoordinator() {
        return COORDINATOR.get();
    }

    public void processMessage(
        int id,
        ElectionMessageType type,
        ElectionMessage payload
    ) {
        switch (type) {
            case ELECTION -> processElection(id, payload);
            case COORDINATOR -> processCoordinator(id, payload);
        }
    }

    private void processElection(int id, ElectionMessage payload) {
        var key = RabbitMQConnection.Queues.ELECTION;
        var route = "";
        if (payload.ids().contains(id)) {
            payload = ElectionMessage.coordinator(id);
            route = "all";
        } else {
            payload.ids().add(id);
            route = String.valueOf(id + 1);
        }

        key = String.join(".", key, route);
        messageService.sendMessage(payload, key);
    }

    private void processCoordinator(int id, ElectionMessage payload) {
        COORDINATOR.set(payload.ids().contains(id));
    }
}
