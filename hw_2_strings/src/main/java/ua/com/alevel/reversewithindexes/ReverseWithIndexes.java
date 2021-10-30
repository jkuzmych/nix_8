package ua.com.alevel.reversewithindexes;

import ua.com.alevel.reversestring.ReverseStringUtil;

import java.io.BufferedReader;
import java.io.IOException;

public class ReverseWithIndexes {
    public static void run(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println();
            System.out.print("Enter your string-> ");
            String src = reader.readLine();
            if (src.compareTo("0") == 0) break;
            int firstindex = 0, lastindex = 0;
            System.out.print("Enter first index-> ");
            String f = reader.readLine();
            System.out.print("Enter second index-> ");
            String l = reader.readLine();
            if (Character.isDigit((l.charAt(0))) || (Character.isDigit((f.charAt(0))))) {
                firstindex = Integer.parseInt(f);
                lastindex = Integer.parseInt(l);
            } else System.out.println(src + "indexes are not digits");
            System.out.print("Save word order or not? true/false-> ");
            Boolean full = Boolean.parseBoolean(reader.readLine());
            System.out.println("result: " + ReverseStringUtil.reverse(src, firstindex, lastindex, full));
        }
    }
}