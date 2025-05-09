package com.Ismael.m8.uf3.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Goalkeeaper extends Actor {
    private Animation<TextureRegion> animation;
    private float stateTime = 0f;

    public Goalkeeaper(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame = animation.getKeyFrame(stateTime, true);
        batch.draw(frame, getX(), getY());
    }
}

