package ua.com.alevel.secondlevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

public class IsValidString {
    public static void run(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println();
            String src;
            System.out.println("Enter the string->");
            src = reader.readLine();
            if (stringValidator(src)) System.out.println("String is valid.");
            else {
                System.out.println("String is invalid.");
            }
            System.out.println("Select 0 to break");
            src = reader.readLine();
            if (src.compareTo("0") == 0 || src.compareTo("q") == 0) break;
        }
    }

    public static boolean stringValidator(String str) {
        if (str == null) return true;
        Stack<Character> stack = new Stack<Character>();
        for (char bracket : str.toCharArray()) {
            if (bracket == '(' || bracket == '[' || bracket == '{') {
                stack.push(bracket);
            }
            if (bracket == ')' || bracket == ']' || bracket == '}') {
                int number = bracket == ')' ? 1 : 2;
                if (stack.isEmpty() || stack.pop() + number != bracket) {
                    System.out.println("String is invalid.");
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}