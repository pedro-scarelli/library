package com.login.user.repository;

import com.loan.management.domain.model.Loan;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<@NonNull Loan, @NonNull UUID>{
}
