package ua.com.alevel.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dynamicarray.DynamicArray;
import ua.com.alevel.entity.User;

import java.util.UUID;

public class UserDB {
    private static UserDB instance;
    private final DynamicArray users;
    Logger loggerInfo = LoggerFactory.getLogger("info");
    Logger loggerWarn = LoggerFactory.getLogger("warn");

    private UserDB() {
        users = new DynamicArray();
    }

    public static UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(generateId());
        users.add(user);
    }

    public void update(User user) {
        User current = findById(user.getId());
        if (current != null) {
            current.setAge(user.getAge());
            current.setName(user.getName());
            loggerInfo.info("user updated, userId=" + user.getId());
        } else {
            loggerWarn.warn("user updating failed, user not found, userId=" + user.getId());
        }
    }

    public void delete(String id) {
        try {
            users.delete(findPositionById(id));
        } catch (Exception e) {
            System.out.println("user not found");
            loggerWarn.warn("user deleting failed, user not found, userId=" + id);
        }
    }

    public User findById(String id) {
        User user;
        try {
            user = (User) users.getbyindex(findPositionById(id));
        } catch (Exception e) {
            System.out.println("user not found");
            user = null;
        }
        return user;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (findPositionById(id) != -1) {
            return generateId();
        }
        return id;
    }

    public DynamicArray findAll() {
        return users;
    }

    private int findPositionById(String id) {
        int position = -1;
        User user;
        for (int i = 0; i < users.Size(); i++) {
            user = (User) users.getbyindex(i);
            if (user.getId().equals(id)) {
                position = i;
                break;
            }
        }
        return position;
    }


}