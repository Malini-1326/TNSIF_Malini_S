package com.onlinefooddelivery.services;

import com.onlinefooddelivery.entities.*;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private List<Restaurant> restaurants = new ArrayList<>();
    private List<DeliveryPerson> deliveryPersons = new ArrayList<>();
    private List<Order> orders; // reference to central orders list

    public AdminService(List<Order> orders) {
        this.orders = orders;
    }

    public void addRestaurant(int id, String name) {
        restaurants.add(new Restaurant(id, name));
    }

    public Restaurant findRestaurantById(int id) {
        for (Restaurant r : restaurants) {
            if (r.getId() == id) return r;
        }
        return null;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public boolean addFoodItemToRestaurant(int restaurantId, FoodItem item) {
        Restaurant r = findRestaurantById(restaurantId);
        if (r == null) return false;
        r.addFoodItem(item);
        return true;
    }

    public boolean removeFoodItemFromRestaurant(int restaurantId, int foodId) {
        Restaurant r = findRestaurantById(restaurantId);
        if (r == null) return false;
        r.removeFoodItem(foodId);
        return true;
    }

    public void viewRestaurantsAndMenus() {
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }
        for (Restaurant r : restaurants) {
            System.out.println("Restaurant ID: " + r.getId() + ", Name: " + r.getName());
            if (r.getMenu().isEmpty()) {
                System.out.println("  No items.");
            } else {
                for (FoodItem f : r.getMenu()) {
                    System.out.println("  - Food Item ID: " + f.getId() + ", Name: " + f.getName() + ", Price: Rs. " + f.getPrice());
                }
            }
        }
    }

    public void addDeliveryPerson(int id, String name, long contact) {
        deliveryPersons.add(new DeliveryPerson(id, name, contact));
    }

    public DeliveryPerson findDeliveryPersonById(int id) {
        for (DeliveryPerson d : deliveryPersons) {
            if (d.getDeliveryPersonId() == id) return d;
        }
        return null;
    }

    public List<DeliveryPerson> getDeliveryPersons() {
        return deliveryPersons;
    }

    public boolean assignDeliveryPersonToOrder(int orderId, int deliveryPersonId) {
        Order order = null;
        for (Order o : orders) {
            if (o.getOrderId() == orderId) { order = o; break; }
        }
        DeliveryPerson dp = findDeliveryPersonById(deliveryPersonId);
        if (order == null || dp == null) return false;
        order.setDeliveryPerson(dp);
        return true;
    }

    public void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders.");
            return;
        }
        for (Order o : orders) {
            System.out.println(o);
        }
    }
}
