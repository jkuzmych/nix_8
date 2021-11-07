package ua.com.alevel.controllers;

import ua.com.alevel.ModuleRun;
import ua.com.alevel.firstlevel.KnightMove;
import ua.com.alevel.firstlevel.TriangleArea;
import ua.com.alevel.firstlevel.UniqueElements;

import java.io.BufferedReader;
import java.io.IOException;

public class FirstLevelController {

    public static void run(BufferedReader reader) {
        preview();
        String event;
        try {
            while (true) {
                System.out.println("So, your choice from first level is(1-3):");
                if ((event = reader.readLine()) == null) break;
                switch (event) {
                    case "1": {
                        UniqueElements.run(reader);
                    }
                    break;
                    case "2": {
                        KnightMove.run(reader);
                    }
                    break;
                    case "3": {
                        TriangleArea.run(reader);
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
        System.out.println("To start unique elements task select 1");
        System.out.println("To start knight move task select 2");
        System.out.println("To start triangle area task select 3");
        System.out.println("To exit print 0");
        System.out.println();
    }
}