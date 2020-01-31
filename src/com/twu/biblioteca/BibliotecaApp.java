package com.twu.biblioteca;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!";
    private static final String ERROR_MESSAGE = "Please select a valid option!";
    public static final String SUCCESS_MESSAGE = "Thank you! Enjoy the book\n";
    private static final String TABLE_FORMAT = "%-30.30s %-30.30s %-30.30s%n";
    private static BookRepository bookRepository = new BookRepository();
    private static List<Book> availableBooks = bookRepository.getAvailableBooks();
    private static MainMenu mainMenu = new MainMenu();
    private static MainMenuOption selectedOption;
    private Book selectedBook;

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        showMessage(getWelcomeMessage());
        Scanner input = startInput();

        // Show main menu and check selected option
        while(!isSelectedOptionValid() || selectedOption != MainMenuOption.OPTION_0) {
            try {
                showMessage(mainMenuOptions());
                app.selectOption(input.nextInt());
            } catch(InvalidParameterException e){
                showMessage(ERROR_MESSAGE);
            }
        }

    }

    private static void showMessage(String message) {
        System.out.println(message);
    }

    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public String getAllBooks() {
        String message = showAsTable("TITLE","AUTHOR", "PUBLICATION YEAR");
        for (Book availableBook : getAvailableBooks()) {
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

    public List<Book> getAvailableBooks() {
        return bookRepository.getAvailableBooks();
    }

    public void setAvailableBooks(List<Book> bookList) {
        bookRepository.setAvailableBooks(bookList);
    }

    public void selectOption(int optionNumber) throws InvalidParameterException {
        switch(optionNumber) {
            case 0:
                finishApplication();
            case 1:
                selectedOption = MainMenuOption.OPTION_1;
                showMessage(getAllBooks());
                break;
            case 2:
                selectedOption = MainMenuOption.OPTION_2;
                showMessage("Select a book to checkout: ");
                showMessage(getCheckoutableBooks());
                checkoutBook(getBookIdInput());
                showMessage(SUCCESS_MESSAGE);
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

    public String getCheckoutableBooks() {
        String message = "";
        for (Book checkoutableBook : getAvailableBooks()) {
            message += checkoutableBook.getId() + ". " + checkoutableBook.getTitle() + "\n";
        }
        return message;
    }

    public void checkoutBook(int bookId) {
        selectBookById(bookId);
        bookRepository.removeBook(getSelectedBook());
    }

    public void selectBookById(int id) {
        Book foundBook = bookRepository.findBookById(id);
        selectedBook = foundBook;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    private int getBookIdInput() {
        String bookIdInput = startInput().next();
        int bookId = Integer.parseInt(bookIdInput);
        return bookId;
    }
}
