package com.rest.CLI;

import com.rest.models.MenuItem;
import com.rest.services.MenuItemService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuItemCLI {
    private final MenuItemService service;
    private final Scanner scanner;

    public MenuItemCLI(MenuItemService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;
        while (running && scanner.hasNextLine()) {
            System.out.println("\n== MENU ITEM MANAGEMENT ==");
            System.out.println("1. View menu items");
            System.out.println("2. Add menu item");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            switch (scanner.nextLine().trim()) {
                case "1":
                    showMenuItems();
                    break;
                case "2":
                    addMenuItem();
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private void showMenuItems() {
        List<MenuItem> items = service.getAllMenuItems();
        if (items.isEmpty()) {
            System.out.println("No menu items found.");
            return;
        }

        Map<String, List<MenuItem>> itemsByCategory = new LinkedHashMap<>();
        for (MenuItem item : items) {
            itemsByCategory.computeIfAbsent(item.getCategory(), key -> new ArrayList<>())
                    .add(item);
        }

        System.out.println("\n== MENU CATALOG ==");
        for (Map.Entry<String, List<MenuItem>> category : itemsByCategory.entrySet()) {
            System.out.println("\n" + category.getKey().toUpperCase() + " MENU");
            System.out.printf("%-5s %-28s %12s %15s%n", "ID", "ITEM", "PRICE", "VEGETARIAN");
            System.out.println("---------------------------------------------------------------");
            for (MenuItem item : category.getValue()) {
                System.out.printf(
                        "%-5d %-28s %12s %15s%n",
                        item.getMenuItemId(),
                        item.getName(),
                        item.getPrice().toPlainString(),
                        item.isVeg() ? "Yes" : "No");
            }
        }
    }

    private void addMenuItem() {
        try {
            System.out.print("Enter item name: ");
            String name = scanner.nextLine();
            System.out.print("Enter price: ");
            BigDecimal price = new BigDecimal(scanner.nextLine().trim());
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            System.out.print("Is this item vegetarian? (yes/no): ");
            String vegInput = scanner.nextLine().trim();
            if (!"yes".equalsIgnoreCase(vegInput) && !"no".equalsIgnoreCase(vegInput)) {
                System.out.println("Please enter yes or no.");
                return;
            }
            MenuItem item = service.createMenuItem(
                    name, price, category, "yes".equalsIgnoreCase(vegInput));
            System.out.println("Menu item added: " + item);
        } catch (NumberFormatException exception) {
            System.out.println("Invalid price. Enter a decimal number.");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
