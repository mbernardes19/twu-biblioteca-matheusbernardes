package com.twu.biblioteca;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookRepository {
    private List<Book> availableBooks = new ArrayList<>();

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

    public Book findBookById(int id) {
        Book bookFound = availableBooks.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .get();
        return bookFound;
    }

    public void removeBook(Book book) {
        List<Book> newAvailableBooks = availableBooks.stream()
                .filter(availableBook -> !availableBook.equals(book))
                .collect(Collectors.toList());

        this.availableBooks = newAvailableBooks;
    }
}
