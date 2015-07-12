package com.epicness.screens;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicness.buttons.Button;
import com.epicness.toolbox.Sizes;
import com.epicness.toolbox.Textures;

/**
 * Created by Frontanilla F. on 12/07/2015.
 */
public class EndScreen {

    private static SpriteBatch endBatch;

    public static void create() {
        endBatch = new SpriteBatch();
        ButtonManager.create();
    }

    public static void render() {

    }

    public static InputProcessor getInputProcessor() {
        return InputManager.getInstance();
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
                    if (b.getName().equals("Next")) {
                        System.out.println("Next");
                    }
                }
            }
            return true;
        }
    }

    private static class ButtonManager {
        private static Button[] buttons;

        private static void create() {
            buttons = new Button[1];
            buttons[0] = new Button(
                    Textures.playButton,
                    Sizes.width / 2 - Sizes.height / 6,
                    Sizes.height / 2 - Sizes.height / 6,
                    Sizes.height / 3,
                    Sizes.height / 3,
                    "Next",
                    Color.WHITE
            );
        }
    }
}
