package ua.com.alevel.controller;

import ua.com.alevel.mathset.MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    MathSet mathSet = new MathSet();
    MathSet ms;
    String[] stringArray;
    Number[] numberArray;
    String input;

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null && !position.equals("0")) {
                collections(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println("""
                
                1 - add 1 number
                2 - add several numbers
                3 - join(MathSet ms)
                4 - join(MathSet ... ms)
                5 - intersection(MathSet ms)
                6 - intersection (MathSet ... ms)
                7 - sortDesc()
                8 - sortDesc(int firstIndex, int lastIndex)
                9 - sortDesc(Number value)
                10 -sortAsc()
                11 -sortAsc(int firstIndex, int lastIndex)
                12 -sortAsc(Number value)
                13 -get(int index)
                14 -getMax()
                15 -getMin()
                16 -getAverage()
                17 -getMedian()
                18 -cut(int firstIndex, int lastIndex)
                19 -void clear()
                20 -clear(Number[] numbers)
                """);
    }

    private void collections(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> add(reader);
            case "2" -> addSeveral(reader);
            case "3" -> join(reader);
            case "4" -> joinSeveral(reader);
            case "5" -> intersection(reader);
            case "6" -> intersectionSeveral(reader);
            case "7" -> sortDesc(reader);
            case "8" -> sortDescFromTo(reader);
            case "9" -> sortDescFrom(reader);
            case "10" -> sortAsc(reader);
            case "11" -> sortAscFromTo(reader);
            case "12" -> sortAscFrom(reader);
            case "13" -> get(reader);
            case "14" -> getMax(reader);
            case "15" -> getMin(reader);
            case "16" -> getAverage(reader);
            case "17" -> getMedian();
            case "18" -> cut(reader);
            case "19" -> clearAll(reader);
            case "20" -> clear(reader);
        }
        runNavigation();
    }

    private void clear(BufferedReader reader) throws IOException {
        System.out.println("enter elements that should be cleared");
        input = reader.readLine();
        String[] subArray = input.split(" ");
        numberArray = new Number[subArray.length];
        try {
            for (int i = 0; i < numberArray.length; i++) {
                if (subArray[i] != null) {
                    numberArray[i] = parseNumber(subArray[i]);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("invalid input");
        }
        mathSet.clear(numberArray);
        System.out.println("Result of clear:" + mathSet);
    }

    private void clearAll(BufferedReader reader) {
        mathSet.clear();
    }

    private void cut(BufferedReader reader) {
        int ind1 = 0, ind2 = 0;
        try {
            System.out.println("enter the start index");
            ind1 = Integer.parseInt(reader.readLine());
            System.out.println("enter the last index");
            ind2 = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("invalid input");
        }
        mathSet.cut(ind1, ind2);
        System.out.println("Result of cut:" + mathSet);
    }

    private void toArrayFromTo(BufferedReader reader) {
    }

    private void toArray(BufferedReader reader) {
    }

    private void getMedian() {
        System.out.println("Current mathset:" + mathSet);
        System.out.println("Median:" + mathSet.getMedian());
    }

    private void getAverage(BufferedReader reader) {
        System.out.println("Current mathset:" + mathSet);
        System.out.println("Average:" + mathSet.getAverage());
    }

    private void getMin(BufferedReader reader) {
        System.out.println("Current mathset:" + mathSet);
        System.out.println("Min value:" + mathSet.getMin());
    }

    private void getMax(BufferedReader reader) {
        System.out.println("Current mathset:" + mathSet);
        System.out.println("Max value:" + mathSet.getMax());
    }

    private void get(BufferedReader reader) {
        int ind = 0;
        try {
            System.out.println("enter the index");
            ind = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("invalid input");
        }
        System.out.println("Value by index:" + mathSet.get(ind));
    }

    private void sortAscFrom(BufferedReader reader) {
        int v = 0;
        try {
            System.out.println("enter the start value");
            v = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("invalid input");
        }
        mathSet.sortAsc(v);
        System.out.println("Result of desc sorting:" + mathSet);
    }

    private void sortAscFromTo(BufferedReader reader) {
        int ind1 = 0, ind2 = 0;
        try {
            System.out.println("enter the start index");
            ind1 = Integer.parseInt(reader.readLine());
            System.out.println("enter the last index");
            ind2 = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("invalid input");
        }
        mathSet.sortAsc(ind1, ind2);
        System.out.println("Result of asc sorting:" + mathSet);
    }

    private void sortAsc(BufferedReader reader) {
        mathSet.sortAsc();
        System.out.println("Result of asc sorting:" + mathSet);
    }

    private void sortDescFrom(BufferedReader reader) {
        int v = 0;
        try {
            System.out.println("enter the start value");
            v = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("invalid input");
        }
        mathSet.sortDesc(v);
        System.out.println("Result of desc sorting:" + mathSet);
    }

    private void sortDescFromTo(BufferedReader reader) {
        mathSet.sortDesc();
        int ind1 = 0, ind2 = 0;
        try {
            System.out.println("enter the start index");
            ind1 = Integer.parseInt(reader.readLine());
            System.out.println("enter the last index");
            ind2 = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("invalid input");
        }
        mathSet.sortDesc(ind1, ind2);
        System.out.println("Result of desc sorting:" + mathSet);
    }

    private void sortDesc(BufferedReader reader) {
        mathSet.sortDesc();
        System.out.println("Result of desc sorting:" + mathSet);
    }

    private void intersectionSeveral(BufferedReader reader) throws IOException {
        System.out.println("enter several mathsets, put ; between them");
        input = reader.readLine();
        mathSet.intersection(fromInputToVarargsMathsets(input));
        System.out.println("Result of intersection:" + mathSet);
    }

    private void intersection(BufferedReader reader) throws IOException {
        System.out.println("enter elements of new mathset withspaces");
        input = reader.readLine();
        stringArray = input.split(" ");
        numberArray = new Number[stringArray.length];
        try {
            for (int i = 0; i < numberArray.length; i++) {
                numberArray[i] = parseNumber(stringArray[i]);
            }

        } catch (NumberFormatException e) {
            System.out.println("invalid data");
        }
        ms = new MathSet(numberArray);
        mathSet.intersection(ms);
        System.out.println("Result of intersection:" + mathSet);
    }

    private void joinSeveral(BufferedReader reader) throws IOException {
        System.out.println("enter several mathsets, put ; between them");
        input = reader.readLine();
        mathSet.join(fromInputToVarargsMathsets(input));
        System.out.println("Result of join:" + mathSet);
    }

    private void join(BufferedReader reader) throws IOException {
        System.out.println("enter elements of new mathset withspaces");
        input = reader.readLine();
        stringArray = input.split(" ");
        numberArray = new Number[stringArray.length];
        try {
            for (int i = 0; i < numberArray.length; i++) {
                numberArray[i] = parseNumber(stringArray[i]);
            }

        } catch (NumberFormatException e) {
            System.out.println("invalid data");
        }
        ms = new MathSet(numberArray);
        mathSet.join(ms);
        System.out.println("Result of join:" + mathSet);
    }

    private void addSeveral(BufferedReader reader) throws IOException {
        System.out.println("enter several numbers with spaces");
        input = reader.readLine();
        stringArray = input.split(" ");
        numberArray = new Number[stringArray.length];
        try {
            for (int i = 0; i < numberArray.length; i++) {
                numberArray[i] = parseNumber(stringArray[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("invalid data");
        }
        mathSet.add(numberArray);
    }

    private void add(BufferedReader reader) {
        System.out.println("enter the number");
        try {
            mathSet.add(parseNumber(reader.readLine()));
        } catch (NumberFormatException | IOException e) {
            System.out.println("invalid input");
        }
    }

    private Number parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                throw exception;
            }
        }
    }

    private MathSet[] fromInputToVarargsMathsets(String input) {
        stringArray = input.split(";");
        MathSet[] mathSets = new MathSet[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            String[] subArray = stringArray[i].split(" ");
            numberArray = new Number[subArray.length];
            for (int j = 0; j < numberArray.length; j++) {
                numberArray[j] = parseNumber(subArray[j]);
            }
            mathSets[i] = new MathSet(numberArray);
        }
        return mathSets;
    }
}