package ua.com.alevel.firstlevel;

import java.io.BufferedReader;
import java.io.IOException;

public class UniqueElements {
    public static void run(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println();
            int size;
            int[] array;
            System.out.println("Enter quantity of the elements");
            size = Integer.parseInt(reader.readLine());
            if (size <= 0) {
                System.out.println("Incorrect value entered");
                return;
            }
            array = new int[size];
            for (int i = 0; i < size; i++) {
                System.out.println("Enter element №" + (i + 1));
                array[i] = Integer.parseInt(reader.readLine());
            }
            System.out.print("Your array->");
            for (int i = 0; i < size; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
            System.out.println("Amount of unique elements is " + amountOfUniqueElements(array));
            System.out.println("Select Q/q to break");
            String src=reader.readLine();
            if (src.compareTo("Q") == 0 || src.compareTo("q") == 0) break;
        }
    }
    public static int amountOfUniqueElements(int[] array) {
        int amount = 0;
        boolean b;
        for (int i = 0; i < array.length; i++) {
            b = false;
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    b = true;
                    break;
                }
            }
            if (!b) amount++;
        }
        return amount;
    }
}