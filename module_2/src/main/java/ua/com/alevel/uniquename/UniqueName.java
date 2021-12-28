package ua.com.alevel.uniquename;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UniqueName {

    public void outputUniqueNameFromFile() {
        Map<String, Integer> map = new HashMap<>();
        String PATH_OUTPUT = "files/output2.txt";
        String PATH_INPUT = "files/input2.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_INPUT));
             BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_OUTPUT))) {
            while (reader.ready()) {
                String inputString = reader.readLine();
                if (inputString.matches("[A-Za-z0-9_]+")) {
                    if (map.containsKey(inputString)) {
                        map.put(inputString, map.get(inputString) + 1);
                    } else {
                        map.put(inputString, 0);
                    }
                }
            }
            String result = "no unique items were found";
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 0) {
                    result = entry.getKey();
                    break;
                }
            }
            writer.write(result);
            writer.write(System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("check output2.txt file");
    }
}
