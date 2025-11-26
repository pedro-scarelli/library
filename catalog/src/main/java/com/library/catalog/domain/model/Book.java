package com.library.catalog.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import java.io.Serial;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name="tb_books")
@SQLRestriction("dt_deleted_at IS NULL")
public class Book {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="pk_id")
    private UUID id;

    @Column (name="st_title", nullable = false)
    private String title;

    @Column (name="st_author", nullable = false)
    private String author;

    @Column (name="st_year", nullable = false)
    private LocalDateTime year;

}
