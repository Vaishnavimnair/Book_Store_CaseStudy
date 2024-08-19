package com.bookstore.BookService.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String bookAlreadyExists) {

        super(bookAlreadyExists);
    }
}
