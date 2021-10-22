package ua.com.alevel.reversewithchars;
import ua.com.alevel.reversestring.ReverseStringUtil;

import java.io.BufferedReader;
import java.io.IOException;
public class ReverseWithChars {
    public static void run(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println();
            System.out.print("Enter your string-> ");
            String src = reader.readLine();
            System.out.print("Enter first symbol-> ");
            char firstindex = (reader.readLine()).charAt(0);
            System.out.print("Enter second symbol-> ");
            char lastindex = (reader.readLine()).charAt(0);

            if (src.compareTo("0") == 0) break;

            System.out.print("Save word order or not? true/false-> ");
            Boolean full = Boolean.parseBoolean(reader.readLine());

            System.out.println("result: " + ReverseStringUtil.reverse(src, firstindex, lastindex, full));
        }
    }
}
