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
        Book foundBook = bookRepo.findBookInAvailableBooks(1);
        assertEquals(foundBook, expectedBook);
    }

    @Test
    public void shouldCheckoutABook() {
        Book expectedBook = new Book(1, "Orope: The White Snake","Guenevere Lee", "2018");
        bookRepo.checkoutBook(expectedBook);
        assertFalse(bookRepo.getAvailableBooks().contains(expectedBook));
        assertTrue(bookRepo.getCheckedOutBooks().contains(expectedBook));
    }

    @Test
    public void shouldCheckoutTwoBooks() {
        Book book1 = new Book(1, "Orope: The White Snake","Guenevere Lee", "2018");
        Book book2 = new Book(2, "Book2","Joseph", "2016");
        bookRepo.checkoutBook(book1);
        bookRepo.checkoutBook(book2);
        assertFalse(bookRepo.getAvailableBooks().contains(book1) && bookRepo.getAvailableBooks().contains(book2));
        assertTrue(bookRepo.getCheckedOutBooks().contains(book1) && bookRepo.getCheckedOutBooks().contains(book1));
    }

    @Test(expected = BookNotFoundException.class)
    public void shouldThrowExceptionWhenSearchingForBookThatDoesntExist() throws BookNotFoundException {
        bookRepo.findBookInAvailableBooks(45);
    }

    @Test
    public void shouldReturnABook() {
        Book expectedBook = new Book(1, "Orope: The White Snake","Guenevere Lee", "2018");
        bookRepo.checkoutBook(expectedBook);
        assertFalse(bookRepo.getAvailableBooks().contains(expectedBook));
        assertTrue(bookRepo.getCheckedOutBooks().contains(expectedBook));
        bookRepo.returnBook(expectedBook);
        assertTrue(bookRepo.getAvailableBooks().contains(expectedBook));
        assertFalse(bookRepo.getCheckedOutBooks().contains(expectedBook));

    }

}


