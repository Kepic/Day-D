package com.epicness.screens;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

import com.epicness.buttons.Button;
import com.epicness.buttons.CountryButton;
import com.epicness.game.Country;
import com.epicness.game.Round;
import com.epicness.toolbox.Fonts;
import com.epicness.toolbox.Sizes;
import com.epicness.toolbox.Textures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Frontanilla F. on 10/07/2015.
 */
public class MapScreen {

    private static float alpha;
    private static SpriteBatch mapBatch;
    private static Country[] countries;
    public static ArrayList<Country> availableCountries = new ArrayList<Country>();
    public static Country selectedCountry;
    public static boolean countrySelected;
    public static boolean canTouch = true;
    public static boolean disaster;
    private static Random rand;

    public static void create() {
        alpha = 0;
        mapBatch = new SpriteBatch();
        rand = new Random();

        Round.create();

        countries = new Country[27];
        countries[0] = new Country("canada");
        countries[1] = new Country("usa");
        countries[2] = new Country("brasil");
        countries[3] = new Country("venezuela");
        countries[4] = new Country("mexico");
        countries[5] = new Country("bolivia");
        countries[6] = new Country("argentina");
        countries[7] = new Country("inglaterra");
        countries[8] = new Country("espana");
        countries[9] = new Country("egipto");
        countries[10] = new Country("madagascar");
        countries[11] = new Country("congo");
        countries[12] = new Country("india");
        countries[13] = new Country("china");
        countries[14] = new Country("rusia");
        countries[15] = new Country("ucrania");
        countries[16] = new Country("australia");
        countries[17] = new Country("japon");
        countries[18] = new Country("italia");
        countries[19] = new Country("alemania");
        countries[20] = new Country("tailandia");
        countries[21] = new Country("sudafrica");
        countries[22] = new Country("francia");
        countries[23] = new Country("suecia");
        countries[24] = new Country("arabiasaudita");
        countries[25] = new Country("nigeria");
        countries[26] = new Country("sudan");

        ButtonManager.create();
        Collections.addAll(availableCountries, countries);

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

        Fonts.neon.getData().setScale((Sizes.height / 8) / 512f, (Sizes.height / 8) / 512f);
        Fonts.glow.getData().setScale((Sizes.height / 8) / 512f, (Sizes.height / 8) / 512f);
        Fonts.glow.setColor(Color.BLUE);

        Round.CountDown.init();
    }

    public static void render() {

        mapBatch.begin();

        // MAP

        mapBatch.setColor(1, 1, 1, alpha);
        mapBatch.draw(
                Textures.map,
                0,
                0,
                Sizes.width,
                Sizes.height
        );

        // COUNT DOWN

        mapBatch.draw(
                Textures.frame,
                0,
                0,
                Sizes.height / 4,
                Sizes.height / 8
        );

        if (Round.CountDown.getTimeLeft() > 0) {
            Fonts.neon.draw(
                    mapBatch,
                    "" + Round.CountDown.getTimeLeft(),
                    0,
                    Sizes.height / 8
            );
            Fonts.glow.draw(
                    mapBatch,
                    "" + Round.CountDown.getTimeLeft(),
                    0,
                    Sizes.height / 8
            );
        }

        // BUTTONS

        for (Button b : ButtonManager.buttons) {
            if (b instanceof CountryButton) {
                if (((CountryButton) b).getEnabled()) {
                    mapBatch.setColor(1, 1, 1, alpha);
                    mapBatch.setColor(b.getColor().r, b.getColor().g, b.getColor().b, alpha);
                    mapBatch.draw(
                            b.getTexture(),
                            b.getX(),
                            b.getY(),
                            b.getWidth(),
                            b.getHeight()
                    );
                }
            } else {
                mapBatch.setColor(1, 1, 1, alpha);
                mapBatch.setColor(b.getColor().r, b.getColor().g, b.getColor().b, alpha);
                mapBatch.draw(
                        b.getTexture(),
                        b.getX(),
                        b.getY(),
                        b.getWidth(),
                        b.getHeight()
                );
            }
        }
        mapBatch.end();

    }

    public static void disaster() {
        disaster = true;
        for (int i = 0; i < Round.disasters; i++) {
            final int r = rand.nextInt(MapScreen.availableCountries.size());
            final String countryname = MapScreen.availableCountries.get(r).toString();
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    System.out.println("DISASTER AT " + countryname);
                    MapScreen.availableCountries.get(r).disaster();
                }
            }
                    , i + 1             // delay
            );
        }
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
            if (canTouch) {
                screenY = Sizes.height - screenY;
                for (Button b : ButtonManager.buttons) {
                    if (b.isWithin(screenX, screenY)) {
                        if (b.getName().equals("Button")) {
                            System.out.println("BUTTON");
                        } else if (b instanceof CountryButton) {
                            if (availableCountries.contains(((CountryButton) b).getCountry())) {
                                selectedCountry = ((CountryButton) b).getCountry();
                                Round.CountDown.countrySelected();
                            }
                        }
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }

    public static class ButtonManager {
        private static Button[] buttons;

        private static void create() {
            buttons = new Button[27];

            buttons[0] = new CountryButton(
                    Textures.canada,
                    Sizes.width * 0.13f,
                    Sizes.height * 0.83f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[0]
            );
            buttons[1] = new CountryButton(
                    Textures.usa,
                    Sizes.width * 0.11f,
                    Sizes.height * 0.7f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[1]
            );
            buttons[2] = new CountryButton(
                    Textures.brasil,
                    Sizes.width * 0.26f,
                    Sizes.height * 0.31f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[2]
            );
            buttons[3] = new CountryButton(
                    Textures.venezuela,
                    Sizes.width * 0.2f,
                    Sizes.height * 0.45f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[3]
            );
            buttons[4] = new CountryButton(
                    Textures.mexico,
                    Sizes.width * 0.1f,
                    Sizes.height * 0.53f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[4]
            );
            buttons[5] = new CountryButton(
                    Textures.bolivia,
                    Sizes.width * 0.2f,
                    Sizes.height * 0.29f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[5]
            );
            buttons[6] = new CountryButton(
                    Textures.argentina,
                    Sizes.width * 0.22f,
                    Sizes.height * 0.14f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[6]
            );
            buttons[7] = new CountryButton(
                    Textures.inglaterra,
                    Sizes.width * 0.41f,
                    Sizes.height * 0.82f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[7]
            );
            buttons[8] = new CountryButton(
                    Textures.espana,
                    Sizes.width * 0.44f,
                    Sizes.height * 0.7f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[8]
            );
            buttons[9] = new CountryButton(
                    Textures.egipto,
                    Sizes.width * 0.52f,
                    Sizes.height * 0.6f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[9]
            );
            buttons[10] = new CountryButton(
                    Textures.madagascar,
                    Sizes.width * 0.6f,
                    Sizes.height * 0.24f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[10]
            );
            buttons[11] = new CountryButton(
                    Textures.congo,
                    Sizes.width * 0.52f,
                    Sizes.height * 0.38f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[11]
            );
            buttons[12] = new CountryButton(
                    Textures.india,
                    Sizes.width * 0.71f,
                    Sizes.height * 0.58f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[12]
            );
            buttons[13] = new CountryButton(
                    Textures.china,
                    Sizes.width * 0.78f,
                    Sizes.height * 0.65f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[13]
            );
            buttons[14] = new CountryButton(
                    Textures.rusia,
                    Sizes.width * 0.73f,
                    Sizes.height * 0.85f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[14]
            );
            buttons[15] = new CountryButton(
                    Textures.ucrania,
                    Sizes.width * 0.56f,
                    Sizes.height * 0.82f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[15]
            );
            buttons[16] = new CountryButton(
                    Textures.australia,
                    Sizes.width * 0.9f,
                    Sizes.height * 0.2f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[16]
            );
            buttons[17] = new CountryButton(
                    Textures.japon,
                    Sizes.width * 0.91f,
                    Sizes.height * 0.72f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[17]
            );
            buttons[18] = new CountryButton(
                    Textures.italia,
                    Sizes.width * 0.5f,
                    Sizes.height * 0.69f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[18]
            );
            buttons[19] = new CountryButton(
                    Textures.alemania,
                    Sizes.width * 0.5f,
                    Sizes.height * 0.77f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[19]
            );
            buttons[20] = new CountryButton(
                    Textures.tailandia,
                    Sizes.width * 0.8f,
                    Sizes.height * 0.52f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[20]
            );
            buttons[21] = new CountryButton(
                    Textures.sudafrica,
                    Sizes.width * 0.53f,
                    Sizes.height * 0.23f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[21]
            );
            buttons[22] = new CountryButton(
                    Textures.francia,
                    Sizes.width * 0.46f,
                    Sizes.height * 0.76f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[22]
            );
            buttons[23] = new CountryButton(
                    Textures.suecia,
                    Sizes.width * 0.5f,
                    Sizes.height * 0.88f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[23]
            );
            buttons[24] = new CountryButton(
                    Textures.arabiasaudita,
                    Sizes.width * 0.6f,
                    Sizes.height * 0.61f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[24]
            );
            buttons[25] = new CountryButton(
                    Textures.nigeria,
                    Sizes.width * 0.45f,
                    Sizes.height * 0.52f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[25]
            );
            buttons[26] = new CountryButton(
                    Textures.sudan,
                    Sizes.width * 0.58f,
                    Sizes.height * 0.46f,
                    Sizes.height / 15,
                    Sizes.height / 15,
                    Color.WHITE,
                    countries[26]
            );
        }

        public static Button[] getButtons() {
            return buttons;
        }
    }
}