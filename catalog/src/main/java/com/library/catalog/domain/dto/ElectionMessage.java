package com.library.catalog.domain.dto;

import java.util.List;

public record ElectionMessage(
    ElectionMessageType type,
    List<Integer> ids
) {
    public static ElectionMessage coordinator(int id) {
        return new ElectionMessage(ElectionMessageType.COORDINATOR, List.of(id));
    }
}