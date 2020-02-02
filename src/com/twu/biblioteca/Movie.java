package com.twu.biblioteca;

import java.security.InvalidParameterException;

public class Movie {
    private String name;
    private String year;
    private String director;
    private int rating;

    public Movie(String name, String year, String director, int rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        try {
            setRating(rating);
        } catch (InvalidParameterException err) {
            System.out.println(err.getMessage());
        }

    }

    public Movie(String name, String year, String director) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) throws InvalidParameterException {
        if (rating < 0 || rating > 10) {
            throw new InvalidParameterException("A movie should be rated from 1 to 10");
        }
        this.rating = rating;
    }

    public String getRatingText() {
        if (rating == 0) {
            return "Unrated";
        }
         else {
             return String.valueOf(rating);
        }
    }
}
