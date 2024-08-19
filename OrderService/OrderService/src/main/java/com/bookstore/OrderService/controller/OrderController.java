package com.bookstore.OrderService.controller;

import com.bookstore.OrderService.convertor.DtoEntityConvertor;
import com.bookstore.OrderService.domain.Order;

import com.bookstore.OrderService.dto.OrderDto;

import com.bookstore.OrderService.service.OrderService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final DtoEntityConvertor convertor;


    public OrderController(OrderService orderService, DtoEntityConvertor convertor) {
        this.orderService = orderService;
        this.convertor = convertor;

    }
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        List<Order> orders= orderService.getAllOrders();
        if(orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<OrderDto> orderDtos= orders.stream()
                .map(convertor::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable long id){
        Order order=orderService.getOrder(id);
        OrderDto orderDto=convertor.toDto(order);
        return ResponseEntity.ok(orderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto){
        Order order=convertor.toEntity(orderDto);
        Order create= orderService.saveOrder(order);
        OrderDto createDto= convertor.toDto(create);
        return ResponseEntity.status(HttpStatus.CREATED).body(createDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update( @PathVariable long id, @Valid @RequestBody OrderDto orderDto){
        Order order =convertor.toEntity(orderDto);
        Order update= orderService.updateOrder(id,order);
        OrderDto updatedDto= convertor.toDto(update);
        return ResponseEntity.ok(updatedDto);
    }

}
