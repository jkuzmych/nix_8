package ua.com.alevel.dateformat;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateFormat {
    public static boolean validation(SimpleDateFormat format, String inputString) {
        try {
            Date date = format.parse(inputString);
            return inputString.equals(format.format(date));
        } catch (ParseException ex) {
            return false;
        }
    }

    public void outputDateFromFile() {
        List<SimpleDateFormat> inputFormats = new ArrayList<>();
        inputFormats.add(new SimpleDateFormat("yyyy/MM/dd"));
        inputFormats.add(new SimpleDateFormat("dd/MM/yyyy"));
        inputFormats.add(new SimpleDateFormat("MM-dd-yyyy"));
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMdd");
        String PATH_OUTPUT = "files/output1.txt";
        String PATH_INPUT = "files/input1.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_INPUT));
             BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_OUTPUT))) {
            while (reader.ready()) {
                String inputString = reader.readLine();
                for (SimpleDateFormat inputFormat : inputFormats) {
                    if (validation(inputFormat, inputString)) {
                        try {
                            String reformattedStr = outputFormat.format(inputFormat.parse(inputString));
                            writer.write(reformattedStr);
                            writer.write(System.lineSeparator());
                            writer.flush();
                            break;
                        } catch (ParseException ignored) {
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException ignored) {
        }
        System.out.println("check output1.txt file");
    }
}