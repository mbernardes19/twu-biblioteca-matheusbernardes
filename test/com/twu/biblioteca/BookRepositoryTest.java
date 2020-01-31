package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;


public class BookRepositoryTest {

    private BookRepository bookRepo;

    @Before
    public void before() {
        bookRepo = new BookRepository();
    }

    @Test
    public void shouldHaveAllBooksPopulatedAtStart() {
        List<Book> expectedAvailableBooks = new ArrayList<>();
        expectedAvailableBooks.add(new Book(1, "Orope: The White Snake","Guenevere Lee", "2018"));
        expectedAvailableBooks.add(new Book(2, "The Great Gatsby","F. Scott Fitzgerald", "1925"));
        expectedAvailableBooks.add(new Book(3, "Boulevard Dreams","E. Ryan Janz", "2018"));
        List<Book> actualAvailableBooks = bookRepo.getAvailableBooks();
        assertTrue(actualAvailableBooks.containsAll(expectedAvailableBooks));
    }

    @Test
    public void shouldFindABook() throws BookNotFoundException {
        Book expectedBook = new Book(1, "Orope: The White Snake","Guenevere Lee", "2018");
        Book foundBook = bookRepo.findBookById(1);
        assertEquals(foundBook, expectedBook);
    }

    @Test
    public void shoudlRemoveABook() {
        Book expectedBook = new Book(1, "Orope: The White Snake","Guenevere Lee", "2018");
        bookRepo.removeBook(expectedBook);
        assertFalse(bookRepo.getAvailableBooks().contains(expectedBook));
    }

    @Test(expected = BookNotFoundException.class)
    public void shouldThrowExceptionWhenSearchingForBookThatDoesntExist() throws BookNotFoundException {
        bookRepo.findBookById(45);
    }

}


