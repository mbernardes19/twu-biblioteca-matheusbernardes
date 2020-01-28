package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!\n";
    private static final String TABLE_FORMAT = "%-30.30s %-30.30s %-30.30s%n";
    private static List<Book> availableBooks = new ArrayList<>();
    private static MainMenu mainMenu = new MainMenu();
    private static MainMenuOption selectedOption;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        BibliotecaApp app = new BibliotecaApp();
        showMessage(welcomeMessage());
        showMessage(mainMenuOptions());
        app.selectOption(input.nextInt());
    }

    public BibliotecaApp() {
        populateAvailableBooks();
    }

    private static void showMessage(String message) {
        System.out.println(message);
    }

    private void populateAvailableBooks() {
        if (availableBooks.size() == 3) {
            return;
        }
        availableBooks.add(new Book("Orope: The White Snake","Guenevere Lee", "2018"));
        availableBooks.add(new Book("The Great Gatsby","F. Scott Fitzgerald", "1925"));
        availableBooks.add(new Book("Boulevard Dreams","E. Ryan Janz", "2018"));
    }

    public static String welcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public String allBooks() {
        String message = showAsTable("TITLE","AUTHOR", "PUBLICATION YEAR");
        for (Book availableBook : availableBooks) {
            message += showAsTable(availableBook.getTitle(), availableBook.getAuthor(), availableBook.getPublicationYear());
        }
        return message;
    }

    private static String showAsTable(String... cols) {
        return String.format(TABLE_FORMAT, cols);
    }

    public static String mainMenuOptions() {
        return mainMenu.showOptions();
    }

    public static List<Book> getAvailableBooks() {
        return availableBooks;
    }

    public void selectOption(int optionNumber) {
        switch(optionNumber) {
            case 1:
                selectedOption = MainMenuOption.OPTION_1;
                showMessage(allBooks());
        }
    }

    public static MainMenuOption getSelectedOption() {
        return selectedOption;
    }
}
