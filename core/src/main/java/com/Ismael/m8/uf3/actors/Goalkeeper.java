package com.Ismael.m8.uf3.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    // Guardamos la posición original para volver tras un chute
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
                // No cambiar currentFrame, para mantener el último frame de la animación
                break;

            case DIVING:
                // Aquí ya asignaste currentFrame en decideDirection()
                // Puedes agregar lógica extra aquí si quieres
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

        if (isLeft && isUp) {
            currentFrame = leftUp;
            moveBy(-50, 30);
        } else if (isLeft && !isUp) {
            currentFrame = leftDown;
            moveBy(-50, -30);
        } else if (!isLeft && isUp) {
            currentFrame = rightUp;
            moveBy(50, 30);
        } else {
            currentFrame = rightDown;
            moveBy(50, -30);
        }

        // ESCALA corregida en estado DIVING
        setSize(currentFrame.getRegionWidth() * 0.5f, currentFrame.getRegionHeight() * 0.5f);
        state = State.DIVING;
    }

    public void resetposition() {
        // Volver a la posición original
        setPosition(initialX, initialY);
    }

    public State getState() {
        return state;
    }
}
