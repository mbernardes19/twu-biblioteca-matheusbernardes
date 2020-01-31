package com.twu.biblioteca;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class BibliotecaAppTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private BibliotecaApp app;
    private List<Book> expectedAvailableBooks;

    @Before
    public void before() {
        app = new BibliotecaApp();

        expectedAvailableBooks = new ArrayList<>();
        expectedAvailableBooks.add(new Book(1,"Orope: The White Snake","Guenevere Lee", "2018"));
        expectedAvailableBooks.add(new Book(2,"The Great Gatsby","F. Scott Fitzgerald", "1925"));
        expectedAvailableBooks.add(new Book(3,"Boulevard Dreams","E. Ryan Janz", "2018"));

        app.setAvailableBooks(expectedAvailableBooks);
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
        expectedAvailableBooks.add(new Book(1,"Orope: The White Snake","Guenevere Lee", "2018"));
        expectedAvailableBooks.add(new Book(2,"The Great Gatsby","F. Scott Fitzgerald", "1925"));
        expectedAvailableBooks.add(new Book(3,"Boulevard Dreams","E. Ryan Janz", "2018"));
        assertEquals(app.getAvailableBooks().get(0).getTitle(), expectedAvailableBooks.get(0).getTitle());
        assertEquals(app.getAvailableBooks().get(1).getTitle(), expectedAvailableBooks.get(1).getTitle());
        assertEquals(app.getAvailableBooks().get(2).getTitle(), expectedAvailableBooks.get(2).getTitle());
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
    public void shouldShowFirstMainMenuOption() {
        assertTrue(BibliotecaApp.mainMenuOptions().contains("1 - List of books\n"));
    }

    @Test
    public void ifOneIsPressed_ShouldSelectFirstOptionFromMainMenu() {
         app.selectOption(1);
         assertEquals("1 - List of books", BibliotecaApp.getSelectedOption().getDescription());
         assertEquals(1, BibliotecaApp.getSelectedOption().getValue());
    }

    @Test(expected = InvalidParameterException.class)
    public void shouldThrowInvalidParameterException_IfASelectedOptionIsValid() {
        app.selectOption(50);
    }

    @Test
    public void shouldEndApplication_IfZeroOptionIsSelectedFromMainMenu() {
        exit.expectSystemExit();
        app.selectOption(0);
    }

    @Test
    public void ifTwoIsPressed_ShouldSelectSecondOptionFromMainMenu() {
        InputStream systemIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        app.selectOption(2);
        assertEquals("2 - Checkout a book", BibliotecaApp.getSelectedOption().getDescription());
        assertEquals(2, BibliotecaApp.getSelectedOption().getValue());
    }

    @Test
    public void shouldShowAllCheckoutableBooks() {
        assertEquals("1. Orope: The White Snake\n2. The Great Gatsby\n3. Boulevard Dreams\n", app.getCheckoutableBooks());
    }

    @Test
    public void shouldCheckoutABook() {
        Book expectedBook = new Book(3, "Orope: The White Snake", "Guenevere Lee", "2018");
        app.checkoutBook(3);
        assertFalse(app.getAvailableBooks().contains(expectedBook));
    }

    @Test
    public void shouldSelectABookToMakeAnAction() {
        Book expectedBook = new Book(2, "Orope: The White Snake", "Guenevere Lee", "2018");
        app.selectBookById(2);
        assertEquals(expectedBook, app.getSelectedBook());
    }

    @Test
    public void shouldSendSucessMessageOnBookCheckout() {
        assertEquals("Thank you! Enjoy the book\n", app.SUCCESS_MESSAGE);
    }

}
