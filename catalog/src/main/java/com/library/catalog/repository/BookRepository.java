package com.library.catalog.repository;

import com.library.catalog.domain.model.Book;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<@NonNull Book, @NonNull UUID> {
}