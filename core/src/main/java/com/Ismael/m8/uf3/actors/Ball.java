package com.Ismael.m8.uf3.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ball extends Actor {
    private TextureRegion[] frames;
    private int currentFrame = 0;
    private float frameDuration = 0.03f;
    private float stateTime = 0f;
    private Vector2 destination = new Vector2(0, 0);
    private float scale = 1f;
    private boolean animating = false;
    private boolean moveStarted = false;
    private float totalDistance;
    private float initialX, initialY;

    public Ball(Texture texture1, Texture texture2) {
        this.frames = new TextureRegion[]{
            new TextureRegion(texture1),
            new TextureRegion(texture2)
        };

        setSize(frames[0].getRegionWidth(), frames[0].getRegionHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
    }

    public void setInitialPosition(float x, float y) {
        this.initialX = x;
        this.initialY = y;
        setPosition(x, y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (animating) {
            stateTime += delta;
            if (stateTime >= frameDuration) {
                currentFrame = (currentFrame + 1) % frames.length;
                stateTime = 0f;
            }

            Vector2 currentPosition = new Vector2(getX(), getY());
            Vector2 direction = destination.cpy().sub(currentPosition);
            float distance = direction.len();
            float progress = MathUtils.clamp(1f - (distance / totalDistance), 0f, 1f);
            scale = 1f - progress * 0.5f;
            setScale(scale);

            if (distance > 1f) {
                direction.nor();
                setPosition(getX() + direction.x * 500f * delta, getY() + direction.y * 500f * delta);
            } else {
                setPosition(destination.x, destination.y);
                animating = false;
                moveStarted = false;
                setScale(1f);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame = frames[currentFrame];
        batch.draw(
            frame,
            getX(), getY(),
            getOriginX(), getOriginY(),
            getWidth(), getHeight(),
            getScaleX(), getScaleY(),
            getRotation()
        );
    }

    public void setDestination(Vector2 destination) {
        this.destination.set(destination);
        this.totalDistance = destination.cpy().sub(getX(), getY()).len();
        this.animating = true;
        this.moveStarted = true;
        this.stateTime = 0f;
        this.scale = 1f;
        setScale(scale);
    }

    public boolean isAnimating() {
        return animating;
    }

    public boolean isMoving() {
        return moveStarted;
    }
    public void stop() {
        animating = false;
        moveStarted = false;
        setScale(1f);
    }

}
