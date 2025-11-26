package com.loan.management.service;

import com.loan.domain.dto.request.CreateLoanRequestDTO;
import com.loan.management.domain.dto.response.LoanPaginationResponseDTO;
import com.loan.management.domain.mapper.LoanMapper;
import com.login.user.domain.model.Loan;
import com.login.user.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    public Loan createLoan(CreateLoanRequestDTO dto) {
        // implementar chamada ao rabbit

        var loan = new Loan();
        loan.setUserId(dto.userId());
        loan.setBookId(dto.bookId());

        return loanRepository.save(loan);
    }

    public Loan getLoan(UUID id) {
        return loanRepository.findById(id)
                .orElseThrow(LoanNotFoundException::new);
    }

    public LoanPaginationResponseDTO getAllLoans(int page, int items) {
        var loans = loanRepository.findAll(PageRequest.of(page - 1, items));
        var loansDto = StreamSupport.stream(loans.spliterator(), false)
                .map(loanMapper::toDto)
                .collect(Collectors.toList());

        return new LoanPaginationResponseDTO(
                loans.getTotalElements(),
                loans.getTotalPages(),
                loansDto
        );
    }

    public Loan finishLoan(UUID id) {
        var loan = getLoan(id);
        loan.setReturnedAt(Instant.now());

        return loanRepository.save(loan);
    }

    public Loan deleteLoan(UUID id) {
        var loan = getLoan(id);
        loan.setDeletedAt(Instant.now());

        return loanRepository.save(loan);
    }
}
