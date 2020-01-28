package com.twu.biblioteca;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        BibliotecaApp.showWelcomeMessage();
        assertEquals("Welcome to Biblioteca! Your one-stop-shop for great book titles in Bangalore!\n", BibliotecaApp.showWelcomeMessage());
    }

    @Test
    public void shouldShowAListOfAllAvailableBooks(){
        String allBooks = BibliotecaApp.displayAllBooks();
        assertTrue(
                allBooks.contains("Orope: The White Snake") &&
                        allBooks.contains("The Great Gatsby") &&
                allBooks.contains("Boulevard Dreams")
        );
    }

    @Test
    public void availableBooksShouldBePopulatedFromStart() {
        List<Book> expectedAvailableBooks = new ArrayList<>();
        expectedAvailableBooks.add(new Book("Orope: The White Snake","Guenevere Lee", "March 6th 2018"));
        expectedAvailableBooks.add(new Book("The Great Gatsby","F. Scott Fitzgerald", "1925"));
        expectedAvailableBooks.add(new Book("Boulevard Dreams","E. Ryan Janz", "August 23rd 2018"));
        assertEquals(BibliotecaApp.getAvailableBooks().get(0).getTitle(), expectedAvailableBooks.get(0).getTitle());
        assertEquals(BibliotecaApp.getAvailableBooks().get(1).getTitle(), expectedAvailableBooks.get(1).getTitle());
        assertEquals(BibliotecaApp.getAvailableBooks().get(2).getTitle(), expectedAvailableBooks.get(2).getTitle());
    }

    @Test
    public void shouldShowBooksAuthorAndPublishedYear() {
        String expectedBooks = BibliotecaApp.displayAllBooks();
        assertTrue(
                expectedBooks.contains("Guenevere Lee") &&
                        expectedBooks.contains("March 6th 2018") &&
                        expectedBooks.contains("F. Scott Fitzgerald") &&
                        expectedBooks.contains("1925") &&
                        expectedBooks.contains("E. Ryan Janz") &&
                        expectedBooks.contains("August 23rd 2018")
        );
    }
}
