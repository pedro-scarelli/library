package com.loan.management.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;
import java.util.Instant;

@Getter
@Setter
@Entity
@Table(name = "tb_loans")
@SQLRestriction("dt_deleted_at IS NULL")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_id")
    private UUID id;

    @Column(name = "fk_user_id", nullable = false)
    private UUID userId;

    @Column(name = "fk_book_id", nullable = false)
    private UUID bookId;

    @CreationTimestamp
    @Column(name = "dt_created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "dt_returned_at")
    private Instant returnedAt;

    @Column(name = "dt_deleted_at")
    private Instant deletedAt;
}
