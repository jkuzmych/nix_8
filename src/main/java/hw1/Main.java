package hw1;

import hw1.task1;
import hw1.task2;
import hw1.task3;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {

        System.out.println("1st task");
        task1 t1 = new task1();
        t1.run();

        System.out.println("2nd task");;
        task2 t2 = new task2();
        t2.run();

        System.out.println("3rd task");
        task3 t3 = new task3();
        t3.run();

    }
}