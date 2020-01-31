package com.twu.biblioteca;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
