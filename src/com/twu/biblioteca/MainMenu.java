package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenu {
    public MainMenu() {

    }

    public String showOptions() {
        String optionList = "";
        for (MainMenuOption option : getMainMenuOptions()) {
            optionList += option.getDescription()+"\n";
        }

        return optionList;
    }

    public MainMenuOption[] getMainMenuOptions() {
        return MainMenuOption.class.getEnumConstants();
    }
}
