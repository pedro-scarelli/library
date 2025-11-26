package com.loan.management.domain.dto.response;

import java.time.Instant;
import java.util.UUID;

public record LoanResponseDTO(
        UUID id,
        UUID userId,
        UUID bookId,
        Instant createdAt,
        Instant returnedAt
) {}
