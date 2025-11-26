package com.library.catalog;

import com.library.catalog.domain.dto.BookResponseDTO;
import com.library.catalog.domain.dto.CreateBookRequestDTO;
import com.library.catalog.domain.mapper.BookMapper;
import com.library.catalog.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search")
    public ResponseEntity<Page<BookResponseDTO>> searchBooks(
            @RequestParam String title,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int items
    ) {
        var books = bookService.searchBooksByTitle(title, page, items);
        var booksResponse = books.map(bookMapper::toDto);
        return ResponseEntity.ok(booksResponse);
    }

    @GetMapping("/search/author")
    public ResponseEntity<Page<BookResponseDTO>> searchBooksByAuthor(
            @RequestParam String author,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int items
    ) {
        var books = bookService.searchBooksByAuthor(author, page, items);
        var booksResponse = books.map(bookMapper::toDto);
        return ResponseEntity.ok(booksResponse);
    }

}
