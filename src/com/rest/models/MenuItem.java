package com.rest.models;

import java.math.BigDecimal;

public class MenuItem {
    private final int menuItemId;
    private final String name;
    private final BigDecimal price;
    private final String category;
    private final boolean veg;

    public MenuItem(int menuItemId, String name, BigDecimal price, String category, boolean veg) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.veg = veg;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public boolean isVeg() {
        return veg;
    }

    @Override
    public String toString() {
        return "Menu item ID: " + menuItemId
                + ", Name: " + name
                + ", Price: " + price.toPlainString()
                + ", Category: " + category
                + ", Vegetarian: " + veg;
    }
}
