package com.rest.models;

import java.time.LocalDateTime;

public class Order {
    public enum Status {
        PLACED,
        PREPARING,
        SERVED,
        COMPLETED
    }

    private final int orderId;
    private final int tableId;
    private Status status;
    private final LocalDateTime orderedAt;

    public Order(int orderId, int tableId, Status status, LocalDateTime orderedAt) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.status = status;
        this.orderedAt = orderedAt;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId
                + ", Table ID: " + tableId
                + ", Status: " + status
                + ", Ordered at: " + orderedAt;
    }
}
