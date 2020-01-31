package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void shouldCreateAFilledBookWithId() {
        Book newBook = new Book(1,"The Test Book", "Matheus", "2020");
        assertEquals(1, newBook.getId());
        assertEquals("The Test Book", newBook.getTitle());
        assertEquals("Matheus", newBook.getAuthor());
        assertEquals("2020", newBook.getPublicationYear());
    }

    @Test
    public void shouldAssertThatTwoBooksAreEqualBasedOnId() {
        Book book1 = new Book(34, "Test", "Joseph", "2020");
        Book book2 = new Book(34, "Test123", "Jose", "2019");
        assertTrue(book1.equals(book2));
    }

    @Test
    public void shouldAssertThatTwoBooksAreDifferentBasedOnId() {
        Book book1 = new Book(34, "Test", "Joseph", "2020");
        Book book2 = new Book(23, "Test", "Joseph", "2020");
        assertFalse(book1.equals(book2));
    }
}