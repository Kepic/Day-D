package com.epicness.game;

import com.epicness.buttons.CountryButton;
import com.epicness.screens.MapScreen;

/**
 * Created by Frontanilla F. on 10/07/2015.
 */
public class Country {

    private String name;
    private CountryButton b;

    public Country(String name) {
        this.name = name;
    }

    public void disaster() {
        MapScreen.availableCountries.remove(this);
        b.setEnabled(false);
    }

    public String toString() {
        return getName();
    }

    public String getName() {
        return name;
    }

    public void setButton(CountryButton button) {
        this.b = button;
    }
}
