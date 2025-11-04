package com.onlinefooddelivery.entities;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private List<FoodItem> menu = new ArrayList<>();

    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<FoodItem> getMenu() { return menu; }

    public void addFoodItem(FoodItem foodItem) {
        if (foodItem != null) menu.add(foodItem);
    }

    public void removeFoodItem(int foodId) {
        menu.removeIf(it -> it.getId() == foodId);
    }

    public FoodItem findFoodById(int foodId) {
        for (FoodItem f : menu) {
            if (f.getId() == foodId) return f;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Restaurant{id=" + id + ", name='" + name + "'}";
    }
}
