package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!\n";
    private static final String TABLE_FORMAT = "%-30.30s %-30.30s %-30.30s%n";
    private static List<Book> availableBooks = new ArrayList<>();

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        System.out.println(BibliotecaApp.showWelcomeMessage());
        System.out.println(BibliotecaApp.displayAllBooks());
    }

    public BibliotecaApp() {
        populateAvailableBooks();
    }

    private static void populateAvailableBooks() {
        availableBooks.add(new Book("Orope: The White Snake","Guenevere Lee", "March 6th 2018"));
        availableBooks.add(new Book("The Great Gatsby","F. Scott Fitzgerald", "1925"));
        availableBooks.add(new Book("Boulevard Dreams","E. Ryan Janz", "August 23rd 2018"));
    }

    public static String showWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public static String displayAllBooks() {
        String message = showAsTable("TITLE","AUTHOR", "PUBLISH DATE");
        for (Book availableBook : availableBooks) {
            message += showAsTable(availableBook.getTitle(), availableBook.getAuthor(), availableBook.getPublishDate());
        }
        return message;
    }

    private static String showAsTable(String... cols) {
        return String.format(TABLE_FORMAT, cols);
    }

    public static List<Book> getAvailableBooks() {
        return availableBooks;
    }
}
