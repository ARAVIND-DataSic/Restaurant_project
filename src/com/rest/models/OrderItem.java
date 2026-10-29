package com.rest.models;

import java.math.BigDecimal;

public class OrderItem {
    private final int id;
    private final int orderId;
    private final int menuItemId;
    private final int quantity;
    private final BigDecimal unitPrice;

    public OrderItem(int id, int orderId, int menuItemId, int quantity, BigDecimal unitPrice) {
        this.id = id;
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getLineTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return "Order item ID: " + id
                + ", Order ID: " + orderId
                + ", Menu item ID: " + menuItemId
                + ", Quantity: " + quantity
                + ", Unit price: " + unitPrice.toPlainString()
                + ", Line total: " + getLineTotal().toPlainString();
    }
}
