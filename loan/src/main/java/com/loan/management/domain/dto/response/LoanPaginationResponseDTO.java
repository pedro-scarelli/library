package com.loan.management.domain.dto.response;

import java.util.List;

public record LoanPaginationResponseDTO(
        long totalItems,
        int totalPages,
        List<LoanResponseDTO> loans
) {}
