package hw1;

import java.util.Scanner;
public class task3 {
    //public int x;

    public  void run()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of lesson(from 1 to 10):");
        int x=  in.nextInt();
        check(x);
        x=x*45+(x/2)*5+((x+1)/2-1)*15;
        System.out.println(9+x/60+" "+x%60);
    }
    public void check(int x) {
        try {
            if (x < 1 ||x > 10 )
                throw new Exception();
        } catch (NumberFormatException e) {
            System.out.println("Invalid data type");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Invalid number");
            System.exit(0);
        }
    }
}