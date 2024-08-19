package com.bookstore.OrderService.dto;

import com.bookstore.OrderService.domain.Order;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record OrderDto(long id,
                       @NotNull(message = "Customer ID cannot be null")
                       long customerId,
                       @NotNull(message = "Book ID cannot be null")
                       long bookId,
                       @NotNull(message = "Quantity cannot be null")
                       @Min(value = 1, message = "Quantity must be greater than 0")
                       int quantity,
                       @NotEmpty(message = "status cannot be null")
                       @Pattern(regexp = "PENDING|CONFIRMED|CANCELLED", message = "Invalid status. Must be one of: PENDING, CONFIRMED, CANCELLED")
                       String status) {


}
