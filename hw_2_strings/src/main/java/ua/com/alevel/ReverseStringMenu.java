package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ua.com.alevel.usualreverse.UsualReverse;
import ua.com.alevel.reversewithsubword.ReverseWithSubword;
import ua.com.alevel.reversewithindexes.ReverseWithIndexes;
import ua.com.alevel.reversewithchars.ReverseWithChars;

public class ReverseStringMenu {
    public static void run() {
        preview();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String event;
        try {
            while (true) {
                System.out.println("So, your choice is(1-4):");
                if ((event = reader.readLine()) == null) break;
                switch (event) {
                    case "1": {
                        UsualReverse.run(reader);
                    }
                    break;
                    case "2": {
                        ReverseWithSubword.run(reader);
                    }
                    break;
                    case "3": {
                        ReverseWithIndexes.run(reader);
                    }
                    break;
                    case "4": {
                        ReverseWithChars.run(reader);
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
        System.out.println("To make usual reverse print 1");//save word order?
        System.out.println("To make reverse of subword in the string print 2");
        System.out.println("To make  reverse in the selected range of indexes print 3");
        System.out.println("To make reverse between selected symbols print 4");
        System.out.println("To finish");
        System.out.println();
    }
}

