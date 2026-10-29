package com.rest.services;

import com.rest.models.MenuItem;
import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.repository.MenuItemRepository;
import com.rest.repository.OrderItemRepository;
import com.rest.repository.OrderRepository;
import java.util.List;
import java.util.Optional;

public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderItemService(
            OrderItemRepository orderItemRepository,
            OrderRepository orderRepository,
            MenuItemRepository menuItemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<OrderItem> getItemsForOrder(int orderId) {
        if (!orderRepository.findById(orderId).isPresent()) {
            throw new IllegalArgumentException("Order " + orderId + " does not exist.");
        }
        return orderItemRepository.findByOrderId(orderId);
    }

    public OrderItem addItem(int orderId, int menuItemId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        Optional<Order> order = orderRepository.findById(orderId);
        if (!order.isPresent()) {
            throw new IllegalArgumentException("Order " + orderId + " does not exist.");
        }
        Optional<MenuItem> menuItem = menuItemRepository.findById(menuItemId);
        if (!menuItem.isPresent()) {
            throw new IllegalArgumentException("Menu item " + menuItemId + " does not exist.");
        }
        MenuItem item = menuItem.get();
        return orderItemRepository.save(orderId, menuItemId, quantity, item.getPrice());
    }
}
