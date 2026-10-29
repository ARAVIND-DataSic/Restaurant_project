package com.rest.repository;

import com.rest.models.MenuItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuItemRepository {
    private final List<MenuItem> menuItems = new ArrayList<>();
    private int nextMenuItemId = 1;

    public MenuItemRepository() {
        save("Orange Juice", new BigDecimal("80.00"), "Juice", true);
        save("Mango Juice", new BigDecimal("90.00"), "Juice", true);
        save("Watermelon Juice", new BigDecimal("70.00"), "Juice", true);
        save("Lime Juice", new BigDecimal("50.00"), "Juice", true);
        save("Veg Meals", new BigDecimal("180.00"), "Meals", true);
        save("Chicken Meals", new BigDecimal("240.00"), "Meals", false);
        save("Curd Rice", new BigDecimal("100.00"), "Meals", true);
        save("Idli (2 pcs)", new BigDecimal("60.00"), "South Indian", true);
        save("Masala Dosa", new BigDecimal("110.00"), "South Indian", true);
        save("Plain Dosa", new BigDecimal("90.00"), "South Indian", true);
        save("Pongal", new BigDecimal("100.00"), "South Indian", true);
        save("Medu Vada (2 pcs)", new BigDecimal("70.00"), "South Indian", true);
        save("Paneer Butter Masala", new BigDecimal("220.00"), "North Indian", true);
        save("Chicken Biryani", new BigDecimal("250.00"), "Biryani", false);
        save("French Fries", new BigDecimal("100.00"), "Snacks", true);
        save("Gulab Jamun (2 pcs)", new BigDecimal("80.00"), "Desserts", true);
    }

    public List<MenuItem> findAll() {
        return new ArrayList<>(menuItems);
    }

    public Optional<MenuItem> findById(int menuItemId) {
        return menuItems.stream()
                .filter(item -> item.getMenuItemId() == menuItemId)
                .findFirst();
    }

    public MenuItem save(String name, BigDecimal price, String category, boolean veg) {
        MenuItem menuItem = new MenuItem(nextMenuItemId++, name, price, category, veg);
        menuItems.add(menuItem);
        return menuItem;
    }
}
