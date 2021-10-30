package ua.com.alevel.view;

import ua.com.alevel.dynamicarray.DynamicArray;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserController {
    private final UserService userService = new UserService();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("if you want create user, please enter 1");
        System.out.println("if you want update user, please enter 2");
        System.out.println("if you want delete user, please enter 3");
        System.out.println("if you want findById user, please enter 4");
        System.out.println("if you want findAll user, please enter 5");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                create(reader);
                break;
            case "2":
                update(reader);
                break;
            case "3":
                delete(reader);
                break;
            case "4":
                findById(reader);
                break;
            case "5":
                findAll(reader);
                break;
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("Create user");
        try {
            System.out.println("Please, enter your name");
            String name = reader.readLine();
            System.out.println("Please, enter your age");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            if (name.isBlank() || age < 0) System.out.println("Invalid data");
            else {
                User user = new User();
                user.setAge(age);
                user.setName(name);
                userService.create(user);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age. Must be number.");
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("Update user");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter your name");
            String name = reader.readLine();
            System.out.println("Please, enter your age");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);
            if (name.isBlank() || age < 0) System.out.println("Invalid data");
            else {
                User user = new User();
                user.setId(id);
                user.setAge(age);
                user.setName(name);
                userService.update(user);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age. Must be number.");
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("Delete user");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            userService.delete(id);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("Find user by id");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            User user = userService.findById(id);
            if (user != null) System.out.println("user = " + user);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("user not found");
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("Find all users");
        DynamicArray users = userService.findAll();
        if (users != null && users.getSize() != 0) {
            System.out.println(users);
        } else System.out.println("users is empty");
    }
}
