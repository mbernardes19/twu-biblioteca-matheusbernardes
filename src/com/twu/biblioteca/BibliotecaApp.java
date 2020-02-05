package com.twu.biblioteca;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private static BookRepository bookRepository = new BookRepository();
    private static List<Book> availableBooks = bookRepository.getAvailableBooks();
    private static MainMenu mainMenu = new MainMenu();
    private static MainMenuOption selectedOption;
    private Book selectedBook;

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        Printer.printMessage(Message.WELCOME_MESSAGE);
        Scanner input = startInput();

        // Show main menu and check selected option
        while(!isSelectedOptionValid() || selectedOption != MainMenuOption.OPTION_0) {
            try {
                Printer.printMessage(mainMenuOptions());
                app.selectOption(input.nextInt());
            } catch(InvalidParameterException e){
                Printer.printError(Message.ERROR_MESSAGE);
            }
        }

    }

    public void printAvailableBooks() {
        Printer.printTable("TITLE","AUTHOR", "PUBLICATION YEAR");
        for (Book availableBook : getAvailableBooks()) {
            Printer.printTable(availableBook.getTitle(), availableBook.getAuthor(), availableBook.getPublicationYear());
        }
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
        Printer.printMessage("Select a book to checkout: ");
        Printer.printMessage(getCheckoutableBooks());
        try {
            checkoutBook(getBookIdInput());
            Printer.printMessage(Message.BOOK_CHECKOUT_SUCCESS_MESSAGE);
        } catch(BookNotFoundException err) {
            Printer.printMessage(Message.BOOK_CHECKOUT_ERROR_MESSAGE);
        }
    }

    private void doBookReturn() {
        selectedOption = MainMenuOption.OPTION_3;
        Printer.printMessage("Select a book to return: ");
        Printer.printMessage(getAllCheckedOutBooks());
        try {
            returnBook(getBookIdInput());
            Printer.printMessage(Message.BOOK_RETURN_SUCCESS_MESSAGE);
        } catch (BookNotFoundException err) {
            Printer.printMessage(Message.BOOK_RETURN_ERROR_MESSAGE);
        }
    }

    private void doBookList() {
        selectedOption = MainMenuOption.OPTION_1;
        printAvailableBooks();
    }
}
