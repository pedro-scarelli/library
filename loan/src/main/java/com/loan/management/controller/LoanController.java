package com.loan.management.controller;

import com.loan.domain.dto.request.CreateLoanRequestDTO;
import com.loan.management.domain.mapper.LoanMapper;
import com.loan.management.service.LoanService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/v1/loan", produces = "application/json")
public class LoanController {

    private final LoanService loanService;
    private final LoanMapper loanMapper;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Object> createLoan(
            @Valid @RequestBody CreateLoanRequestDTO dto) {

        var loan = loanService.createLoan(dto);

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(loan.getId())
                .toUri();

        var message = Map.of("message", "Empréstimo criado com sucesso", "loanId", loan.getId());

        return ResponseEntity.created(location).body(message);
    }

    @GetMapping
    public ResponseEntity<@NonNull LoanPaginationResponseDTO> getAllLoans(
            @RequestParam int page,
            @RequestParam int items) {

        return ResponseEntity.ok(loanService.getAllLoans(page, items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLoan(@PathVariable UUID id) {
        var loan = loanMapper.toDto(loanService.getLoan(id));
        return ResponseEntity.ok(loan);
    }

    @PatchMapping("/finish/{id}")
    public ResponseEntity<Object> finishLoan(@PathVariable UUID id) {
        var loan = loanMapper.toDto(loanService.finishLoan(id));
        return ResponseEntity.ok(loan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLoan(@PathVariable UUID id) {
        var loan = loanService.deleteLoan(id);
        var msg = Map.of("message", "Empréstimo deletado com sucesso", "loanId", loan.getId());
        return ResponseEntity.ok(msg);
    }
}
