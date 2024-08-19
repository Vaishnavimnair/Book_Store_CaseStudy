package com.bookstore.CustomerService.convertor;

import com.bookstore.CustomerService.domain.Customer;
import com.bookstore.CustomerService.dto.CustomerDto;
import org.apache.http.conn.util.PublicSuffixList;
import org.springframework.stereotype.Component;

@Component
public class DtoEntityConvertor {
    public CustomerDto toDto(Customer customer){
        return new CustomerDto(customer.getCustomerId(),
                   customer.getCustomerName(),
                    customer.getCustomerEmail(),
                customer.getCustomerPhoneNumber());

    }
    public Customer toEntity(CustomerDto dto){
        Customer customer=new Customer();
        customer.setCustomerId(dto.customerId());
        customer.setCustomerName(dto.customerName());
        customer.setCustomerEmail(dto.customerEmail());
        customer.setCustomerPhoneNumber(dto.customerPhoneNumber());
        return customer;
    }

}
