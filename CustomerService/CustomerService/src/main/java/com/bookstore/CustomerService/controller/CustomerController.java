package com.bookstore.CustomerService.controller;

import com.bookstore.CustomerService.convertor.DtoEntityConvertor;
import com.bookstore.CustomerService.domain.Customer;
import com.bookstore.CustomerService.dto.CustomerDto;
import com.bookstore.CustomerService.service.CustomerService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final DtoEntityConvertor dtoEntityConvertor;

    public CustomerController(CustomerService customerService, DtoEntityConvertor dtoEntityConvertor) {
        this.customerService = customerService;
        this.dtoEntityConvertor = dtoEntityConvertor;
    }
    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers(){
        var customers=customerService.getAllCustomers();
        if(customers.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable long id){
        Customer customer=customerService.getCustomer(id);
        CustomerDto customerDto=dtoEntityConvertor.toDto(customer);
        return ResponseEntity.ok(customerDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id){
      //  var customers= customerService.getCustomer(id);
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto){
        Customer customer = dtoEntityConvertor.toEntity(customerDto);
        Customer savedCustomer = customerService.createCustomer(customer);
        CustomerDto saved= dtoEntityConvertor.toDto(savedCustomer);
       // return new ResponseEntity<>(dtoEntityConvertor.toDto(savedCustomer), HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto){
       Customer customer=dtoEntityConvertor.toEntity(customerDto);
        Customer update= customerService.updateCustomer(customer);
        CustomerDto updatedDto=dtoEntityConvertor.toDto(update);
        return  ResponseEntity.ok(updatedDto);
    }
}

