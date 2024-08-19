package com.bookstore.OrderService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDto(  Long id,
                        String title,
                        int stock)

{
}
