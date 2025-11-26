package com.loan.management.domain.mapper;

import com.loan.management.domain.dto.response.LoanResponseDTO;
import com.loan.management.domain.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(target = "")
    LoanResponseDTO toDto(Loan loan);
}
