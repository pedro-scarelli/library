package com.loan.management.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class LoanMessage {

    private UUID bookId;

}