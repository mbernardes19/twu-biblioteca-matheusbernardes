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
        assertEquals(menu.showOptions(), "1 - List of books\n");
    }
}
