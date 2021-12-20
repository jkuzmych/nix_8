package ua.com.alevel.calendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    private final MyDate date = new MyDate();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("select an option:");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                mainOperations(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println("to show allowed  templates, enter 1");
        System.out.println("to set input template, enter 2");
        System.out.println("to set output template, enter 3");
        System.out.println("to find difference between dates, enter 4");
        System.out.println("to increase date, enter 5");
        System.out.println("to reduce date, enter 6");
        System.out.println("to sort dates by desc, enter 7");
        System.out.println("to sort dates by asc, enter 8");
        System.out.println("to exit, enter 0");
        System.out.println();
    }

    private void mainOperations(String position, BufferedReader reader) {
        System.out.println("current input template:"+date.getInputTemplate());
        System.out.println("current input template:"+date.getOutputTemplate());
        switch (position) {
            case "1" -> showInputOutputTemplates();
            case "2" -> setInputTemplate(reader);
            case "3" -> setOutputTemplate(reader);
            case "4" -> differenceBetweenDates(reader);
            case "5" -> increaseDate(reader);
            case "6" -> reduceDate(reader);
            case "7" -> sortDatesByDesc(reader);
            case "8" -> sortDatesByAsc(reader);
        }
        runNavigation();
    }

    private void showInputOutputTemplates() {
        date.getDateFormat().forEach((key, value) -> System.out.println(key + ": " + value));
    }

    private void sortDatesByAsc(BufferedReader reader) {
        HashMap<MyDate, Long> calendars = new HashMap<>();
        System.out.println("to sort->1:");
        System.out.println("enter date:");
        String position;
        try {
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                if (position.equals("1")) {
                    calendars.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> System.out.println(entry.getKey().getDateToStringInFormat()));
                    break;
                }
                MyDate inputDate = new MyDate(date);
                try {
                    inputDate.parseDate(position);
                    calendars.put(inputDate, inputDate.getDateInMillis());
                    System.out.println("to sort->1");
                    System.out.println("or enter the next date");
                } catch (RuntimeException e) {
                    System.out.println("input data is incorrect try again");
                }
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void sortDatesByDesc(BufferedReader reader) {
        HashMap<MyDate, Long> calendars = new HashMap<>();
        System.out.println("to sort->1");
        System.out.println("enter date:");
        String position;
        try {
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                if (position.equals("1")) {
                    calendars.entrySet().stream().sorted(Map.Entry.<MyDate, Long>comparingByValue().reversed()).forEach(entry -> System.out.println(entry.getKey().getDateToStringInFormat()));
                    break;
                }
                MyDate inputDate = new MyDate(date);
                try {
                    inputDate.parseDate(position);
                    calendars.put(inputDate, inputDate.getDateInMillis());
                    System.out.println("to sort->1");
                    System.out.println("or enter the next date");
                } catch (RuntimeException e) {
                    System.out.println("input data is incorrect try again");
                }
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void increaseDate(BufferedReader reader) {
        MyDate inputDate = new MyDate(date);
        try {
            inputDate(inputDate, reader);
        } catch (MyException e) {
            return;
        }
        changeDateByPart(reader, true, inputDate);
        String outputDate = inputDate.getDateToStringInFormat();
        System.out.println("new date: " + outputDate);
    }

    private void reduceDate(BufferedReader reader) {
        MyDate inputDate = new MyDate(date);
        try {
            inputDate(inputDate, reader);
        } catch (MyException e) {
            return;
        }
        changeDateByPart(reader, false, inputDate);
        String outputDate = inputDate.getDateToStringInFormat();
        System.out.println("new date: " + outputDate);
    }

    public void changeDateByPart(BufferedReader reader, boolean increase, MyDate inputDate) {
        System.out.println("Select type of operation:");
        String position;
        try {
            partOfDateNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                changeDateOperations(position, reader, increase, inputDate);
                break;
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void changeDateOperations(String position, BufferedReader reader, boolean increase, MyDate inputDate) {
        switch (position) {
            case "1":
                if (increase) {
                    inputDate.plusYear(inputCountForChangeDate(reader));
                } else {
                    inputDate.plusYear(-inputCountForChangeDate(reader));
                }
                break;
            case "2":
                if (increase) {
                    inputDate.plusDay(inputCountForChangeDate(reader));
                } else {
                    inputDate.plusDay(-inputCountForChangeDate(reader));
                }
                break;
            case "3":
                if (increase) {
                    inputDate.plusHour(inputCountForChangeDate(reader));
                } else {
                    inputDate.plusHour(-inputCountForChangeDate(reader));
                }
                break;
            case "4":
                if (increase) {
                    inputDate.plusMinute(inputCountForChangeDate(reader));
                } else {
                    inputDate.plusMinute(-inputCountForChangeDate(reader));
                }
                break;
            case "5":
                if (increase) {
                    inputDate.plusSecond(inputCountForChangeDate(reader));
                } else {
                    inputDate.plusSecond(-inputCountForChangeDate(reader));
                }
                break;
            case "6":
                if (increase) {
                    inputDate.plusMillis(inputCountForChangeDate(reader));
                } else {
                    inputDate.plusMillis(-inputCountForChangeDate(reader));
                }
                break;
        }
    }

    private long inputCountForChangeDate(BufferedReader reader) {
        System.out.println("if you want exit, please enter 0");
        System.out.println("enter count:");
        long count = 0;
        String inputRow = "";
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("0")) {
                    throw new MyException("exit");
                }
                try {
                    count = Long.parseLong(inputRow);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("input count is incorrect try again.");
                }
            }
        } catch (IOException e) {
            System.out.println("problem: " + e.getMessage());
        }
        return count;
    }

    private void partOfDateNavigation() {
        System.out.println("to enter years->1");
        System.out.println("to enter days->2");
        System.out.println("to enter hours->3");
        System.out.println("to enter minutes, please enter 4");
        System.out.println("to enter seconds, please enter 5");
        System.out.println("to enter millis, please enter 6");
        System.out.println("0 to exit");
        System.out.println();
    }

    private void differenceBetweenDates(BufferedReader reader) {
        try {
            MyDate firstDate = new MyDate(date);
            System.out.println("first date:");
            inputDate(firstDate, reader);
            MyDate secondDate = new MyDate(date);
            System.out.println("second date:");
            inputDate(secondDate, reader);
            System.out.println("difference in whole parts of a date:");
            Map<String, Long> difference = date.findDifferencesDate(firstDate.getDateInMillis() - secondDate.getDateInMillis());
            difference.forEach((key, value) -> System.out.println(key + ": " + value));
        } catch (MyException ignored) {
        } catch (NumberFormatException e) {
            System.out.println("problem with parse date: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("problem: " + e.getMessage());
        }
    }

    private void setInputTemplate(BufferedReader reader) {
        try {
            String newTemplate = ioTemplate(reader);
            date.setInputTemplate(newTemplate);
            System.out.println("new input template: " + newTemplate);
        } catch (MyException ignored) {
        } catch (RuntimeException e) {
            System.out.println("invalid template");
            System.out.println("problem: " + e.getMessage());
        }
    }

    private void setOutputTemplate(BufferedReader reader) {
        try {
            String newTemplate = ioTemplate(reader);
            date.setOutputTemplate(newTemplate);
            System.out.println("new output template is: " + newTemplate);
        } catch (MyException ignored) {
        } catch (RuntimeException e) {
            System.out.println("invalid template");
            System.out.println("problem: " + e.getMessage());
        }
    }

    private String ioTemplate(BufferedReader reader) {
        System.out.println("0 to exit");
        System.out.println("enter number of template");
        String inputRow = "";
        int numberOfTemplate = 0;
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("0")) {
                    throw new MyException("exit");
                } else {
                    try {
                        numberOfTemplate = Integer.parseInt(inputRow);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("invalid data");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("problem: " + e.getMessage());
        }
        return date.getTemplateById(numberOfTemplate);
    }

    private void inputDate(MyDate calendarDate, BufferedReader reader) {
        System.out.println("if you want exit, enter 0");
        System.out.println("enter date:");
        String inputRow = "";
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("0")) {
                    throw new MyException("exit");
                }
                try {
                    calendarDate.parseDate(inputRow);
                    break;
                } catch (RuntimeException e) {
                    System.out.println("invalid data");
                }
            }
        } catch (IOException e) {
            System.out.println("problem: " + e.getMessage());
        }
    }
}