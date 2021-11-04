package ua.com.alevel.firstlevel;

import java.io.BufferedReader;
import java.io.IOException;

public class TriangleArea {
    public static void run(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println();
            System.out.println("Enter x of point A");
            double xA = Double.parseDouble(reader.readLine());
            System.out.println("Enter y of point A");
            double yA = Double.parseDouble(reader.readLine());
            System.out.println("Enter x of point B");
            double xB = Double.parseDouble(reader.readLine());
            System.out.println("Enter y of point B");
            double yB = Double.parseDouble(reader.readLine());
            System.out.println("Enter x of point C");
            double xC = Double.parseDouble(reader.readLine());
            System.out.println("Enter y of point C");
            double yC = Double.parseDouble(reader.readLine());
            double a = sideLengthCount(xB, yB, xC, yC);
            double b = sideLengthCount(xA, yA, xC, yC);
            double c = sideLengthCount(xA, yA, xB, yB);
            System.out.println("The area of triangle is " + String.format("%.2f", areaCount(a, b, c)));
            System.out.println("Select 0 to break");
            String src = reader.readLine();
            if (src.compareTo("0") == 0 || src.compareTo("q") == 0) break;
        }
    }

    public static double sideLengthCount(double x1, double y1, double x2, double y2) {
        return (Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
    }

    public static double areaCount(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return (Math.sqrt(p * (p - a) * (p - b) * (p - c)));
    }
}