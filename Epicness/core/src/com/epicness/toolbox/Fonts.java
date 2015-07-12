package com.epicness.toolbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.epicness.screens.SplashScreen;

/**
 * Created by Frontanilla F. on 05/07/2015.
 */
public class Fonts {

    public static BitmapFont neon, glow;

    public static void create() {
        neon = new BitmapFont(Gdx.files.internal("fonts/neon.fnt"));
        glow = new BitmapFont(Gdx.files.internal("fonts/glow.fnt"));
        SplashScreen.afterFontsCreated();
    }
}
