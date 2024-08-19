package com.bookstore.BookService.convertor;

import com.bookstore.BookService.domain.Book;
import com.bookstore.BookService.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class DtoEntityConvertors {
    public BookDto toDto(Book book){
        return new BookDto(book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getStock());
    }
    public Book toEntity(BookDto dto){
        Book book=new Book();
        book.setId(dto.id());
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setPrice(dto.price());
        book.setStock(dto.stock());

        return book;
    }
}
