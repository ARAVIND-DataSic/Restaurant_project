package com.rest.repository;

import com.rest.models.OrderItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository {
    private final List<OrderItem> orderItems = new ArrayList<>();
    private int nextOrderItemId = 1;

    public List<OrderItem> findByOrderId(int orderId) {
        List<OrderItem> matchingItems = new ArrayList<>();
        for (OrderItem item : orderItems) {
            if (item.getOrderId() == orderId) {
                matchingItems.add(item);
            }
        }
        return matchingItems;
    }

    public OrderItem save(int orderId, int menuItemId, int quantity, BigDecimal unitPrice) {
        OrderItem orderItem = new OrderItem(
                nextOrderItemId++, orderId, menuItemId, quantity, unitPrice);
        orderItems.add(orderItem);
        return orderItem;
    }
}
