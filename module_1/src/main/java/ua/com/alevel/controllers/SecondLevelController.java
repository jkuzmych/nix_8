package ua.com.alevel.controllers;

import ua.com.alevel.ModuleRun;
import ua.com.alevel.secondlevel.BinaryTreeLength;
import ua.com.alevel.secondlevel.IsValidString;

import java.io.BufferedReader;
import java.io.IOException;

public class SecondLevelController {

    public static void run(BufferedReader reader) {
        preview();
        String event;
        try {
            while (true) {
                System.out.println("So, your choice from second level is(1-2):");
                if ((event = reader.readLine()) == null) break;
                switch (event) {
                    case "1": {
                        IsValidString.run(reader);
                    }
                    break;
                    case "2": {
                        new BinaryTreeLength().run(reader);
                    }
                    break;
                    case "0": {
                        ModuleRun.run();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void preview() {
        System.out.println("To start string validation task select 1");
        System.out.println("To start binary tree task select 2");
        System.out.println("To exit print 0");
        System.out.println();
    }
}