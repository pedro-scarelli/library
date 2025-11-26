package com.loan.management.domain.model;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException() {
        super("Empréstimo não encontrado");
    }
}
