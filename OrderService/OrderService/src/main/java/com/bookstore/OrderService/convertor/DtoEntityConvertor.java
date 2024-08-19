package com.bookstore.OrderService.convertor;

import com.bookstore.OrderService.domain.Order;
import com.bookstore.OrderService.dto.OrderDto;

import org.springframework.stereotype.Component;

@Component
public class DtoEntityConvertor {
    public OrderDto toDto(Order order){
        return new OrderDto(order.getId(),
                order.getCustomerId(),
                order.getBookId(),
                order.getQuantity(),
                order.getStatus());
    }

    public Order toEntity(OrderDto dto){
        Order order=new Order();
        order.setId(dto.id());
        order.setCustomerId(dto.customerId());
        order.setBookId(dto.bookId());
        order.setQuantity(dto.quantity());
        order.setStatus(dto.status());
        return order;
    }
}
