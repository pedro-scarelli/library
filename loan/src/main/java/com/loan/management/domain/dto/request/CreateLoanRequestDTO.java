package com.loan.management.domain.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateLoanRequestDTO(
        @NotNull UUID userId,
        @NotNull UUID bookId
) {}
