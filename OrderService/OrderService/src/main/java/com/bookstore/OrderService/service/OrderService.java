package com.bookstore.OrderService.service;

import com.bookstore.OrderService.domain.Order;
import jdk.dynalink.linker.LinkerServices;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
    public Order saveOrder(Order order);
    public List<Order> getAllOrders();
    public Order getOrder(long id);
    public Order updateOrder(long id, Order order);
    public void deleteOrder(long id);
}
