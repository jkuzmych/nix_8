package ua.com.alevel.endoflessons;

import java.io.BufferedReader;
import java.io.IOException;

public class EndOfLessons {

    public void run(BufferedReader reader) throws IOException {
        System.out.println("Task end of lessons run");
        while (true) {
            System.out.println();
            System.out.print("Enter the number of lesson(from 1 to 10):");
            String st = reader.readLine();
            if (st.compareTo("0") == 0) break;
            int x;
            while (true) {
                try {
                    x = Integer.parseInt(st);
                } catch (Exception e) {
                    System.out.println("Could not parse input, please try again.");
                    st = reader.readLine();
                    continue;
                }
                break;
            }
            if (x <= 10 && x > 0) {
                count(x);
            } else System.out.println("Invalid number. Try again.");

        }
    }

    public void count(int x) {
        x = x * 45 + (x / 2) * 5 + ((x + 1) / 2 - 1) * 15;
        System.out.println(9 + x / 60 + " " + x % 60);
    }
}


