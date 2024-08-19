package com.bookstore.BookService.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record BookDto(long id,

                      @NotNull(message = "title cannot be empty")
                      @Length(min = 1,max=255, message = "invalid")
                      String title,

                      @NotNull(message = "author cannot be empty")
                      @Length(min = 1,max=255, message = "invalid")
                      String author,

                      @NotNull(message = "price cannot be empty")
                      @Positive(message = "price must be greater than zero")
                      float price,

                      @NotNull(message = "cannot be empty")
                      @PositiveOrZero(message = "cannot be negative")
                      int stock)
{
}
