package com.library.catalog.domain.mapper;

import com.library.catalog.domain.dto.BookResponseDTO;
import com.library.catalog.domain.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponseDTO toDto(Book book);

}