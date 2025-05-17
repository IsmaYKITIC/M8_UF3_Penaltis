package com.Ismael.m8.uf3.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Goal extends Actor {
    private Rectangle goalArea;
    private Texture texture;

    public Goal(Texture texture) {
        this.texture = texture;
        setSize(texture.getWidth(), texture.getHeight());

        goalArea = new Rectangle();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getGoalArea() {
        // Supongamos que el área de gol es una franja central en la portería
        float areaWidth = getWidth() * 0.7f;
        float areaHeight = getHeight() * 0.45f;

        float areaX = getX() + (getWidth() - areaWidth) / 2f;
        float areaY =  getY()+ (getHeight() - areaHeight) /2f -30;

        goalArea.set(areaX, areaY, areaWidth, areaHeight);
        return goalArea;
    }

}
