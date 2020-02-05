package com.twu.biblioteca;

import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
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
    private List<Book> checkedOutBooks;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private String output = outContent.toString();

    @Before
    public void before() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        output = outContent.toString();


        app = new BibliotecaApp();

        checkedOutBooks = new ArrayList<>();
        expectedAvailableBooks = new ArrayList<>();

        expectedAvailableBooks.add(new Book(1,"Orope: The White Snake","Guenevere Lee", "2018"));
        expectedAvailableBooks.add(new Book(2,"The Great Gatsby","F. Scott Fitzgerald", "1925"));
        expectedAvailableBooks.add(new Book(3,"Boulevard Dreams","E. Ryan Janz", "2018"));

        app.setCheckedOutBooks(checkedOutBooks);
        app.setAvailableBooks(expectedAvailableBooks);
    }

    @After
    public void restoreStream() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void shouldShowAListOfAllAvailableBooks(){
        app.printAvailableBooks();
        assertTrue(
                outContent.toString().contains("Boulevard Dreams") &&
                        outContent.toString().contains("Orope: The White Snake") &&
                        outContent.toString().contains("Boulevard Dreams")
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
        app.printAvailableBooks();
        assertTrue(
                outContent.toString().contains("Guenevere Lee") &&
                        outContent.toString().contains("2018") &&
                        outContent.toString().contains("F. Scott Fitzgerald") &&
                        outContent.toString().contains("1925") &&
                        outContent.toString().contains("E. Ryan Janz") &&
                        outContent.toString().contains("2018")
        );
    }

    @Test
    public void shouldShowAllAvailableBooksInTableFormat() {
        String expected = String.format("%-30.30s %-30.30s %-30.30s%n", "TITLE", "AUTHOR", "PUBLICATION YEAR");
        expected += String.format("%-30.30s %-30.30s %-30.30s%n", "Orope: The White Snake", "Guenevere Lee", "2018");
        expected += String.format("%-30.30s %-30.30s %-30.30s%n", "The Great Gatsby", "F. Scott Fitzgerald", "1925");
        expected += String.format("%-30.30s %-30.30s %-30.30s%n", "Boulevard Dreams", "E. Ryan Janz", "2018");

        app.printAvailableBooks();

        assertEquals(expected, outContent.toString());
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
    public void shouldCheckoutABook() throws BookNotFoundException {
        Book expectedBook = new Book(3, "Orope: The White Snake", "Guenevere Lee", "2018");
        app.checkoutBook(3);
        assertFalse(app.getAvailableBooks().contains(expectedBook));
    }

    @Test
    public void shouldSelectABookToMakeAnAction() throws BookNotFoundException {
        Book expectedBook = new Book(2, "Orope: The White Snake", "Guenevere Lee", "2018");
        app.selectBookById(2, BookListOption.AVAILABLE_BOOKS);
        assertEquals(expectedBook, app.getSelectedBook());
    }

    @Test
    public void shouldSendSucessMessageOnBookCheckout() {
        assertEquals("Thank you! Enjoy the book\n", Message.BOOK_CHECKOUT_SUCCESS_MESSAGE);
    }

    @Test(expected = BookNotFoundException.class)
    public void shouldThrowError_IfSelectedBookForCheckOutDoesNotExist() throws BookNotFoundException {
        app.selectBookById(45, BookListOption.AVAILABLE_BOOKS);
    }

    @Test
    public void shouldShowOneCheckedOutBook() throws BookNotFoundException {
        app.checkoutBook(1);
        assertEquals("1. Orope: The White Snake\n", app.getAllCheckedOutBooks());
    }

    @Test
    public void shouldShowTwoCheckedOutBooks() throws BookNotFoundException {
        app.checkoutBook(3);
        app.checkoutBook(2);
        assertEquals("3. Boulevard Dreams\n2. The Great Gatsby\n", app.getAllCheckedOutBooks());
    }

    @Test
    public void ifThreeIsPressed_ShouldSelectThirdOptionFromMainMenu() {
        InputStream systemIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
        System.setIn(in);

        app.selectOption(3);
        assertEquals("3 - Return a book", BibliotecaApp.getSelectedOption().getDescription());
        assertEquals(3, BibliotecaApp.getSelectedOption().getValue());
    }

    @Test
    public void shouldReturnABook() throws BookNotFoundException {
        Book expectedBook = new Book(1, "Orope: The White Snake","Guenevere Lee", "2018");

        app.checkoutBook(1);
        assertFalse(app.getAvailableBooks().contains(expectedBook));
        assertTrue(app.getCheckedOutBooks().contains(expectedBook));
        app.returnBook(1);
        assertTrue(app.getAvailableBooks().contains(expectedBook));
        assertFalse(app.getCheckedOutBooks().contains(expectedBook));
        assertEquals("2. The Great Gatsby\n3. Boulevard Dreams\n1. Orope: The White Snake\n", app.getCheckoutableBooks());
    }

    @Test
    public void shouldSendSucessMessageOnBookReturn() {
        assertEquals("Thank you for returning the book\n", Message.BOOK_RETURN_SUCCESS_MESSAGE);
    }

    @Test(expected = BookNotFoundException.class)
    public void shouldThrowError_IfSelectedBookForReturnDoesNotExist() throws BookNotFoundException {
        app.selectBookById(1, BookListOption.CHECKEDOUT_BOOKS);
    }

    @Test(expected = BookNotFoundException.class)
    public void shouldThrowError_IfSelectedBookForReturnDoesNotExist2() throws BookNotFoundException {
        app.checkoutBook(1);
        app.checkoutBook(2);
        app.selectBookById(1, BookListOption.CHECKEDOUT_BOOKS);
        app.selectBookById(3, BookListOption.CHECKEDOUT_BOOKS);
    }
}
