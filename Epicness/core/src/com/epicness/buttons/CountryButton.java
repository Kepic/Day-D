package com.epicness.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.epicness.game.Country;

/**
 * Created by Frontanilla F. on 11/07/2015.
 */
public class CountryButton extends Button {

    private Country country;
    private boolean enabled;

    public CountryButton(Texture texture, float x, float y, float width, float height,
                         Color color, Country country) {
        super(texture, x, y, width, height, country.getName(), color);
        this.country = country;
        country.setButton(this);
        enabled = true;
    }

    public Country getCountry() {
        return country;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getEnabled() {
        return enabled;
    }
}
