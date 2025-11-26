package com.library.catalog.service;

import com.library.catalog.domain.dto.CreateBookRequestDTO;
import com.library.catalog.domain.model.Book;
import com.library.catalog.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(CreateBookRequestDTO createBookRequestDTO) {
        var newBook = new Book();
        BeanUtils.copyProperties(createBookRequestDTO, newBook);
        return bookRepository.save(newBook);
    }

    public Page<Book> searchBooksByTitle(String title, int page, int items) {
        return bookRepository.findByTitleContaining(title, PageRequest.of(page - 1, items));
    }
}
