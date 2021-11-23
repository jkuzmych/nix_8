package ua.com.alevel.dao;

import ua.com.alevel.db.OrderDB;
import ua.com.alevel.dynamicarray.DynamicArray;
import ua.com.alevel.entity.Order;

public class OrderDao {
    public void create(Order order) {
        OrderDB.getInstance().create(order);
    }

    public void update(Order order) {
        OrderDB.getInstance().update(order);
    }

    public void delete(String id) {
        OrderDB.getInstance().delete(id);
    }

    public Order findById(String id) {
        return OrderDB.getInstance().findById(id);
    }

    public DynamicArray findAll() {
        return OrderDB.getInstance().findAll();
    }

    public DynamicArray findAllByUserId(String userId) {
        return OrderDB.getInstance().findAllByUserId(userId);
    }
}