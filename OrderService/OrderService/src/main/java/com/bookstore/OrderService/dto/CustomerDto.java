package com.bookstore.OrderService.dto;

import jakarta.validation.constraints.NotNull;

public record CustomerDto(
        @NotNull(message = "Customer ID cannot be null") Long customerId) {



}
