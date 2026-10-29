package com.rest.services;

import com.rest.models.MenuItem;
import com.rest.repository.MenuItemRepository;
import java.math.BigDecimal;
import java.util.List;

public class MenuItemService {
    private final MenuItemRepository repository;

    public MenuItemService(MenuItemRepository repository) {
        this.repository = repository;
    }

    public List<MenuItem> getAllMenuItems() {
        return repository.findAll();
    }

    public MenuItem createMenuItem(String name, BigDecimal price, String category, boolean veg) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Menu item name cannot be empty.");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Menu item category cannot be empty.");
        }
        if (price == null || price.signum() < 0) {
            throw new IllegalArgumentException("Price must be zero or greater.");
        }
        return repository.save(name.trim(), price, category.trim(), veg);
    }
}
