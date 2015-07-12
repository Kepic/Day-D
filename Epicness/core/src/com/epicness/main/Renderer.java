package com.epicness.main;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.epicness.screens.EndScreen;
import com.epicness.screens.MapScreen;
import com.epicness.screens.MenuScreen;
import com.epicness.screens.SplashScreen;
import com.epicness.toolbox.Sizes;
import com.epicness.toolbox.Textures;

public class Renderer extends ApplicationAdapter {

    public static boolean onSplashScreen = true;
    public static boolean onMenuScreen = false;
    public static boolean onMapScreen = false;
    public static boolean onEndScreen = false;

    @Override
    public void create() {
        Textures.create();
        Sizes.create();
        SplashScreen.create();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (onMapScreen) {
            MapScreen.render();
            return;
        } else if (onMenuScreen) {
            MenuScreen.render();
            return;
        } else if (onEndScreen) {
            EndScreen.render();
            return;
        } else if (onSplashScreen) {
            SplashScreen.render();
        }
    }
}
