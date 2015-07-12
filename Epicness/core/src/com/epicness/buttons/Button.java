package com.epicness.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Frontanilla F. on 23/06/2015.
 */
public class Button {

    private Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;
    private String name;
    private Color color;

    public Button(Texture texture, float x, float y, float width, float height, String name, Color color) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.color = color;
    }

    public boolean isWithin(int x, int y) {
        return x >= this.x && x <= this.x + width
                && y >= this.y && y <= this.y + height;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

}
