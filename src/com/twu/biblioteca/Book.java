package com.twu.biblioteca;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publicationYear;

    public Book(int id, String title, String author, String publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getId() {
        return this.id;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Book)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Book otherBook = (Book) obj;
        if (this.id == ((Book) obj).getId()) {
            return true;
        } else {
            return false;
        }
    }
}
