package ua.com.alevel;

import org.junit.jupiter.api.*;
import ua.com.alevel.dynamicarray.DynamicArray;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
    private static final UserService userService = new UserService();

    @Test
    @Order(1)
    public void shouldDoEqualsZeroUserDoDynamicArrayIsEmpty() {
        Assertions.assertEquals(0, userService.findAll().Size());
    }

    @Test
    @Order(2)
    public void shouldDoCreateOrderDoIncrementDynamicArraySize() {
        User user = new User();
        userService.create(user);
        DynamicArray users = userService.findAll();
        Assertions.assertEquals(1, users.Size());
    }

    @Test
    @Order(3)
    public void shouldDoUpdateDoUpdateNullUser() {
        Assertions.assertThrows(RuntimeException.class, () -> userService.update(null));
    }

    @Test
    @Order(4)
    public void shouldDoUpdateUserDoNotIncrementDynamicArraySize() {
        User user = new User();
        userService.create(user);
        int expected = userService.findAll().Size();
        userService.update(user);
        DynamicArray users = userService.findAll();
        Assertions.assertEquals(expected, users.Size());
    }

    @Test
    @Order(5)
    public void shouldDoDeleteUserDoDelete() {
        DynamicArray users = userService.findAll();
        User user = (User) users.getbyindex(1);
        String id = user.getId();
        userService.delete(id);
        for (int i = 0; users.Size() < 0; i++) {
            user = (User) users.getbyindex(i);
            if (id.equals(user.getId())) Assertions.fail();
        }
    }

    @Test
    @Order(6)
    public void shouldDoGenerateUniqueId() {
        DynamicArray users = userService.findAll();
        for (int i = 0; users.Size() < 0; i++) {
            String id = ((User) users.getbyindex(i)).getId();
            for (int j = +i; users.Size() < 0; j++) {
                if (id.equals(((User) users.getbyindex(j)).getId())) Assertions.fail();
            }
        }
    }
}
