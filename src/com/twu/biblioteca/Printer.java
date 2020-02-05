package com.twu.biblioteca;

public class Printer {
    private static final String COLUMN_FORMAT = "%-30.30s ";

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printError(String errorMessage) {
        System.err.println(errorMessage);
    }

    public static void printTable(String... columns) {
        String tableFormat = "";
        for (String column : columns) {
            tableFormat += COLUMN_FORMAT;
        }
        String format = tableFormat.substring(0, tableFormat.length()-1);
        System.out.printf(format+"%n", columns);
    }

}
