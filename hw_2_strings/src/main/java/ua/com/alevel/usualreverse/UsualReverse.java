package ua.com.alevel.usualreverse;

import ua.com.alevel.reversestring.ReverseStringUtil;

import java.io.BufferedReader;
import java.io.IOException;

public class UsualReverse {
    public  static void run(BufferedReader reader)throws IOException {
        while (true) {
            System.out.println();
            System.out.print("Enter your string-> ");
            String src = reader.readLine();
            if(src.compareTo("0")==0)break;
            System.out.print("Save word order or not? true/false-> ");
            Boolean full = Boolean.parseBoolean(reader.readLine());

            System.out.println("result: " +  ReverseStringUtil.reverse(src, full));
        }
    }
}
