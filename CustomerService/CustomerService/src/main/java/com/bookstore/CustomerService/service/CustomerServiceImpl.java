package com.bookstore.CustomerService.service;

import com.bookstore.CustomerService.domain.Customer;
import com.bookstore.CustomerService.repo.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer createCustomer(Customer customer) {
        customerRepository.findByCustomerPhoneNumber(customer.getCustomerPhoneNumber())
                .ifPresent(existingCustomer->{throw new RuntimeException("customer exists");
                });
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {

        return List.copyOf(customerRepository.findAll());
    }

    @Override
    public Customer getCustomer(long id) {
        return customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer not found"));
    }

    @Override
    public void deleteCustomer(long id) {
        var customer=customerRepository.findById(id).orElseThrow(()->new RuntimeException("patient not found"));
        customerRepository.delete(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
//       var customer1= customerRepository.findByPhoneNumber(customer.getCustomerPhoneNumber());
//        if(customer1.isPresent()){
//            return customerRepository.save(customer)
//        }
//        else {
//            throw new RuntimeException("customer not found");
//        }
        Optional<Customer> updated = customerRepository.findByCustomerPhoneNumber(customer.getCustomerPhoneNumber());
        if (updated.isPresent()) {
            Customer existingCustomer = updated.get();
            existingCustomer.setCustomerName(customer.getCustomerName());
            existingCustomer.setCustomerEmail(customer.getCustomerEmail());
            existingCustomer.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
            return customerRepository.save(existingCustomer);
        }
        throw new RuntimeException("Customer not found with phone number: " + customer.getCustomerPhoneNumber());

    }
}
