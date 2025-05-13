package com.Ismael.m8.uf3.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Goalkeeper extends Actor {

    public enum State {
        PREPARING,
        READY,
        DIVING_LEFT,
        DIVING_RIGHT
    }

    private State state = State.PREPARING;

    private final Animation<TextureRegion> prepareAnimation;
    private final TextureRegion diveLeft;
    private final TextureRegion diveRight;

    private float stateTime = 0f;
    private TextureRegion currentFrame;

    public Goalkeeper(Animation<TextureRegion> prepareAnim, TextureRegion diveLeft, TextureRegion diveRight) {
        this.prepareAnimation = prepareAnim;
        this.diveLeft = diveLeft;
        this.diveRight = diveRight;

        // Usar el primer frame para establecer el tamaño inicial del actor
        this.currentFrame = prepareAnim.getKeyFrame(0); // <-- esto inicializa el frame visible
        setSize(currentFrame.getRegionWidth(), currentFrame.getRegionHeight()); // <-- tamaño base
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
                }
                break;

            case READY:
                // Mantener el último frame de preparación
                currentFrame = (TextureRegion) prepareAnimation.getKeyFrame(prepareAnimation.getAnimationDuration());

                break;

            case DIVING_LEFT:
                /*currentFrame = diveLeft;
                moveBy(-5, 0); // TODO: mejorar movimiento*/
                break;

            case DIVING_RIGHT:
               /* currentFrame = diveRight;
                moveBy(5, 0); // TODO: mejorar movimiento*/
                break;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (currentFrame != null) {
            batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
        }
    }

    // Métodos públicos para controlar el estado del portero
    public void diveLeft() {
        state = State.DIVING_LEFT;
    }

    public void diveRight() {
        state = State.DIVING_RIGHT;
    }

    public void reset() {
        state = State.PREPARING;
        stateTime = 0f;
    }

    public State getState() {
        return state;
    }
}
