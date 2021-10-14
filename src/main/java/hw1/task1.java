package hw1;

import java.util.Scanner;
public class task1 {
    public void run()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Input (alphanumerical string): ");
        String st= in.nextLine();
//ggggggg
        //566656
        String buff="0";
        int sum = 0;
        for (int i = 0; i < st.length(); i++) {
            char ch = st.charAt(i);
            if (Character.isDigit(ch))
                buff += ch;
            else {
                sum += Integer.parseInt(buff);
                buff = "0";
            }
        }
        sum += Integer.parseInt(buff);
        System.out.println("sum of numbers: "+sum);
    }
}