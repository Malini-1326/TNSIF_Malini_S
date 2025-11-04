package com.onlinefooddelivery.services;

import com.onlinefooddelivery.entities.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();
    private AdminService adminService; // to read restaurants/menus
    private OrderService orderService;

    public CustomerService(AdminService adminService, OrderService orderService) {
        this.adminService = adminService;
        this.orderService = orderService;
    }

    public void addCustomer(int userId, String username, long contactNo) {
        customers.add(new Customer(userId, username, contactNo));
    }

    public Customer findCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getUserId() == id) return c;
        }
        return null;
    }

    public void viewFoodItems() {
        adminService.viewRestaurantsAndMenus();
    }

    public boolean addFoodToCart(int customerId, int restaurantId, int foodId, int qty) {
        Customer c = findCustomerById(customerId);
        if (c == null) return false;
        Restaurant r = adminService.findRestaurantById(restaurantId);
        if (r == null) return false;
        FoodItem f = r.findFoodById(foodId);
        if (f == null) return false;
        c.getCart().addItem(f, qty);
        return true;
    }

    public void viewCart(int customerId) {
        Customer c = findCustomerById(customerId);
        if (c == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.println("Cart:\n" + c.getCart().toString());
    }

    public Order placeOrder(int customerId) {
        Customer c = findCustomerById(customerId);
        if (c == null) return null;
        if (c.getCart().getItems().isEmpty()) return null;
        return orderService.createOrder(c);
    }

    public void viewOrders(int customerId) {
        Customer c = findCustomerById(customerId);
        if (c == null) {
            System.out.println("Customer not found.");
            return;
        }
        boolean any = false;
        for (Order o : orderService.getAllOrders()) {
            if (o.getCustomer().getUserId() == customerId) {
                System.out.println(o);
                any = true;
            }
        }
        if (!any) System.out.println("No orders found for this customer.");
    }
}
