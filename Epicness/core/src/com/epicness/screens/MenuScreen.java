package com.epicness.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.epicness.buttons.Button;
import com.epicness.main.Renderer;
import com.epicness.toolbox.Sizes;
import com.epicness.toolbox.Textures;

/**
 * Created by Frontanilla F. on 02/07/2015.
 */
public class MenuScreen {

    private static float alpha;
    private static SpriteBatch menuBatch;

    public static void create() {
        ButtonManager.create();
        alpha = 0;
        menuBatch = new SpriteBatch();

        // FADE IN
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (alpha + 0.01 <= 1) {
                    alpha += 0.01;
                }
            }
        }
                , 0     // delay
                , 0.01f // cada cuanto
                , 100   // numero de ejecuciones
        );
    }

    public static void render() {

        // BACKGROUND

        menuBatch.begin();

        menuBatch.setColor(1, 1, 1, alpha * 0.8f);
        menuBatch.draw(
                Textures.map,
                0,
                0,
                Sizes.width,
                Sizes.height
        );

        // BUTTONS

        for (Button b : ButtonManager.buttons) {
            menuBatch.setColor(1, 1, 1, alpha);
            menuBatch.setColor(b.getColor().r, b.getColor().g, b.getColor().b, alpha);
            menuBatch.draw(
                    b.getTexture(),
                    b.getX(),
                    b.getY(),
                    b.getWidth(),
                    b.getHeight()
            );
        }
        menuBatch.end();

    }

    public static InputProcessor getInputProcessor() {
        return InputManager.getInstance();
    }

    private static void playButtonTransition() {
        Renderer.onMenuScreen = false;
        Renderer.onMapScreen = true;
        Timer.instance().clear();                                           // IMPORTANT
        MapScreen.create();
        Gdx.input.setInputProcessor(MapScreen.getInputProcessor());
    }

    private static class InputManager extends InputAdapter {
        private static InputManager instance = new InputManager();

        private static InputManager getInstance() {
            return instance;
        }

        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            screenY = Sizes.height - screenY;
            for (Button b : ButtonManager.buttons) {
                if (b.isWithin(screenX, screenY)) {
                    if (b.getName().equals("Play")) {
                        playButtonTransition();
                    } else if (b.getName().equals("Tutorial")) {
                        System.out.println("Tutorial");
                    } else if (b.getName().equals("Options")) {
                        System.out.println("Options");
                    }
                }
            }
            return true;
        }
    }

    private static class ButtonManager {
        private static Button[] buttons;

        private static void create() {
            buttons = new Button[3];
            buttons[0] = new Button(
                    Textures.playButton,
                    Sizes.width / 2 - Sizes.height / 6,
                    Sizes.height / 2 - Sizes.height / 6,
                    Sizes.height / 3,
                    Sizes.height / 3,
                    "Play",
                    Color.WHITE
            );
            buttons[1] = new Button(
                    Textures.tutorialButton,
                    Sizes.width / 2 + Sizes.height / 6,
                    Sizes.height / 2 - Sizes.height / 6,
                    Sizes.height / 6,
                    Sizes.height / 6,
                    "Tutorial",
                    Color.WHITE
            );
            buttons[2] = new Button(
                    Textures.optionsButton,
                    Sizes.width / 2 - Sizes.height / 3,
                    Sizes.height / 2 - Sizes.height / 6,
                    Sizes.height / 6,
                    Sizes.height / 6,
                    "Options",
                    Color.WHITE
            );
        }
    }
}
