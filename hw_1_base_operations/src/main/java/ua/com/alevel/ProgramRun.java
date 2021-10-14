package ua.com.alevel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ua.com.alevel.sumofnumbers.SumOfNumbers;
import ua.com.alevel.amountofeachletter.AmountOfEachLetter;
import ua.com.alevel.endoflessons.EndOfLessons;

public class ProgramRun {
    public static void run() {
        preview();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String event;
        try {
            while (true) {
                System.out.println("So, your choice is:");
                if((event = reader.readLine()) == null)break;
                switch (event) {
                    case "1" : {
                        new SumOfNumbers().run(reader);
                    } break;
                    case "2" : {
                        new AmountOfEachLetter().run(reader);
                    } break;
                    case "3" : {
                        new EndOfLessons().run(reader);
                    } break;
                    case "0" : {
                        System.exit(0);
                    } break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void preview() {
        System.out.println("To calculate the sum of numbers in the row(task 1) print 1");
        System.out.println("To find amount of each cyrillic/latin symbol(task 2) print 2");
        System.out.println("To count the time of the end of the lesson(task 3) print 3");
        System.out.println("To exit print 0");
        System.out.println();
    }
}
