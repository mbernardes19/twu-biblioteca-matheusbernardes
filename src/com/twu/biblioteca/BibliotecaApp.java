package com.twu.biblioteca;

public class BibliotecaApp {
    public static String lastMessage;
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!\n";
    private static String availableBooks = "Orope: The White Snake - Guenevere Lee\nThe Great Gatsby - F. Scott Fitzgerald\nBoulevard Dreams - E. Ryan Janz";

    public static void main(String[] args) {
        BibliotecaApp.showWelcomeMessage();
    }

    public static void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        lastMessage = WELCOME_MESSAGE;
    }

    public static void displayAllBooks() {
        System.out.println(availableBooks);
        lastMessage = availableBooks;
    }
}
