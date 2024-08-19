package com.bookstore.BookService.controller;

import com.bookstore.BookService.convertor.DtoEntityConvertors;
import com.bookstore.BookService.domain.Book;
import com.bookstore.BookService.dto.BookDto;
import com.bookstore.BookService.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final DtoEntityConvertors convertor;

    public BookController(BookService bookService, DtoEntityConvertors convertor) {
        this.bookService = bookService;
        this.convertor = convertor;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<BookDto> bookDtos = books.stream()
                .map(convertor::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDtos);
    }

//    @GetMapping
//    public ResponseEntity<List<Book>> getAllBooks(){
//        var books= bookService.getAllBooks();
//        if(books.isEmpty()){
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(books);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable long id){
        Book book=bookService.getBook(id);
        BookDto bookDto=convertor.toDto(book);
        return ResponseEntity.ok(bookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id){
       bookService.deleteBook(id);
       return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto){
        Book book=convertor.toEntity(bookDto);
        Book create= bookService.createBook(book);
        BookDto createDto=convertor.toDto(create);
        return ResponseEntity.status(HttpStatus.CREATED).body(createDto);
    }

    @PutMapping("/{id})")
    public ResponseEntity<BookDto> update(@Valid @PathVariable long id,@RequestBody BookDto bookDto){
        Book book=convertor.toEntity(bookDto);
        Book update= bookService.updateBook(bookDto.id(), book);
        BookDto updatedDto=convertor.toDto(update);
        return ResponseEntity.ok(updatedDto);
    }
}
