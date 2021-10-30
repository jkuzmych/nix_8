package ua.com.alevel.db;

import ua.com.alevel.dynamicarray.DynamicArray;
import ua.com.alevel.entity.User;

import java.util.UUID;

public class UserDB {

    private static UserDB instance;
    private final DynamicArray users;

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
        try {
            User current = findById(user.getId());
            current.setAge(user.getAge());
            current.setName(user.getName());
        } catch (Exception e) {
            System.out.println("");
        }
    }

    public void delete(String id) {
        try {
            users.delete(findPositionById(id));
        } catch (Exception e) {
            System.out.println("user is not found");
        }
    }

    public User findById(String id) {
        User user;
        try {
            user = (User) users.getbyindex(findPositionById(id));
        } catch (Exception e) {
            System.out.println("user is not found");
            user = null;
        }
        return user;
    }

    public DynamicArray findAll() {
        return users;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (findPositionById(id) != -1) {
            return generateId();
        }
        return id;
    }

    private int findPositionById(String id) {
        int position = -1;
        User user;
        for (int i = 0; i < users.getSize(); i++) {
            user = (User) users.getbyindex(i);
            if (user.getId().equals(id)) {
                position = i;
                break;
            }
        }
        return position;
    }
}