package com.library.catalog;

import com.library.catalog.domain.dto.BookResponseDTO;
import com.library.catalog.domain.dto.CreateBookRequestDTO;
import com.library.catalog.domain.mapper.BookMapper;
import com.library.catalog.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping(path = "/v1/book", produces = "application/json")
@RestController
public class BookController {

    private BookService bookService;

    private BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody CreateBookRequestDTO createBookRequestDTO) {
        var book = bookService.createBook(createBookRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.toDto(book));
    }

}
