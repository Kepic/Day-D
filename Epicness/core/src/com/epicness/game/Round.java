package com.epicness.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Timer;
import com.epicness.buttons.CountryButton;
import com.epicness.main.Renderer;
import com.epicness.screens.EndScreen;
import com.epicness.screens.MapScreen;
import com.epicness.toolbox.Fonts;

import java.util.Random;

/**
 * Created by Frontanilla F. on 11/07/2015.
 */
public class Round {

    public static int round;
    public static int disasters;
    public static int score;

    public static void create() {
        round = 1;
    }

    public static void passRound() {
        round++;
        score += 100;
        MapScreen.disaster = false;
        if (round >= 10) {
            Renderer.onMapScreen = false;
            Renderer.onEndScreen = true;
            Timer.instance().clear();                                           // IMPORTANT
            EndScreen.create();
            Gdx.input.setInputProcessor(EndScreen.getInputProcessor());
        }
    }

    public static class CountDown {
        private static Random rand = new Random();
        private static int timeLeft;

        public static int getTimeLeft() {
            return timeLeft;
        }

        public static void init() {

            // SETS THE INITIAL TIME OF EACH ROUND
            if (round < 4) {
                timeLeft = 35;
                disasters = 1;
            } else if (round < 7) {
                timeLeft = 25;
                disasters = 2;
            } else if (round < 10) {
                timeLeft = 15;
                disasters = 3;
            }
            start();
        }

        private static void start() {

            // RESETS THE ATTRS
            Fonts.glow.setColor(Color.BLUE);
            MapScreen.countrySelected = false;

            // TIMER THAT REDUCES THE TIME EACH SEC
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    timeLeft--;
                    if (timeLeft <= 3) {
                        Fonts.glow.setColor(Color.RED);
                    }
                }
            }
                    , 1             // delay
                    , 1             // frequency
                    , timeLeft - 1  // repetitions
            );

            // TIMER ACTIVATES WHEN TIME'S UP
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if (!MapScreen.countrySelected) {
                        // Time up!
                        int r = rand.nextInt(MapScreen.availableCountries.size());
                        MapScreen.selectedCountry = MapScreen.availableCountries.get(r);
                        System.out.println("RANDOM = " + MapScreen.selectedCountry);
                        countrySelected();
                    }
                }
            }
                    , timeLeft  // delay
            );
        }

        public static void countrySelected() {
            for (int i = 0; i < MapScreen.ButtonManager.getButtons().length; i++) {
                if (((CountryButton) MapScreen.ButtonManager.getButtons()[i]).getCountry().equals(MapScreen.selectedCountry)) {
                    ((CountryButton) MapScreen.ButtonManager.getButtons()[i]).setEnabled(false);
                    break;
                }
            }
            MapScreen.availableCountries.remove(MapScreen.selectedCountry);
            MapScreen.canTouch = false;
            Fonts.glow.setColor(Color.RED);
            if (timeLeft > 3) {
                timeLeft = 3;
            }
            MapScreen.countrySelected = true;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    MapScreen.disaster();
                }
            }
                    , timeLeft      // delay
            );
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    // AFTER DISASTER
                    passRound();
                    MapScreen.canTouch = true;
                    Timer.instance().clear();
                    init();
                }
            }
                    , timeLeft + 5      // delay
            );
        }
    }
}
