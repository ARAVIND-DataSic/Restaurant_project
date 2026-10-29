package com.rest;
import com.rest.CLI.MenuItemCLI;
import com.rest.CLI.OrderCLI;
import com.rest.CLI.PaymentCLI;
import com.rest.CLI.TableCLI;
import com.rest.repository.MenuItemRepository;
import com.rest.repository.OrderItemRepository;
import com.rest.repository.OrderRepository;
import com.rest.repository.PaymentRepository;
import com.rest.repository.TableRepository;
import com.rest.services.MenuItemService;
import com.rest.services.OrderItemService;
import com.rest.services.OrderService;
import com.rest.services.PaymentService;
import com.rest.services.TableService;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TableRepository tableRepository = new TableRepository();
        OrderRepository orderRepository = new OrderRepository();
        OrderItemRepository orderItemRepository = new OrderItemRepository();
        MenuItemRepository menuItemRepository = new MenuItemRepository();
        PaymentService paymentService = new PaymentService(new PaymentRepository());

        TableService tableService = new TableService(tableRepository);
        OrderService orderService = new OrderService(orderRepository, tableRepository);
        MenuItemService menuItemService = new MenuItemService(menuItemRepository);
        OrderItemService orderItemService = new OrderItemService(
                orderItemRepository, orderRepository, menuItemRepository);

        boolean running = true;
        while (running && scanner.hasNextLine()) {
            System.out.println("== RESTAURANT MANAGEMENT ==");
            System.out.println("1. Manage tables");
            System.out.println("2. Manage orders");
            System.out.println("3. Manage menu items");
            System.out.println("4. Manage payments");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("Please enter a choice from 1 to 5.");
                continue;
            }

            switch (input.trim()) {
                case "1":
                    new TableCLI(tableService, scanner).start();
                    break;
                case "2":
                    new OrderCLI(orderService, orderItemService, scanner).start();
                    break;
                case "3":
                    new MenuItemCLI(menuItemService, scanner).start();
                    break;
                case "4":
                    new PaymentCLI(paymentService, scanner).start();
                    break;
                case "5":
                    running = false;
                    System.out.println("THANK YOU");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }
}