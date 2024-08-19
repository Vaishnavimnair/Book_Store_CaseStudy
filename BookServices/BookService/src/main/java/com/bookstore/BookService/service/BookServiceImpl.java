package com.bookstore.BookService.service;

import com.bookstore.BookService.domain.Book;
import com.bookstore.BookService.exception.BookAlreadyExistsException;
import com.bookstore.BookService.exception.BookNotFoundException;
import com.bookstore.BookService.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
//        bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor())
//                .ifPresent(existingBook -> {
//                    throw new BookAlreadyExistsException("book already exists");
//                });
        return bookRepository.save(book);
    }

    @Override
    public Book getBook(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("no book found"));
    }

    @Override
    public List<Book> getAllBooks() {
        return List.copyOf(bookRepository.findAll());
    }

    @Override
    public void deleteBook(long id) {
        var book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("book not found"));
        bookRepository.delete(book);
    }



    @Override
    public Book updateBook(long id,Book book) {
       // Optional<Book> updated = bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
        Optional<Book> updated =bookRepository.findById(book.getId());
        if (updated.isPresent()) {
            Book existingBook = updated.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPrice(book.getPrice());
            existingBook.setStock(book.getStock());

            return bookRepository.save(existingBook);
        }
        throw new BookNotFoundException("book not found");
    }
//    throw new BookNotFoundException("book not found");
//        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
//        existingBook.setTitle(book.getTitle());
//        existingBook.setAuthor(book.getAuthor());
//        existingBook.setPrice(book.getPrice());
//        existingBook.setStock(book.getStock());
//        bookRepository.save(existingBook);
//        return book;

}
