package com.onlinefooddelivery.entities;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<FoodItem, Integer> items = new HashMap<>();

    public void addItem(FoodItem foodItem, int quantity) {
        if (foodItem == null || quantity <= 0) return;
        items.put(foodItem, items.getOrDefault(foodItem, 0) + quantity);
    }

    public void removeItem(FoodItem foodItem) {
        items.remove(foodItem);
    }

    public Map<FoodItem, Integer> getItems() {
        return items;
    }

    public double getTotalCost() {
        double total = 0;
        for (Map.Entry<FoodItem, Integer> e : items.entrySet()) {
            total += e.getKey().getPrice() * e.getValue();
        }
        return total;
    }

    @Override
    public String toString() {
        if (items.isEmpty()) return "Cart is empty.";
        StringBuilder sb = new StringBuilder();
        double total = 0;
        for (Map.Entry<FoodItem, Integer> e : items.entrySet()) {
            double cost = e.getKey().getPrice() * e.getValue();
            sb.append("Food Item: " + e.getKey().getName() +
                      ", Quantity: " + e.getValue() +
                      ", Cost: Rs. " + cost + "\n");
            total += cost;
        }
        sb.append("Total Cost: Rs. " + total);
        return sb.toString();
    }
}
