package ua.com.alevel.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dynamicarray.DynamicArray;
import ua.com.alevel.entity.Order;

import java.util.UUID;

public class OrderDB {
    private static OrderDB instance;
    private final DynamicArray orders;
    Logger loggerInfo = LoggerFactory.getLogger("info");
    Logger loggerWarn = LoggerFactory.getLogger("info");

    private OrderDB() {
        orders = new DynamicArray();
    }

    public static OrderDB getInstance() {
        if (instance == null) {
            instance = new OrderDB();
        }
        return instance;
    }

    public void create(Order order) {
        order.setId(generateId());
        orders.add(order);
    }

    public void update(Order order) {
        Order current = findById(order.getId());
        if (current != null) {
            current.setName(order.getName());
            current.setAmount(order.getAmount());
        }
    }

    public void delete(String id) {
        try {
            orders.delete(findPositionById(id));
            loggerInfo.info("order removed, ordertId=" + id);
        } catch (Exception e) {
            System.out.println("order not found");
            loggerWarn.warn("order deleting failed, order not found, orderId=" + id);
        }
    }

    public Order findById(String id) {
        Order order;
        try {
            order = (Order) orders.getbyindex(findPositionById(id));
        } catch (Exception e) {
            System.out.println("order not found");
            order = null;
        }
        return order;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (findPositionById(id) != -1) {
            return generateId();
        }
        return id;
    }

    public DynamicArray findAll() {
        return orders;
    }

    public DynamicArray findAllByUserId(String userId) {
        Order order;
        DynamicArray userPosts = new DynamicArray();
        for (int i = 0; i < orders.Size(); i++) {
            order = (Order) orders.getbyindex(i);
            if (userId.equals(order.getUserId())) {
                userPosts.add(order);
            }
        }
        return userPosts;
    }

    private int findPositionById(String id) {
        int position = -1;
        Order order;
        for (int i = 0; i < orders.Size(); i++) {
            order = (Order) orders.getbyindex(i);
            if (order.getId().equals(id)) {
                position = i;
                break;
            }
        }
        return position;
    }
}