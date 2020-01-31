package com.twu.biblioteca;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!";
    private static final String ERROR_MESSAGE = "Please select a valid option!";
    public static final String BOOK_CHECKOUT_SUCCESS_MESSAGE = "Thank you! Enjoy the book\n";
    public static final String BOOK_CHECKOUT_ERROR_MESSAGE = "Sorry, that book is not available\n";
    public static final String BOOK_RETURN_SUCCESS_MESSAGE = "Thank you for returning the book\n";
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

    public String getAllAvailableBooks() {
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
                doBookList();
                break;
            case 2:
                doBookCheckout();
                break;
            case 3:
                doBookReturn();
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

    public void checkoutBook(int bookId) throws BookNotFoundException {
        selectBookById(bookId, BookListOption.AVAILABLE_BOOKS);
        bookRepository.checkoutBook(getSelectedBook());
    }

    public void selectBookById(int id, BookListOption list) throws BookNotFoundException {
        if (list == BookListOption.AVAILABLE_BOOKS) {
            Book foundBook = bookRepository.findBookInAvailableBooks(id);
            selectedBook = foundBook;
        }
        if (list == BookListOption.CHECKEDOUT_BOOKS) {
            Book foundBook = bookRepository.findBookInCheckedOutBooks(id);
            selectedBook = foundBook;
        }
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    private int getBookIdInput() {
        String bookIdInput = startInput().next();
        int bookId = Integer.parseInt(bookIdInput);
        return bookId;
    }

    public List<Book> getCheckedOutBooks() {
        return bookRepository.getCheckedOutBooks();
    }

    public void setCheckedOutBooks(List<Book> bookList) {
        bookRepository.setCheckedOutBooks(bookList);
    }

    public String getAllCheckedOutBooks() {
        String message = "";
        for (Book book : getCheckedOutBooks()) {
            message += book.getId() + ". " + book.getTitle() + "\n";
        }
        return message;
    }

    public void returnBook(int bookId) throws BookNotFoundException {
        selectBookById(bookId, BookListOption.CHECKEDOUT_BOOKS);
        bookRepository.returnBook(getSelectedBook());
    }

    private void doBookCheckout() {
        selectedOption = MainMenuOption.OPTION_2;
        showMessage("Select a book to checkout: ");
        showMessage(getCheckoutableBooks());
        try {
            checkoutBook(getBookIdInput());
            showMessage(BOOK_CHECKOUT_SUCCESS_MESSAGE);
        } catch(BookNotFoundException err) {
            showMessage(BOOK_CHECKOUT_ERROR_MESSAGE);
        }
    }

    private void doBookReturn() {
        selectedOption = MainMenuOption.OPTION_3;
        showMessage("Select a book to return: ");
        showMessage(getAllCheckedOutBooks());
        try {
            returnBook(getBookIdInput());
            showMessage(BOOK_RETURN_SUCCESS_MESSAGE);
        } catch (BookNotFoundException err) {

        }
    }

    private void doBookList() {
        selectedOption = MainMenuOption.OPTION_1;
        showMessage(getAllAvailableBooks());
    }
}
