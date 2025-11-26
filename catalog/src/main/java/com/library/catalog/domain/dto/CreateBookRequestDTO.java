package com.library.catalog.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreateBookRequestDTO(
        @NotBlank @Size(max = 100) String title,
        @NotBlank String author,
        LocalDateTime year
) {}