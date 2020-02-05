package com.twu.biblioteca;

public class Printer {
    private static final String TABLE_FORMAT = "%-30.30s %-30.30s %-30.30s%n";

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printError(String errorMessage) {
        System.err.println(errorMessage);
    }

    public static void printTable(String... columns) {
        String tableFormat = "";
        for (String column : columns) {
            tableFormat += "%-30.30s";
        }
        System.out.printf(tableFormat+"%n", columns);
    }

}
