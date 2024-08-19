package com.bookstore.BookService.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String s) {
        super(s);
    }
}
