package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleTest {

    private BibliotecaApp app;

    @Before
    public void init() {
        app = new BibliotecaApp();
    }

    @Test
    public void shouldDisplayWelcomeMessage() {
        BibliotecaApp.showWelcomeMessage();
        assertEquals("Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!\n", BibliotecaApp.lastMessage);
    }

    @Test
    public void shouldShowAListOfAllLibraryBooks(){
        BibliotecaApp.displayAllBooks();
        assertEquals("Orope: The White Snake - Guenevere Lee\nThe Great Gatsby - F. Scott Fitzgerald\nBoulevard Dreams - E. Ryan Janz", BibliotecaApp.lastMessage);
    }
}
