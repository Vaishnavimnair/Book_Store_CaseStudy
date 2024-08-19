package com.bookstore.CustomerService.service;

import com.bookstore.CustomerService.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomer(long id);
    void deleteCustomer(long id);
    Customer updateCustomer(Customer customer);
}
