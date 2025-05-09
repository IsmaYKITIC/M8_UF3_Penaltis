package com.Ismael.m8.uf3.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Goal  extends Actor {
    private Texture goalTexture;  // Para la imagen de la portería
    private float width, height;

    public Goal(Texture goalTexture) {
        this.goalTexture = goalTexture;
        this.width = goalTexture.getWidth();
        this.height = goalTexture.getHeight();
        setSize(width, height);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
            this.goalTexture,
            getX(), getY(),
            getOriginX(), getOriginY(),
            getWidth(), getHeight(),
            getScaleX(), getScaleY()
        );
    }


    // Métodos para obtener los límites de la portería
    public float getGoalX() {
        return getX();
    }

    public float getGoalY() {
        return getY();
    }

    public float getGoalWidth() {
        return getWidth();
    }

    public float getGoalHeight() {
        return getHeight();
    }
}

