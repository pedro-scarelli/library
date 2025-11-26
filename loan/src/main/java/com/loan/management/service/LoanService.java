package com.loan.management.service;

import com.loan.management.config.CustomMessageSender;
import com.loan.management.config.RabbitMQConnection;
import com.loan.management.domain.LoanMessage;
import com.loan.management.domain.dto.request.CreateLoanRequestDTO;
import com.loan.management.domain.dto.response.LoanPaginationResponseDTO;
import com.loan.management.domain.mapper.LoanMapper;
import com.loan.management.domain.model.Loan;
import com.loan.management.domain.model.LoanNotFoundException;
import com.login.user.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@AllArgsConstructor
@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final CustomMessageSender customMessageSender;

    public Loan createLoan(CreateLoanRequestDTO dto) {
        sendLoanRequestToCatalog(dto.bookId());

        var loan = new Loan();
        loan.setUserId(dto.userId());
        loan.setBookId(dto.bookId());

        return loanRepository.save(loan);
    }

    private void sendLoanRequestToCatalog(UUID bookId) {
        var message = new LoanMessage(
                bookId
            );

        customMessageSender.sendMessage(
                message,
                RabbitMQConnection.EXCHANGE,
                RabbitMQConnection.Queues.CATALOG
            );
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
