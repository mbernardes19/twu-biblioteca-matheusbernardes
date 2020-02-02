package com.twu.biblioteca;

import java.util.List;

public enum MainMenuOption {
    OPTION_1(1,"1 - List of books"),
    OPTION_2(2, "2 - Checkout a book"),
    OPTION_3(3, "3 - Return a book"),
    OPTION_4(4, "4 - List of movies"),
    OPTION_0(0, "0 - Exit");

    private MainMenuOption(int value, String description) {
        this.value = value;
        this.description = description;
    }

    private String description;
    private int value;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public MainMenuOption[] getAllOptions() {
        return MainMenuOption.class.getEnumConstants();
    }
}
