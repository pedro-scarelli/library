package com.loan.management.domain.mapper;

import com.loan.management.domain.dto.response.LoanResponseDTO;
import com.loan.management.domain.model.Loan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanResponseDTO toDto(Loan loan);
}
