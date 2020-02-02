package com.twu.biblioteca;

import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void shouldCreateAMovieFilledWithData() {
        Movie movie = new Movie("The Movie", "2020", "Joseph Blinker", 5);
        assertEquals("The Movie", movie.getName());
        assertEquals("2020", movie.getYear());
        assertEquals("Joseph Blinker", movie.getDirector());
        assertEquals(5, movie.getRating());
    }

    @Test(expected = InvalidParameterException.class)
    public void shouldThrowException_IfRatingIsAbove10() {
        Movie movie = new Movie("The Movie", "2020", "Joseph Blinker", 5);
        movie.setRating(50);
    }

    @Test(expected = InvalidParameterException.class)
    public void shouldThrowException_IfRatingIsBelow1AndDifferentFrom0() {
        Movie movie = new Movie("The Movie", "2020", "Joseph Blinker", 5);
        movie.setRating(-5);
    }

    @Test
    public void shouldBeAbleToSetMovieAsUnrated() {
        Movie movie = new Movie("The Movie", "2020", "Joseph Blinker", 5);
        movie.setRating(0);
        assertEquals(0, movie.getRating());
    }

    @Test
    public void shouldCreateAMovieAsUnrated() {
        Movie movie = new Movie("The Movie", "2020", "Joseph Blinker");
        assertEquals("The Movie", movie.getName());
        assertEquals("2020", movie.getYear());
        assertEquals("Joseph Blinker", movie.getDirector());
        assertEquals(0, movie.getRating());
    }

    @Test
    public void ifMovieIsUnrated_ShouldReturnUnratedDescription() {
        Movie movie = new Movie("The Movie", "2020", "Joseph Blinker");
        assertEquals("Unrated", movie.getRatingText());
    }
    @Test
    public void shouldReturnTheRatingAsString() {
        Movie movie = new Movie("The Movie", "2020", "Joseph Blinker", 8);
        assertEquals("8", movie.getRatingText());
    }

}
