package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    private Book book;

    @Before
    public void before() {
        book = new Book();
    }

    @Test
    public void shouldCreateANulledBook() {
        assertEquals(null, book.getTitle());
        assertEquals(null, book.getAuthor());
        assertEquals(null, book.getPublicationYear());
    }

    @Test
    public void shouldCreateAFilledBook() {
        Book newBook = new Book("The Test Book", "Matheus", "2020");
        assertEquals("The Test Book", newBook.getTitle());
        assertEquals("Matheus", newBook.getAuthor());
        assertEquals("2020", newBook.getPublicationYear());
    }
}