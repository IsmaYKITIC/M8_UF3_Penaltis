package com.Ismael.m8.uf3.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Banner extends Actor {
    private Texture texture;
    private float xOffset;
    private float speed;

    public Banner(Texture texture, float speed) {
        this.texture = texture;
        this.speed = speed;
        this.xOffset = 0;
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        xOffset -= speed * delta;
        if (xOffset <= -texture.getWidth()) {
            xOffset += texture.getWidth();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        int screenWidth = (int) getStage().getViewport().getWorldWidth();
        int repetitions = (screenWidth / texture.getWidth()) + 2;

        for (int i = 0; i < repetitions; i++) {
            batch.draw(texture, xOffset + i * texture.getWidth(), getY());
        }
    }
}
