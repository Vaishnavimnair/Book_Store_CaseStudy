package com.bookstore.OrderService.service;

import com.bookstore.OrderService.convertor.DtoEntityConvertor;
import com.bookstore.OrderService.domain.Order;
import com.bookstore.OrderService.dto.BookDto;
import com.bookstore.OrderService.dto.OrderDto;
import com.bookstore.OrderService.exception.InsufficientStockException;
import com.bookstore.OrderService.exception.OrderNotFoundException;
import com.bookstore.OrderService.feign_clients.BookClient;
import com.bookstore.OrderService.repo.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    private final BookClient bookClient;
    private final DtoEntityConvertor dtoEntityConvertor;

    public OrderServiceImpl(OrderRepository orderRepository, BookClient bookClient, DtoEntityConvertor dtoEntityConvertor) {
        this.orderRepository = orderRepository;
        this.bookClient = bookClient;
        this.dtoEntityConvertor = dtoEntityConvertor;
    }

    @Override
    public Order saveOrder(Order order) {
        OrderDto orderDto = dtoEntityConvertor.toDto(order);
        validateStock(orderDto);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return List.copyOf(orderRepository.findAll());
    }

    @Override
    public Order getOrder(long id) {
        return orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("not found"));
    }

    @Override
    public Order updateOrder(long id, Order order) {
        Optional<Order> updated=orderRepository.findById(order.getId());
        if(updated.isPresent()){
            Order existingOrder= updated.get();
            existingOrder.setCustomerId(order.getCustomerId());
            existingOrder.setBookId(order.getBookId());
            existingOrder.setQuantity(order.getQuantity());
            existingOrder.setStatus(order.getStatus());

            OrderDto orderDto = dtoEntityConvertor.toDto(existingOrder);
            validateStock(orderDto);
            return orderRepository.save(existingOrder);
        }
        throw new OrderNotFoundException("no order found");
    }

    @Override
    public void deleteOrder(long id) {
    var order= orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("not found"));
    orderRepository.delete(order);
    }


    private void validateStock(OrderDto orderDto) {
        BookDto bookDto = bookClient.getBookById(orderDto.bookId());

        if (bookDto == null) {
            throw new EntityNotFoundException("Book not found with id: " + orderDto.bookId());
        }

        if (bookDto.stock() < orderDto.quantity()) {
            throw new InsufficientStockException("Not enough stock for book with id: " + orderDto.bookId());
        }
    }
}
