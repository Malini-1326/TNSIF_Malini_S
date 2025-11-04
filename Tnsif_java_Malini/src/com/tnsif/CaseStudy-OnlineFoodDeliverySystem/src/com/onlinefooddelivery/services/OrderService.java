package com.onlinefooddelivery.services;

import com.onlinefooddelivery.entities.*;
import java.util.List;

public class OrderService {
    private List<Order> orders;
    private int nextOrderId = 1;

    public OrderService(List<Order> orders) {
        this.orders = orders;
    }

    public Order createOrder(Customer customer) {
        Order order = new Order(nextOrderId++, customer);
        // copy cart items
        for (var e : customer.getCart().getItems().entrySet()) {
            order.addItem(e.getKey(), e.getValue());
        }
        orders.add(order);
        // clear customer's cart
        customer.getCart().getItems().clear();
        return order;
    }

    public Order findOrderById(int id) {
        for (Order o : orders) if (o.getOrderId() == id) return o;
        return null;
    }

    public List<Order> getAllOrders() {
        return orders;
    }
}
