package com.twu.biblioteca;

import org.junit.Test;

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
}
