package com.bookstore.BookService.service;

import com.bookstore.BookService.domain.Book;

import java.util.List;

public interface BookService {
    Book createBook(Book book);
    Book getBook(long id);
    List<Book> getAllBooks();
    void deleteBook(long id);
    Book updateBook(long id,Book book);
}
