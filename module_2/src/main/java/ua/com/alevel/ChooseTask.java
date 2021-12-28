package ua.com.alevel;

import ua.com.alevel.dateformat.DateFormat;
import ua.com.alevel.graphs.Graphs;
import ua.com.alevel.uniquename.UniqueName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChooseTask {
    private static void ModuleInterface(BufferedReader reader) {
        String position;
        try {
            runModuleNavigation();
            while ((position = reader.readLine()) != null) {
                levelOfModule(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                levelOfModule(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private static void runModuleNavigation() {
        System.out.println();
        System.out.println("to output dates from a file, please enter 1");
        System.out.println("to output names from a file, please enter 2");
        System.out.println("to output the shortest path from a file, please enter 3");
        System.out.println("tp exit, enter 0");
        System.out.println();
    }

    private static void levelOfModule(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                new DateFormat().outputDateFromFile();
                break;
            case "2":
                try {
                    new UniqueName().outputUniqueNameFromFile();
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "3":
                new Graphs().ioFile();
                break;
            case "0":
                System.exit(0);
                break;
        }
        runModuleNavigation();
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ModuleInterface(reader);
    }
}
