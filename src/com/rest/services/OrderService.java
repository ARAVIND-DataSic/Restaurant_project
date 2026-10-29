package com.rest.services;

import com.rest.models.Order;
import com.rest.repository.OrderRepository;
import com.rest.repository.TableRepository;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;

    public OrderService() {
        this(new OrderRepository(), new TableRepository());
    }

    public OrderService(OrderRepository orderRepository, TableRepository tableRepository) {
        this.orderRepository = orderRepository;
        this.tableRepository = tableRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order placeOrder(int tableId) {
        boolean tableExists = tableRepository.findAll().stream()
                .anyMatch(table -> table.getTableId() == tableId);
        if (!tableExists) {
            throw new IllegalArgumentException("Table " + tableId + " does not exist.");
        }
        return orderRepository.save(tableId);
    }

    public Optional<Order> updateStatus(int orderId, String statusValue) {
        Order.Status status;
        try {
            status = Order.Status.valueOf(statusValue.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Status must be PLACED, PREPARING, SERVED, or COMPLETED.");
        }

        Optional<Order> order = orderRepository.findById(orderId);
        order.ifPresent(foundOrder -> foundOrder.setStatus(status));
        return order;
    }
}
