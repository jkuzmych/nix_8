package ua.com.alevel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    public void run() {
        Logger loggerError = LoggerFactory.getLogger("error");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                switch (position) {
                    case "1" -> new UserController().run();
                    case "2" -> new OrderController().run();
                    case "0" -> System.exit(0);
                }
                runNavigation();
            }
        } catch (IOException e) {
            loggerError.error("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println("1 - User menu  2 - Make on order menu | 0 - Exit");
    }
}