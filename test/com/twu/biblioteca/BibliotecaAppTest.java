package com.twu.biblioteca;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BibliotecaAppTest {

    private BibliotecaApp app;

    @Before
    public void before() {
        app = new BibliotecaApp();
    }

    @Test
    public void shouldDisplayWelcomeMessage() {
        BibliotecaApp.getWelcomeMessage();
        assertEquals("Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!", BibliotecaApp.getWelcomeMessage());
    }

    @Test
    public void shouldShowAListOfAllAvailableBooks(){
        String allBooks = app.getAllBooks();
        assertTrue(
                allBooks.contains("Orope: The White Snake") &&
                        allBooks.contains("The Great Gatsby") &&
                allBooks.contains("Boulevard Dreams")
        );
    }

    @Test
    public void availableBooksShouldBePopulatedFromStart() {
        List<Book> expectedAvailableBooks = new ArrayList<>();
        expectedAvailableBooks.add(new Book("Orope: The White Snake","Guenevere Lee", "2018"));
        expectedAvailableBooks.add(new Book("The Great Gatsby","F. Scott Fitzgerald", "1925"));
        expectedAvailableBooks.add(new Book("Boulevard Dreams","E. Ryan Janz", "2018"));
        assertEquals(BibliotecaApp.getAvailableBooks().get(0).getTitle(), expectedAvailableBooks.get(0).getTitle());
        assertEquals(BibliotecaApp.getAvailableBooks().get(1).getTitle(), expectedAvailableBooks.get(1).getTitle());
        assertEquals(BibliotecaApp.getAvailableBooks().get(2).getTitle(), expectedAvailableBooks.get(2).getTitle());
    }

    @Test
    public void shouldShowBooksAuthorAndPublicationYear() {
        String expectedBooks = app.getAllBooks();
        assertTrue(
                expectedBooks.contains("Guenevere Lee") &&
                        expectedBooks.contains("2018") &&
                        expectedBooks.contains("F. Scott Fitzgerald") &&
                        expectedBooks.contains("1925") &&
                        expectedBooks.contains("E. Ryan Janz") &&
                        expectedBooks.contains("2018")
        );
    }

    @Test
    public void shouldShowAllAvailableBooksInTableFormat() {
        String expected = String.format("%-30.30s %-30.30s %-30.30s%n", "TITLE", "AUTHOR", "PUBLICATION YEAR");
        expected += String.format("%-30.30s %-30.30s %-30.30s%n", "Orope: The White Snake", "Guenevere Lee", "2018");
        expected += String.format("%-30.30s %-30.30s %-30.30s%n", "The Great Gatsby", "F. Scott Fitzgerald", "1925");
        expected += String.format("%-30.30s %-30.30s %-30.30s%n", "Boulevard Dreams", "E. Ryan Janz", "2018");

        assertEquals(expected, app.getAllBooks());
    }

    @Test
    public void shouldFirstMainMenuOption() {
        assertEquals("1 - List of books\n", BibliotecaApp.mainMenuOptions());
    }

    @Test
    public void ifOneIsPressed_ShouldSelectFirstOptionFromMainMenu() {
         app.selectOption(1);
         assertEquals("1 - List of books", BibliotecaApp.getSelectedOption().getDescription());
         assertEquals(1, BibliotecaApp.getSelectedOption().getValue());
    }

    @Test(expected = InvalidParameterException.class)
    public void ShouldThrowInvalidParameterException_IfASelectedOptionIsValid() {
        app.selectOption(50);
    }
}
