package com.library.catalog.domain.dto;

import java.time.Instant;
import java.util.UUID;

public record BookResponseDTO(
        UUID id,
        String title,
        String author,
        Instant createdAt
) { }