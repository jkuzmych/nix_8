package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.OrderDao;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.dynamicarray.DynamicArray;
import ua.com.alevel.entity.Order;

public class OrderService {
    private final OrderDao orderDao = new OrderDao();
    private final UserDao userDao = new UserDao();
    Logger loggerInfo = LoggerFactory.getLogger("info");
    Logger loggerWarn = LoggerFactory.getLogger("info");

    public void create(Order order) {
        if (userDao.findById(order.getUserId()) != null) {
            loggerInfo.info("OrderService.delete, id=" + order.getUserId());
            orderDao.create(order);
        } else {
            loggerWarn.warn("order creation failed, user not found, userId=" + order.getUserId());
        }
    }

    public void update(Order order) {
        if (userDao.findById(order.getUserId()) != null) {
            orderDao.update(order);
            loggerInfo.info("order updated, orderId=" + order.getId() + ", userId=" + order.getUserId());
        } else {
            loggerWarn.warn("order updating failed, user not found, orderId=" + order.getId()
                    + ", userId=" + order.getUserId());
        }
    }

    public void delete(String id) {
        loggerInfo.info("OrderService.delete, id=" + id);
        orderDao.delete(id);
    }

    public Order findById(String id) {
        loggerInfo.info("OrderService.findById, id=" + id);
        return orderDao.findById(id);
    }

    public DynamicArray findAll() {
        loggerInfo.info("OrderService.findAll");
        return orderDao.findAll();
    }

    public DynamicArray findAllByUserId(String userId) {
        loggerInfo.info("OrderService.findAllByUserId, userId=" + userId);
        return orderDao.findAllByUserId(userId);
    }
}
