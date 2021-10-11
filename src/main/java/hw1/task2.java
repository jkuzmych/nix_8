package hw1;

import java.util.Scanner;
import java.util.regex.*;
import java.util.Arrays;

public class task2 {
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input (alphanumerical string): ");
        String st= in.nextLine();

        st=st.replaceAll("[^a-zA-Za-яА-ЯёЁ]+", "");
        char[] arr = st.toCharArray();
        Arrays.sort(arr);
        st = String.valueOf(arr);

        int n=1;
        for (int i = 0; i < arr.length;) {
            System.out.println(n+". "+arr[i]+"-"+(1+st.lastIndexOf(arr[i])-st.indexOf(arr[i])));
            i+= (1+st.lastIndexOf(arr[i]) - st.indexOf(arr[i]));
            n++;
        }
        if(n==1)
            System.out.println("No latin/cyrillic symbols found");
    }
}