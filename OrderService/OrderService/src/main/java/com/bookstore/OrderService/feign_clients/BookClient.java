package com.bookstore.OrderService.feign_clients;

import com.bookstore.OrderService.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="BookService")
public interface BookClient {
    @GetMapping("/books/{id}")
    BookDto getBookById(@PathVariable long id);
}
