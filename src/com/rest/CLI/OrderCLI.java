package com.rest.CLI;

import com.rest.models.Order;
import com.rest.models.OrderItem;
import com.rest.services.OrderItemService;
import com.rest.services.OrderService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class OrderCLI {
    private final OrderService service;
    private final OrderItemService orderItemService;
    private final Scanner scanner;

    public OrderCLI(OrderService service, OrderItemService orderItemService, Scanner scanner) {
        this.service = service;
        this.orderItemService = orderItemService;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;
        while (running && scanner.hasNextLine()) {
            System.out.println("\n== RESTAURANT ORDER MANAGEMENT ==");
            System.out.println("1. Place order");
            System.out.println("2. View all orders");
            System.out.println("3. Update order status");
            System.out.println("4. Add menu item to order");
            System.out.println("5. View order details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    placeOrder();
                    break;
                case "2":
                    showOrders();
                    break;
                case "3":
                    updateStatus();
                    break;
                case "4":
                    addItem();
                    break;
                case "5":
                    showOrderDetails();
                    break;
                case "6":
                    running = false;
                    System.out.println("THANK YOU");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }

    private void placeOrder() {
        System.out.print("Enter table ID: ");
        try {
            int tableId = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Order placed: " + service.placeOrder(tableId));
        } catch (NumberFormatException exception) {
            System.out.println("Invalid table ID. Enter a whole number.");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void showOrders() {
        List<Order> orders = service.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private void updateStatus() {
        try {
            System.out.print("Enter order ID: ");
            int orderId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter status (PLACED, PREPARING, SERVED, COMPLETED): ");
            String status = scanner.nextLine();
            Optional<Order> order = service.updateStatus(orderId, status);
            if (order.isPresent()) {
                System.out.println("Order updated: " + order.get());
            } else {
                System.out.println("Order " + orderId + " was not found.");
            }
        } catch (NumberFormatException exception) {
            System.out.println("Invalid order ID. Enter a whole number.");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void addItem() {
        try {
            System.out.print("Enter order ID: ");
            int orderId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter menu item ID: ");
            int menuItemId = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Order item added: "
                    + orderItemService.addItem(orderId, menuItemId, quantity));
        } catch (NumberFormatException exception) {
            System.out.println("Order ID, menu item ID, and quantity must be whole numbers.");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void showOrderDetails() {
        try {
            System.out.print("Enter order ID: ");
            int orderId = Integer.parseInt(scanner.nextLine().trim());
            List<OrderItem> items = orderItemService.getItemsForOrder(orderId);
            if (items.isEmpty()) {
                System.out.println("This order has no items yet.");
                return;
            }

            BigDecimal total = BigDecimal.ZERO;
            for (OrderItem item : items) {
                System.out.println(item);
                total = total.add(item.getLineTotal());
            }
            System.out.println("Order total: " + total.toPlainString());
        } catch (NumberFormatException exception) {
            System.out.println("Order ID must be a whole number.");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
