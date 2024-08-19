package com.bookstore.CustomerService.dto;

public record CustomerDto(long customerId,
                          String customerName,
                          String customerEmail,
                          String customerPhoneNumber) {
}
