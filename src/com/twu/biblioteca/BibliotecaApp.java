package com.twu.biblioteca;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!";
    private static final String ERROR_MESSAGE = "Please select a valid option!";
    private static final String TABLE_FORMAT = "%-30.30s %-30.30s %-30.30s%n";
    private static List<Book> availableBooks = new ArrayList<>();
    private static MainMenu mainMenu = new MainMenu();
    private static MainMenuOption selectedOption;

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        showMessage(getWelcomeMessage());
        Scanner input = startInput();

        // Show main menu and check selected option
        while(!isSelectedOptionValid()) {
            try {
                showMessage(mainMenuOptions());
                app.selectOption(input.nextInt());
            } catch(InvalidParameterException e){
                showMessage(ERROR_MESSAGE);
            }
        }

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

    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public String getAllBooks() {
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

    public void selectOption(int optionNumber) throws InvalidParameterException {
        switch(optionNumber) {
            case 0:
                finishApplication();
            case 1:
                selectedOption = MainMenuOption.OPTION_1;
                showMessage(getAllBooks());
                break;
            default:
                throw new InvalidParameterException("Please select a valid option!");
        }
    }

    private static boolean isSelectedOptionValid() {
        List<MainMenuOption> mainMenuOptions = Arrays.asList(mainMenu.getMainMenuOptions());
        if(mainMenuOptions.contains(selectedOption)) {
            return true;
        } else {
            return false;
        }
    }

    public static MainMenuOption getSelectedOption() {
        return selectedOption;
    }

    private void finishApplication() {
        System.exit(0);
    }

    private static Scanner startInput() {
        Scanner input = new Scanner(System.in);
        return input;
    }
}
