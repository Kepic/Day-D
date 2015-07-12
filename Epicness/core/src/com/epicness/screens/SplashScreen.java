package com.epicness.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.epicness.main.Renderer;
import com.epicness.toolbox.Fonts;
import com.epicness.toolbox.Sizes;
import com.epicness.toolbox.Textures;

/**
 * Created by Frontanilla F. on 02/07/2015.
 */
public class SplashScreen {

    private static SpriteBatch splashBatch;
    private static float alpha;

    public static void create() {
        alpha = 0;
        splashBatch = new SpriteBatch();

        // LOAD STUFF
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Fonts.create();
            }
        }
                , 1         // delay
        );

        // FADE IN
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (alpha + 0.01 <= 1) {
                    alpha += 0.01;
                }
            }
        }
                , 0         // delay
                , 0.01f     // cada cuanto
                , 100       // numero de ejecuciones
        );
    }

    public static void afterFontsCreated() {
        // FADE OUT
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (alpha - 0.01 >= 0) {
                    alpha -= 0.01;
                }
            }
        }
                , 0         // delay
                , 0.01f     // cada cuanto
                , 100       // numero de ejecuciones
        );
        // TO THE MENU SCREEN
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                transition();
            }
        }, 1);              // delay
    }

    public static void render() {
        splashBatch.setColor(1, 1, 1, alpha);
        splashBatch.begin();
        splashBatch.draw(
                Textures.spbg, // LOGO
                0,
                0,
                Sizes.width,
                Sizes.height
        );
        splashBatch.draw(
                Textures.bgimg, // LOGO
                Sizes.width / 2 - Sizes.width * 0.2f,
                0,
                Sizes.width * 0.4f,
                Sizes.height
        );
        splashBatch.end();
    }

    private static void transition() {
        Renderer.onSplashScreen = false;
        Renderer.onMenuScreen = true;
        Timer.instance().clear();
        MenuScreen.create();
        Gdx.input.setInputProcessor(MenuScreen.getInputProcessor());
    }
}
