package com.Ismael.m8.uf3.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Goalkeeper extends Actor {
    public enum State {
        PREPARING,
        READY,
        DIVING
    }

    private State state = State.PREPARING;
    private final Animation<TextureRegion> prepareAnimation;
    private TextureRegion currentFrame;
    private float stateTime = 0f;

    private final TextureRegion leftUp, leftDown, rightUp, rightDown;


    private float initialX, initialY;

    public Goalkeeper(
        Animation<TextureRegion> prepareAnim,
        TextureRegion leftUp,
        TextureRegion leftDown,
        TextureRegion rightUp,
        TextureRegion rightDown
    ) {
        this.prepareAnimation = prepareAnim;
        this.leftUp = leftUp;
        this.leftDown = leftDown;
        this.rightUp = rightUp;
        this.rightDown = rightDown;

        this.currentFrame = prepareAnim.getKeyFrame(0);
        setSize(currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        this.initialX = x;
        this.initialY = y;
    }
    public void prepare() {
        setState(State.PREPARING);
    }

    private void setState(State state) {
        this.state = state;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;

        switch (state) {
            case PREPARING:
                currentFrame = prepareAnimation.getKeyFrame(stateTime, false);
                if (prepareAnimation.isAnimationFinished(stateTime)) {
                    state = State.READY;
                    // Guardamos explícitamente el último frame para que se mantenga fijo
                    currentFrame = prepareAnimation.getKeyFrame(prepareAnimation.getAnimationDuration(), false);
                }
                break;

            case READY:

                break;

            case DIVING:
                break;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (currentFrame != null) {
            batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
        }
    }

    public void decideDirection() {
        boolean isLeft = Math.random() < 0.5;
        boolean isUp = Math.random() < 0.5;

        float diveX = 500;
        float diveY = 150;

        if (isLeft && isUp) {
            currentFrame = leftUp;
            moveBy(-diveX, diveY*2);
        } else if (isLeft && !isUp) {
            currentFrame = leftDown;
            moveBy(-diveX, -diveY);
        } else if (!isLeft && isUp) {
            currentFrame = rightUp;
            moveBy(diveX, diveY*2);
        } else {
            currentFrame = rightDown;
            moveBy(diveX, -diveY);
        }

        setSize(currentFrame.getRegionWidth() * 0.5f, currentFrame.getRegionHeight() * 0.5f);
        state = State.DIVING;
    }

    public void resetposition() {
        // reiniciar posició
        setPosition(initialX, initialY);
    }



    public State getState() {
        return state;
    }
}
