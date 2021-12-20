package ua.com.alevel.calendar;

import java.text.DecimalFormat;
import java.util.*;

public class MyDate {

    private static final Map<Integer, String> DATE_FORMAT = new LinkedHashMap<>();
    private long dateInMillis;
    private int year;
    private int month;
    private int dayOfMonth;
    private int hours;
    private int minutes;
    private int seconds;
    private int millis;
    private String inputTemplate;
    private String outputTemplate;

    public MyDate() {
        inputTemplate = "dd-mm-yyyy 00:00";
        outputTemplate = "dd-mm-yyyy 00:00";
        setComponentsOfDateFormat();
    }

    public MyDate(MyDate date) {
        inputTemplate = date.inputTemplate;
        outputTemplate = date.outputTemplate;
    }

    public long getDateInMillis() {
        return dateInMillis;
    }

    public void setDateInMillis(long dateInMillis) {
        this.dateInMillis = dateInMillis;
        fillDateFromMilliseconds();
    }

    public String getInputTemplate() {
        return inputTemplate;
    }

    public void setInputTemplate(String inputTemplate) {
        this.inputTemplate = inputTemplate;
    }

    public String getOutputTemplate() {
        return outputTemplate;
    }

    public void setOutputTemplate(String outputTemplate) {
        this.outputTemplate = outputTemplate;
    }

    public void plusYear(long count) {
        long plusMilliseconds = 0;
        plusMilliseconds = plusMilliseconds + count * 365 * 24 * 60 * 60 * 1000;
        if ((count + year) % 4 == 0) {
            plusMilliseconds = plusMilliseconds + ((count + year) / 4 - year / 4 + 1) * 24 * 60 * 60 * 1000;
        } else {
            plusMilliseconds = plusMilliseconds + ((count + year) / 4 - year / 4) * 24 * 60 * 60 * 1000;
        }
        dateInMillis = dateInMillis + plusMilliseconds;
        fillDateFromMilliseconds();
    }

    public void plusDay(long count) {
        long plusMilliseconds = 0;
        int countOfDays = (int) (dateInMillis / 1000 / 60 / 60 / 24);
        int newYear = getContOfYears(countOfDays + (int) count + 1);
        if (newYear != year) {
            plusMilliseconds = plusMilliseconds + (long) (newYear / 4 - year / 4) * 24 * 60 * 60 * 1000;
        }
        plusMilliseconds = plusMilliseconds + count * 24 * 60 * 60 * 1000;
        dateInMillis = dateInMillis + plusMilliseconds;
        fillDateFromMilliseconds();
    }

    public void plusHour(long count) {
        long plusMilliseconds = 0;
        int countOfDays = (int) (dateInMillis / 1000 / 60 / 60 / 24);
        int newCountOfDays = (int) ((dateInMillis + count * 60 * 60 * 1000) / 1000 / 60 / 60 / 24);
        if (newCountOfDays != countOfDays) {
            int newYear = getContOfYears(countOfDays + (newCountOfDays - countOfDays) + 1);
            if (newYear != year) {
                plusMilliseconds = plusMilliseconds + (long) (newYear / 4 - year / 4) * 24 * 60 * 60 * 1000;
            }
        }
        plusMilliseconds = plusMilliseconds + count * 60 * 60 * 1000;
        dateInMillis = dateInMillis + plusMilliseconds;
        fillDateFromMilliseconds();
    }

    public void plusMinute(long count) {
        long plusMilliseconds = 0;
        int countOfDays = (int) (dateInMillis / 1000 / 60 / 60 / 24);
        int newCountOfDays = (int) ((dateInMillis + count * 60 * 1000) / 1000 / 60 / 60 / 24);
        if (newCountOfDays != countOfDays) {
            int newYear = getContOfYears(countOfDays + (newCountOfDays - countOfDays) + 1);
            if (newYear != year) {
                plusMilliseconds = plusMilliseconds + (long) (newYear / 4 - year / 4) * 24 * 60 * 60 * 1000;
            }
        }
        plusMilliseconds = plusMilliseconds + count * 60 * 1000;
        dateInMillis = dateInMillis + plusMilliseconds;
        fillDateFromMilliseconds();
    }

    public void plusSecond(long count) {
        long plusMilliseconds = 0;
        int countOfDays = (int) (dateInMillis / 1000 / 60 / 60 / 24);
        int newCountOfDays = (int) ((dateInMillis + count * 1000) / 1000 / 60 / 60 / 24);
        if (newCountOfDays != countOfDays) {
            int newYear = getContOfYears(countOfDays + (newCountOfDays - countOfDays) + 1);
            if (newYear != year) {
                plusMilliseconds = plusMilliseconds + (long) (newYear / 4 - year / 4) * 24 * 60 * 60 * 1000;
            }
        }
        plusMilliseconds = plusMilliseconds + count * 1000;
        dateInMillis = dateInMillis + plusMilliseconds;
        fillDateFromMilliseconds();
    }

    public void plusMillis(long count) {
        long plusMilliseconds = 0;
        int countOfDays = (int) (dateInMillis / 1000 / 60 / 60 / 24);
        int newCountOfDays = (int) ((dateInMillis + count) / 1000 / 60 / 60 / 24);
        if (newCountOfDays != countOfDays) {
            int newYear = getContOfYears(countOfDays + (newCountOfDays - countOfDays) + 1);
            if (newYear != year) {
                plusMilliseconds = plusMilliseconds + (long) (newYear / 4 - year / 4) * 24 * 60 * 60 * 1000;
            }
        }
        plusMilliseconds = plusMilliseconds + count;
        dateInMillis = dateInMillis + plusMilliseconds;
        fillDateFromMilliseconds();
    }

    public Map<Integer, String> getDateFormat() {
        return DATE_FORMAT;
    }

    private void setComponentsOfDateFormat() {
        DATE_FORMAT.put(1, "d/m/yyyy");
        DATE_FORMAT.put(2, "dd/m/yyyy");
        DATE_FORMAT.put(3, "d/mm/yyyy");
        DATE_FORMAT.put(4, "dd/mm/yyyy");
        DATE_FORMAT.put(5, "d/mmm/yyyy");
        DATE_FORMAT.put(6, "dd/mmm/yyyy");
        DATE_FORMAT.put(7, "d/m/yyyy 00:00");
        DATE_FORMAT.put(8, "dd/m/yyyy 00:00");
        DATE_FORMAT.put(9, "d/mm/yyyy 00:00");
        DATE_FORMAT.put(10, "dd/mm/yyyy 00:00");
        DATE_FORMAT.put(11, "d/mmm/yyyy 00:00");
        DATE_FORMAT.put(12, "dd/mmm/yyyy 00:00");
        DATE_FORMAT.put(13, "d/m/yyyy 00:00:00");
        DATE_FORMAT.put(14, "dd/m/yyyy 00:00:00");
        DATE_FORMAT.put(15, "d/mm/yyyy 00:00:00");
        DATE_FORMAT.put(16, "dd/mm/yyyy 00:00:00");
        DATE_FORMAT.put(17, "d/mmm/yyyy 00:00:00");
        DATE_FORMAT.put(18, "dd/mmm/yyyy 00:00:00");
        DATE_FORMAT.put(19, "d/m/yyyy 00:00:00:000");
        DATE_FORMAT.put(20, "dd/m/yyyy 00:00:00:000");
        DATE_FORMAT.put(21, "d/mm/yyyy 00:00:00:000");
        DATE_FORMAT.put(22, "dd/mm/yyyy 00:00:00:000");
        DATE_FORMAT.put(23, "d/mmm/yyyy 00:00:00:000");
        DATE_FORMAT.put(24, "dd/mmm/yyyy 00:00:00:000");
        DATE_FORMAT.put(25, "d-m-yyyy");
        DATE_FORMAT.put(26, "dd-m-yyyy");
        DATE_FORMAT.put(27, "d-mm-yyyy");
        DATE_FORMAT.put(28, "dd-mm-yyyy");
        DATE_FORMAT.put(29, "d-mmm-yyyy");
        DATE_FORMAT.put(30, "dd-mmm-yyyy");
        DATE_FORMAT.put(31, "d-m-yyyy 00:00");
        DATE_FORMAT.put(32, "dd-m-yyyy 00:00");
        DATE_FORMAT.put(33, "d-mm-yyyy 00:00");
        DATE_FORMAT.put(34, "dd-mm-yyyy 00:00");
        DATE_FORMAT.put(35, "d-mmm-yyyy 00:00");
        DATE_FORMAT.put(36, "dd-mmm-yyyy 00:00");
        DATE_FORMAT.put(37, "d-m-yyyy 00:00:00");
        DATE_FORMAT.put(38, "dd-m-yyyy 00:00:00");
        DATE_FORMAT.put(39, "d-mm-yyyy 00:00:00");
        DATE_FORMAT.put(40, "dd-mm-yyyy 00:00:00");
        DATE_FORMAT.put(41, "d-mmm-yyyy 00:00:00");
        DATE_FORMAT.put(42, "dd-mmm-yyyy 00:00:00");
        DATE_FORMAT.put(43, "d-m-yyyy 00:00:00:000");
        DATE_FORMAT.put(44, "dd-m-yyyy 00:00:00:000");
        DATE_FORMAT.put(45, "d-mm-yyyy 00:00:00:000");
        DATE_FORMAT.put(46, "dd-mm-yyyy 00:00:00:000");
        DATE_FORMAT.put(47, "d-mmm-yyyy 00:00:00:000");
        DATE_FORMAT.put(48, "dd-mmm-yyyy 00:00:00:000");
    }

    public String getDateToStringInFormat() {
        String outputDate = "";
        String[] arrayOfPartsDate = outputTemplate.split(" ");
        String separator = "";
        if (arrayOfPartsDate[0].contains("-")) {
            separator = "-";
        }else separator = "/";
        String[] subStringsPartOfDate = arrayOfPartsDate[0].split(separator);
        for (int i = 0; i < 3; i++) {
            switch (subStringsPartOfDate[i]) {
                case "d":
                    if (i == 0) {
                        outputDate = outputDate + dayOfMonth;
                    } else {
                        outputDate = outputDate + separator + dayOfMonth;
                    }
                case "dd":
                    if (i == 0) {
                        outputDate = outputDate + new DecimalFormat("00").format(dayOfMonth);
                    } else {
                        outputDate = outputDate + separator + new DecimalFormat("00").format(dayOfMonth);
                    }
                    break;
                case "m":
                    if (i == 0) {
                        outputDate = outputDate + month;
                    } else {
                        outputDate = outputDate + separator + month;
                    }
                case "mm":
                    if (i == 0) {
                        outputDate = outputDate + new DecimalFormat("00").format(month);
                    } else {
                        outputDate = outputDate + separator + new DecimalFormat("00").format(month);
                    }
                    break;
                case "mmm":
                    if (i == 0) {
                        outputDate = outputDate + getMonthFromNumber(month);
                    } else {
                        outputDate = outputDate + separator + getMonthFromNumber(month);
                    }
                    break;
                case "yyyy":
                    if (i == 0) {
                        outputDate = outputDate + year;
                    } else {
                        outputDate = outputDate + separator + year;
                    }
                    break;
            }
        }
        if (arrayOfPartsDate.length == 2) {
            outputDate = outputDate + " ";
            String[] subStringsPartOfTimeTemplate = arrayOfPartsDate[1].split(":");
            if (subStringsPartOfTimeTemplate.length == 2) {
                outputDate = outputDate + new DecimalFormat("00").format(hours) + ":" + new DecimalFormat("00").format(minutes);
            } else if (subStringsPartOfTimeTemplate.length == 3) {
                outputDate = outputDate + new DecimalFormat("00").format(hours) + ":" + new DecimalFormat("00").format(minutes) + ":" + new DecimalFormat("00").format(seconds);
            } else if (subStringsPartOfTimeTemplate.length == 4) {
                outputDate = outputDate + new DecimalFormat("00").format(hours) + ":" + new DecimalFormat("00").format(minutes) + ":" + new DecimalFormat("00").format(seconds) + ":" + new DecimalFormat("000").format(millis);
            } else {
                throw new RuntimeException("input time is incorrect");
            }
        }
        return outputDate;
    }

    public List<String> getDateTemplatesBySeparator(String separator) {
        List<String> templates = DATE_FORMAT.entrySet().stream()
                .filter(entry -> entry.getValue().contains(separator))
                .map(entry -> entry.getKey() + ": " + entry.getValue()).toList();
        return templates;
    }

    public String getTemplateById(int id) {
        if (DATE_FORMAT.get(id) == null) {
            throw new RuntimeException("Entered id is not found.");
        } else {
            return DATE_FORMAT.get(id);
        }
    }

    public void parseDate(String rowOfDate) {
        String[] arrayOfPartsDate = rowOfDate.split(" ");
        String[] arrayOfPartsTemplate = inputTemplate.split(" ");
        if (arrayOfPartsDate.length != arrayOfPartsTemplate.length) {
            throw new RuntimeException("problem: input data is not compare to template.");
        }
        parsePartDate(arrayOfPartsTemplate[0], arrayOfPartsDate[0]);
        if (arrayOfPartsTemplate.length == 2) {
            parsePartTime(arrayOfPartsTemplate[1], arrayOfPartsDate[1]);
        }
        fillDateFromMilliseconds();
    }

    private void fillDateFromMilliseconds() {
        long passedMilliseconds = 0;
        int countOfDays = (int) (dateInMillis / 1000 / 60 / 60 / 24);
        year = getContOfYears(countOfDays);
        if (year != 0) {
            passedMilliseconds = passedMilliseconds + (long) year * 365 * 24 * 60 * 60 * 1000;
            passedMilliseconds = passedMilliseconds + (long) year / 4 * 24 * 60 * 60 * 1000;
        }
        countOfDays = (int) ((dateInMillis - passedMilliseconds) / 1000 / 60 / 60 / 24);
        month = getContOfMonth(countOfDays);
        for (int i = 1; i <= month; i++) {
            passedMilliseconds = passedMilliseconds + (long) getCountDaysInMonth(i, year) * 24 * 60 * 60 * 1000;
        }
        month = month + 1;
        countOfDays = (int) ((dateInMillis - passedMilliseconds) / 1000 / 60 / 60 / 24);
        passedMilliseconds = passedMilliseconds + (long) countOfDays * 24 * 60 * 60 * 1000;
        dayOfMonth = countOfDays + 1;
        hours = (int) (dateInMillis - passedMilliseconds) / 1000 / 60 / 60;
        passedMilliseconds = passedMilliseconds + hours * 60 * 60 * 1000;
        minutes = (int) (dateInMillis - passedMilliseconds) / 1000 / 60;
        passedMilliseconds = passedMilliseconds + minutes * 60 * 1000;
        seconds = (int) (dateInMillis - passedMilliseconds) / 1000;
        passedMilliseconds = passedMilliseconds + seconds * 1000;
        millis = (int) (dateInMillis - passedMilliseconds);
    }

    private int getContOfYears(int countOfDays) {
        int yearOfDays = 0;
        while (countOfDays > 0) {
            if (yearOfDays % 4 == 0) {
                if (countOfDays >= 366) {
                    countOfDays = countOfDays - 366;
                    yearOfDays++;
                } else {
                    return yearOfDays;
                }
            } else if (countOfDays >= 365) {
                countOfDays = countOfDays - 365;
                yearOfDays++;
            } else {
                return yearOfDays;
            }
        }
        return yearOfDays;
    }

    public Map<String, Long> findDifferencesDate(long countOfMillis) {
        Map<String, Long> difference = new LinkedHashMap<>();
        difference.put("years", (long) getContOfYears((int) (countOfMillis / 1000 / 60 / 60 / 24)));
        difference.put("days", countOfMillis / 1000 / 60 / 60 / 24);
        difference.put("hours", countOfMillis / 1000 / 60 / 60);
        difference.put("minutes", countOfMillis / 1000 / 60);
        difference.put("seconds", countOfMillis / 1000);
        difference.put("millis", countOfMillis);
        return difference;
    }

    private int getContOfMonth(int countOfDays) {
        int monthOfDays = 0;
        int countDaysInMonth;
        for (int i = 1; i <= 12; i++) {
            countDaysInMonth = getCountDaysInMonth(i, year);
            if (countOfDays >= countDaysInMonth) {
                countOfDays = countOfDays - countDaysInMonth;
                monthOfDays++;
            } else {
                return monthOfDays;
            }
        }
        return monthOfDays;
    }

    private void parsePartTime(String partOfTimeTemplate, String partOfTime) {
        String separator = ":";
        String[] subStringsPartOfTime = partOfTime.split(separator);
        String[] subStringsPartOfTimeTemplate = partOfTimeTemplate.split(separator);
        if (subStringsPartOfTime.length != subStringsPartOfTimeTemplate.length) {
            throw new RuntimeException("problem: no temletes found");
        }
        if (subStringsPartOfTime.length == 2) {
            if (Long.parseLong(subStringsPartOfTime[0]) > 23 || Long.parseLong(subStringsPartOfTime[0]) < 0) {
                throw new RuntimeException("input time is incorrect");
            }
            if (Long.parseLong(subStringsPartOfTime[1]) > 59 || Long.parseLong(subStringsPartOfTime[1]) < 0) {
                throw new RuntimeException("input time is incorrect");
            }
            dateInMillis = dateInMillis + Long.parseLong(subStringsPartOfTime[0]) * 60 * 60 * 1000;
            dateInMillis = dateInMillis + Long.parseLong(subStringsPartOfTime[1]) * 60 * 1000;
        } else if (subStringsPartOfTime.length == 3) {
            if (Long.parseLong(subStringsPartOfTime[0]) > 23 || Long.parseLong(subStringsPartOfTime[0]) < 0) {
                throw new RuntimeException("input time is incorrect");
            }
            if (Long.parseLong(subStringsPartOfTime[1]) > 59 || Long.parseLong(subStringsPartOfTime[1]) < 0) {
                throw new RuntimeException("input time is incorrect");
            }
            if (Long.parseLong(subStringsPartOfTime[2]) > 59 || Long.parseLong(subStringsPartOfTime[2]) < 0) {
                throw new RuntimeException("input time is incorrect");
            }
            dateInMillis = dateInMillis + Long.parseLong(subStringsPartOfTime[0]) * 60 * 60 * 1000;
            dateInMillis = dateInMillis + Long.parseLong(subStringsPartOfTime[1]) * 60 * 1000;
            dateInMillis = dateInMillis + Long.parseLong(subStringsPartOfTime[2]) * 1000;
        } else if (subStringsPartOfTime.length == 4) {
            if (Long.parseLong(subStringsPartOfTime[0]) > 23 || Long.parseLong(subStringsPartOfTime[0]) < 0) {
                throw new RuntimeException("input time is incorrect");
            }
            if (Long.parseLong(subStringsPartOfTime[1]) > 59 || Long.parseLong(subStringsPartOfTime[1]) < 0) {
                throw new RuntimeException("input time is incorrect");
            }
            if (Long.parseLong(subStringsPartOfTime[2]) > 59 || Long.parseLong(subStringsPartOfTime[2]) < 0) {
                throw new RuntimeException("input time is incorrect");
            }
            if (Long.parseLong(subStringsPartOfTime[3]) > 999 || Long.parseLong(subStringsPartOfTime[3]) < 0) {
                throw new RuntimeException("input time is incorrect");
            }
            dateInMillis = dateInMillis + Long.parseLong(subStringsPartOfTime[0]) * 60 * 60 * 1000;
            dateInMillis = dateInMillis + Long.parseLong(subStringsPartOfTime[1]) * 60 * 1000;
            dateInMillis = dateInMillis + Long.parseLong(subStringsPartOfTime[2]) * 1000;
            dateInMillis = dateInMillis + Long.parseLong(subStringsPartOfTime[3]);
        } else {
            throw new RuntimeException("input time is incorrect");
        }
    }

    private void parsePartDate(String partOfDateTemplate, String partOfDate) {
        String separator = "/";
        if (partOfDateTemplate.contains("-")) {
            separator = "-";
        }
        String[] subStringsPartOfDate = partOfDate.split(separator);
        String[] subStringsPartOfDateTemplate = partOfDateTemplate.split(separator);
        if (subStringsPartOfDate.length != subStringsPartOfDateTemplate.length) {
            throw new RuntimeException("problem: input data is not compare to template.");
        }
        Map<String, Integer> valueOfPartDate = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            switch (subStringsPartOfDateTemplate[i]) {
                case "d":
                case "dd":
                    if (Objects.equals(subStringsPartOfDate[i], "")) {
                        valueOfPartDate.put("day", 1);
                    } else {
                        valueOfPartDate.put("day", Integer.parseInt(subStringsPartOfDate[i]));
                    }
                    break;
                case "m":
                case "mm":
                    if (Objects.equals(subStringsPartOfDate[i], "")) {
                        valueOfPartDate.put("month", 1);
                    } else {
                        valueOfPartDate.put("month", Integer.parseInt(subStringsPartOfDate[i]));
                    }
                    break;
                case "mmm":
                    if (Objects.equals(subStringsPartOfDate[i], "")) {
                        valueOfPartDate.put("month", 1);
                    } else {
                        valueOfPartDate.put("month", getMonthByName(subStringsPartOfDate[i]));
                    }
                    break;
                case "yyyy":
                    if (Objects.equals(subStringsPartOfDate[i], "")) {
                        valueOfPartDate.put("year", 1);
                    } else {
                        valueOfPartDate.put("year", Integer.parseInt(subStringsPartOfDate[i]));
                    }
                    break;
            }
        }
        if (valueOfPartDate.get("year") < 0) {
            throw new RuntimeException("entered year is incorrect");
        }
        if (valueOfPartDate.get("month") > 12 || valueOfPartDate.get("month") <= 0) {
            throw new RuntimeException("entered month is incorrect");
        }
        if (valueOfPartDate.get("day") < 0 || valueOfPartDate.get("day") > getCountDaysInMonth(valueOfPartDate.get("month"), valueOfPartDate.get("year"))) {
            throw new RuntimeException("entered day is incorrect");
        }
        if (valueOfPartDate.get("year") != 0) {
            dateInMillis = dateInMillis + (long) valueOfPartDate.get("year") * 365 * 24 * 60 * 60 * 1000;
            dateInMillis = dateInMillis + (long) valueOfPartDate.get("year") / 4 * 24 * 60 * 60 * 1000;
        }
        for (int i = 1; i < valueOfPartDate.get("month"); i++) {
            dateInMillis = dateInMillis + (long) getCountDaysInMonth(i, valueOfPartDate.get("year")) * 24 * 60 * 60 * 1000;
        }
        dateInMillis = dateInMillis + (long) (valueOfPartDate.get("day") - 1) * 24 * 60 * 60 * 1000;
    }

    private int getCountDaysInMonth(int numberOfMonth, int year) {
        switch (numberOfMonth) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (year % 4 == 0) {
                    return 29;
                } else {
                    return 28;
                }
        }
        throw new RuntimeException("incorrect number of month");
    }

    private int getMonthByName(String nameOfMonth) {
        switch (nameOfMonth) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
        }
        throw new RuntimeException("Name of month is not valid.");
    }

    private String getMonthFromNumber(int numberOfMonth) {
        switch (numberOfMonth) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }
        throw new RuntimeException("N=number of month is not valid.");
    }
}