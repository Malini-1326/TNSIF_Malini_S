package com.onlinefooddelivery.entities;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private int orderId;
    private Customer customer;
    private Map<FoodItem, Integer> items = new HashMap<>();
    private String status = "Pending";
    private DeliveryPerson deliveryPerson;
    private String deliveryAddress;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Map<FoodItem, Integer> getItems() { return items; }
    public String getStatus() { return status; }
    public DeliveryPerson getDeliveryPerson() { return deliveryPerson; }
    public String getDeliveryAddress() { return deliveryAddress; }

    public void addItem(FoodItem f, int qty) {
        if (f == null || qty <= 0) return;
        items.put(f, items.getOrDefault(f, 0) + qty);
    }

    public void setDeliveryPerson(DeliveryPerson dp) {
        this.deliveryPerson = dp;
        if (dp != null) this.status = "Assigned";
    }

    public void setDeliveryAddress(String address) {
        this.deliveryAddress = address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order{orderId=" + orderId + ", customer=" + customer.getUsername() + ", items={");
        boolean first = true;
        for (Map.Entry<FoodItem, Integer> e : items.entrySet()) {
            if (!first) sb.append(", ");
            sb.append(e.getKey().getName() + "=" + e.getValue());
            first = false;
        }
        sb.append("}, status='" + status + "', deliveryPerson=" + (deliveryPerson != null ? deliveryPerson.getName() : "Not Assigned") + "}");
        return sb.toString();
    }
}
