package ua.com.alevel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dynamicarray.DynamicArray;
import ua.com.alevel.entity.Order;
import ua.com.alevel.service.OrderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderController {
    private final OrderService orderService = new OrderService();
    Logger loggerError = LoggerFactory.getLogger("error");

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null && !position.equals("0")) {
                crud(position, reader);
            }
        } catch (IOException e) {
            loggerError.error("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println(" 1 - Make an order");
        System.out.println(" 2 - Update order");
        System.out.println(" 3 - Delete order");
        System.out.println(" 4 - Find order bu id");
        System.out.println(" 5 - Find all orders");
        System.out.println(" 6 - Find all orders by user");
        System.out.println(" 0 - Return back");
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll();
            case "6" -> findAllByUserId(reader);
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        try {
            System.out.print("name of item: ");
            String name = reader.readLine();
            System.out.print("amount of items: ");
            String amount = reader.readLine();
            System.out.print("user id: ");
            String userId = reader.readLine();
            if (userId.isBlank()) System.out.println("Invalid input");
            else {
                Order order = new Order();
                order.setName(name);
                order.setAmount(Integer.parseInt(amount));
                order.setUserId(userId);
                orderService.create(order);
            }
        } catch (IOException e) {
            loggerError.error("problem: = " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Amount must be an integer");
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.print("order id: ");
            String id = reader.readLine();
            System.out.print("new item name: ");
            String name = reader.readLine();
            System.out.print("userId: ");
            String userId = reader.readLine();
            if (id.isBlank() || userId.isBlank()) System.out.println("Invalid input");
            else {
                Order order = new Order();
                order.setId(id);
                order.setName(name);
                order.setAmount(Integer.parseInt(name));
                order.setUserId(userId);
                orderService.update(order);
            }
        } catch (IOException e) {
            loggerError.error("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.print("order id: ");
            String id = reader.readLine();
            if (id.isBlank()) System.out.println("id empty");
            else orderService.delete(id);
        } catch (IOException e) {
            loggerError.error("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.print("order id: ");
            String id = reader.readLine();
            if (id.isBlank()) System.out.println("id empty");
            else {
                Order order = orderService.findById(id);
                if (order != null) System.out.println("order = " + order);
            }
        } catch (IOException e) {
            loggerError.error("problem: = " + e.getMessage());
        }
    }

    private void findAll() {
        DynamicArray orders = orderService.findAll();
        if (orders != null && orders.Size() != 0) {
            System.out.println(orders);
        } else System.out.println("no orders yet");
    }

    private void findAllByUserId(BufferedReader reader) {
        try {
            System.out.print("user id: ");
            String userId = reader.readLine();
            if (userId.isBlank()) System.out.println("id empty");
            else {
                DynamicArray ordersByUserId = orderService.findAllByUserId(userId);
                if (ordersByUserId != null && ordersByUserId.Size() != 0) {
                    System.out.println(ordersByUserId);
                } else System.out.println("orders not found");
            }
        } catch (IOException e) {
            loggerError.error("problem: = " + e.getMessage());
        }
    }
}