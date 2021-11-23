package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.OrderDao;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.dynamicarray.DynamicArray;
import ua.com.alevel.entity.Order;
import ua.com.alevel.entity.User;

public class UserService {
    private final UserDao userDao = new UserDao();
    private final OrderDao orderDao = new OrderDao();
    Logger loggerInfo = LoggerFactory.getLogger("info");

    public void create(User user) {
        loggerInfo.info("UserService.create");
        userDao.create(user);
    }

    public void update(User user) {
        loggerInfo.info("UserService.update");
        userDao.update(user);
    }

    public void delete(String id) {
        loggerInfo.info("UserService.delete, userId=" + id);
        DynamicArray userOrders = orderDao.findAllByUserId(id);
        for (int i = 0; i < userOrders.Size(); i++) {
            Order order = (Order) userOrders.getbyindex(i);
            loggerInfo.info("related post found, postId=" + order.getId());
            orderDao.delete(order.getId());
        }
        userDao.delete(id);
    }

    public User findById(String id) {
        loggerInfo.info("UserService.findById");
        return userDao.findById(id);
    }

    public DynamicArray findAll() {
        loggerInfo.info("UserService.findAll");
        return userDao.findAll();
    }
}
