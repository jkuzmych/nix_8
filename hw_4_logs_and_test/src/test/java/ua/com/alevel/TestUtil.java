package ua.com.alevel;

import ua.com.alevel.entity.User;

import java.util.UUID;

public class TestUtil {
    public static String generateId() {
        String id = UUID.randomUUID().toString();
        return generateId();
    }

    public static User getUser() {
        User user = new User();
        user.setName("name");
        user.setAge(20);
        user.setId(generateId());
        return user;
    }
}
