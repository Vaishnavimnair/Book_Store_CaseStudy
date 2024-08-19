package com.bookstore.OrderService.feign_clients;

import com.bookstore.OrderService.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CustomerService")
public interface CustomerClient {
    @GetMapping("/customer")
    CustomerDto getCustomerById(@PathVariable long id);
}
