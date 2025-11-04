package com.onlinefooddelivery.apllication;

import com.onlinefooddelivery.entities.*;
import com.onlinefooddelivery.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodDeliveryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // central orders list shared across services
        List<Order> orders = new ArrayList<>();
        OrderService orderService = new OrderService(orders);
        AdminService adminService = new AdminService(orders);
        CustomerService customerService = new CustomerService(adminService, orderService);

        boolean running = true;
        while (running) {
            System.out.println("\n1. Admin Menu\n2. Customer Menu\n3. Exit\nChoose an option: ");
            int mainChoice = readInt(sc);
            switch (mainChoice) {
                case 1:
                    adminMenu(sc, adminService);
                    break;
                case 2:
                    customerMenu(sc, customerService);
                    break;
                case 3:
                    System.out.println("Exiting application.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }

    private static void adminMenu(Scanner sc, AdminService adminService) {
        boolean go = true;
        while (go) {
            System.out.println("\nAdmin Menu:\n1. Add Restaurant\n2. Add Food Item to Restaurant\n3. Remove Food Item from Restaurant\n4. View Restaurants and Menus\n5. View Orders\n6. Add Delivery Person\n7. Assign Delivery Person to Order\n8. Exit\nChoose an option: ");
            int ch = readInt(sc);
            switch (ch) {
                case 1:
                    System.out.print("Enter Restaurant ID: ");
                    int rid = readInt(sc);
                    System.out.print("Enter Restaurant Name: ");
                    String rname = readLine(sc);
                    adminService.addRestaurant(rid, rname);
                    System.out.println("Restaurant added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Restaurant ID: ");
                    int rId = readInt(sc);
                    System.out.print("Enter Food Item ID: ");
                    int fid = readInt(sc);
                    System.out.print("Enter Food Item Name: ");
                    String fname = readLine(sc);
                    System.out.print("Enter Food Item Price: ");
                    double price = readDouble(sc);
                    boolean added = adminService.addFoodItemToRestaurant(rId, new FoodItem(fid, fname, price));
                    System.out.println(added ? "Food item added successfully!" : "Failed to add food item (restaurant not found).");
                    break;
                case 3:
                    System.out.print("Enter Restaurant ID: ");
                    int remRid = readInt(sc);
                    System.out.print("Enter Food Item ID to remove: ");
                    int remFid = readInt(sc);
                    boolean removed = adminService.removeFoodItemFromRestaurant(remRid, remFid);
                    System.out.println(removed ? "Food item removed successfully!" : "Failed to remove (restaurant not found).");
                    break;
                case 4:
                    adminService.viewRestaurantsAndMenus();
                    break;
                case 5:
                    adminService.viewOrders();
                    break;
                case 6:
                    System.out.print("Enter Delivery Person ID: ");
                    int dpId = readInt(sc);
                    System.out.print("Enter Delivery Person Name: ");
                    String dpName = readLine(sc);
                    System.out.print("Enter Contact No.: ");
                    long dpContact = readLong(sc);
                    adminService.addDeliveryPerson(dpId, dpName, dpContact);
                    System.out.println("Delivery person added successfully!");
                    break;
                case 7:
                    System.out.print("Enter Order ID: ");
                    int oId = readInt(sc);
                    System.out.print("Enter Delivery Person ID: ");
                    int dId = readInt(sc);
                    boolean ok = adminService.assignDeliveryPersonToOrder(oId, dId);
                    System.out.println(ok ? "Delivery person assigned to order successfully!" : "Failed to assign (order or delivery person not found).");
                    break;
                case 8:
                    System.out.println("Exiting Admin Module");
                    go = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void customerMenu(Scanner sc, CustomerService customerService) {
        boolean go = true;
        while (go) {
            System.out.println("\nCustomer Menu:\n1. Add Customer\n2. View Food Items\n3. Add Food to Cart\n4. View Cart\n5. Place Order\n6. View Orders\n7. Exit\nChoose an option: ");
            int ch = readInt(sc);
            switch (ch) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int uid = readInt(sc);
                    System.out.print("Enter Username: ");
                    String uname = readLine(sc);
                    System.out.print("Enter Contact No.: ");
                    long contact = readLong(sc);
                    customerService.addCustomer(uid, uname, contact);
                    System.out.println("Customer created successfully!");
                    break;
                case 2:
                    customerService.viewFoodItems();
                    break;
                case 3:
                    System.out.print("Enter Customer ID: ");
                    int cId = readInt(sc);
                    System.out.print("Enter Restaurant ID: ");
                    int restId = readInt(sc);
                    System.out.print("Enter Food Item ID: ");
                    int foodId = readInt(sc);
                    System.out.print("Enter Quantity: ");
                    int qty = readInt(sc);
                    boolean added = customerService.addFoodToCart(cId, restId, foodId, qty);
                    System.out.println(added ? "Food item added to cart!" : "Failed to add to cart (check ids).");
                    break;
                case 4:
                    System.out.print("Enter Customer ID: ");
                    int viewCid = readInt(sc);
                    customerService.viewCart(viewCid);
                    break;
                case 5:
                    System.out.print("Enter Customer ID: ");
                    int placeCid = readInt(sc);
                    Order order = customerService.placeOrder(placeCid);
                    if (order != null) {
                        System.out.println("Order placed successfully! Your order ID is: " + order.getOrderId());
                    } else {
                        System.out.println("Failed to place order (customer not found or cart empty).");
                    }
                    break;
                case 6:
                    System.out.print("Enter Customer ID: ");
                    int viewOrdersCid = readInt(sc);
                    customerService.viewOrders(viewOrdersCid);
                    break;
                case 7:
                    System.out.println("Exiting Customer Module");
                    go = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // helpers for safe input reading
    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next(); System.out.print("Please enter a number: ");
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }

    private static long readLong(Scanner sc) {
        while (!sc.hasNextLong()) {
            sc.next(); System.out.print("Please enter a valid long number: ");
        }
        long v = sc.nextLong();
        sc.nextLine();
        return v;
    }

    private static double readDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            sc.next(); System.out.print("Please enter a valid number: ");
        }
        double v = sc.nextDouble();
        sc.nextLine();
        return v;
    }

    private static String readLine(Scanner sc) {
        String line = sc.nextLine();
        if (line == null) return "";
        return line.trim();
    }
}
