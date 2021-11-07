package ua.com.alevel;

import ua.com.alevel.controllers.FirstLevelController;
import ua.com.alevel.controllers.SecondLevelController;
import ua.com.alevel.thirdlevel.Life;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ModuleRun {

    public static void run() {
        preview();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String event;
        try {
            while (true) {
                System.out.println("So, your choice is(1-3):");
                if ((event = reader.readLine()) == null) break;
                switch (event) {
                    case "1": {
                        FirstLevelController.run(reader);
                    }
                    break;
                    case "2": {
                        SecondLevelController.run(reader);
                    }
                    break;
                    case "3": {
                        new Life().run(reader);
                    }
                    break;
                    case "0": {
                        System.exit(0);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void preview() {
        System.out.println("To check first level select 1");
        System.out.println("To check second level select 2");
        System.out.println("To check third level select 3");
        System.out.println("To exit print 0");
        System.out.println();
    }
}