package com.library.catalog.domain.dto;

import java.util.Map;

public enum ElectionMessageType {
    ELECTION,
    COORDINATOR;

    private static final Map<String, ElectionMessageType> MAPPING = Map.of(
        "election", ELECTION,
        "coordinator", COORDINATOR
    );

    public static ElectionMessageType from(String routingKey) {
        return MAPPING.get(routingKey);
    }
}
