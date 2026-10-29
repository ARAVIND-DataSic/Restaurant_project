package com.rest.repository;

import com.rest.models.Order;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();
    private int nextOrderId = 1;

    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    public Optional<Order> findById(int orderId) {
        return orders.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst();
    }

    public Order save(int tableId) {
        Order order = new Order(
                nextOrderId++,
                tableId,
                Order.Status.PLACED,
                LocalDateTime.now());
        orders.add(order);
        return order;
    }
}
