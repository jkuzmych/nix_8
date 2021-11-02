package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ua.com.alevel.controllers.FirstLevelController;
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
        System.out.println("To exit print 0");
        System.out.println();
    }
}