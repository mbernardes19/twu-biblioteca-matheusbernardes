package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainMenuTest {

    private MainMenu menu;

    @Before
    public void before() {
        menu = new MainMenu();
    }

    @Test
    public void shouldShowAllOptions() {
        assertEquals(menu.showOptions(), "1 - List of books\n2 - Checkout a book\n3 - Return a book\n4 - List of movies\n0 - Exit\n");
    }
}
