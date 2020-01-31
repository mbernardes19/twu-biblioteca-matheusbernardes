package com.twu.biblioteca;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookRepository {
    private List<Book> availableBooks = new ArrayList<>();
    private List<Book> checkedOutBooks = new ArrayList<>();

    public BookRepository() {
        populateAvailableBooks();
    }

    private void populateAvailableBooks() {
        if (availableBooks.size() == 3) {
            return;
        }
        availableBooks.add(new Book(1,"Orope: The White Snake","Guenevere Lee", "2018"));
        availableBooks.add(new Book(2, "The Great Gatsby","F. Scott Fitzgerald", "1925"));
        availableBooks.add(new Book(3,"Boulevard Dreams","E. Ryan Janz", "2018"));
    }

    public List<Book> getAvailableBooks() {
        return availableBooks;
    }
    public void setAvailableBooks(List<Book> bookList) {
        this.availableBooks = bookList;
    }
    public List<Book> getCheckedOutBooks() { return checkedOutBooks; }
    public void setCheckedOutBooks(List<Book> bookList) { this.checkedOutBooks = bookList; }

    public Book findBookInAvailableBooks(int id) throws BookNotFoundException {
        Book bookFound = availableBooks.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Sorry, that book is not available"));
        return bookFound;
    }

    public Book findBookInCheckedOutBooks(int id) throws BookNotFoundException {
        Book bookFound = checkedOutBooks.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Sorry, that book is not available"));
        return bookFound;
    }

    public void checkoutBook(Book book) {
        this.checkedOutBooks.add(book);
        this.availableBooks.remove(book);
    }

    public void returnBook(Book book) {
        this.availableBooks.add(book);
        this.checkedOutBooks.remove(book);
    }
}
