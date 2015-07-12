package com.epicness.toolbox;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Frontanilla F. on 10/07/2015.
 */
public class Textures {
    public static Texture alemania, australia, brasil, canada, china, francia, inglaterra, japon,
            usa, nigeria, tailandia, sudan, suecia, ucrania, madagascar, congo, italia, espana,
            sudafrica, egipto, argentina, venezuela, mexico, india, arabiasaudita, rusia, bolivia;
    public static Texture map, spbg, bgimg, frame, gameover;
    public static Texture playButton, optionsButton, tutorialButton;
    public static Texture earthquake, hurricane, toxic, tsunami, burn;

    public static void create() {
        alemania = new Texture("countries/alemania.png");
        australia = new Texture("countries/australia.png");
        brasil = new Texture("countries/brasil.png");
        canada = new Texture("countries/canada.png");
        china = new Texture("countries/china.png");
        francia = new Texture("countries/francia.png");
        inglaterra = new Texture("countries/inglaterra.png");
        japon = new Texture("countries/japon.png");
        usa = new Texture("countries/usa.png");
        nigeria = new Texture("countries/nigeria.png");
        tailandia = new Texture("countries/tailandia.png");
        sudan = new Texture("countries/sudan.png");
        suecia = new Texture("countries/suecia.png");
        ucrania = new Texture("countries/ucrania.png");
        madagascar = new Texture("countries/madagascar.png");
        congo = new Texture("countries/congo.png");
        italia = new Texture("countries/italia.png");
        espana = new Texture("countries/espana.png");
        sudafrica = new Texture("countries/sudafrica.png");
        egipto = new Texture("countries/egipto.png");
        argentina = new Texture("countries/argentina.png");
        venezuela = new Texture("countries/venezuela.png");
        mexico = new Texture("countries/mexico.png");
        india = new Texture("countries/india.png");
        arabiasaudita = new Texture("countries/arabiasaudita.png");
        rusia = new Texture("countries/rusia.png");
        bolivia = new Texture("countries/bolivia.png");

        earthquake = new Texture("disasters/earthquake.png");
        hurricane = new Texture("disasters/hurricane.png");
        toxic = new Texture("disasters/toxic.png");
        tsunami = new Texture("disasters/tsunami.png");
        burn = new Texture("disasters/burn.png");

        gameover = new Texture("gameover.png");
        map = new Texture("map.png");
        spbg = new Texture("spbg.png");
        bgimg = new Texture("bgimg.png");
        frame = new Texture("frame.png");

        playButton = new Texture("buttons/play.png");
        optionsButton = new Texture("buttons/options.png");
        tutorialButton = new Texture("buttons/tutorial.png");
    }
}
