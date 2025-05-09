package com.Ismael.m8.uf3.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ball extends Actor {

    private TextureRegion[] frames;  // Frames de la animación de la pelota
    private int currentFrame = 0;  // Frame actual
    private float frameDuration = 0.03f;  // Duración de cada frame de la animación
    private float stateTime = 0f;  // Tiempo transcurrido para la animación
    private Vector2 destination = new Vector2(0, 0);  // Destino de la pelota
    private float scale = 1f;  // Tamaño original de la pelota
    private boolean animating = false;  // Indica si la pelota está animándose
    private boolean moveStarted = false;  // Indica si el movimiento ha comenzado (para animar)

    public Ball(Texture texture1, Texture texture2) {
        // Inicializamos los frames de la animación
        this.frames = new TextureRegion[]{
            new TextureRegion(texture1),
            new TextureRegion(texture2)
        };

        // Establecer el tamaño de la pelota igual al tamaño de los frames
        setSize(frames[0].getRegionWidth(), frames[0].getRegionHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);  // Establecemos el origen al centro
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Solo actualizamos la animación de los frames si la pelota está moviéndose
        if (animating) {
            // Actualizar la animación de los frames
            stateTime += delta;
            if (stateTime >= frameDuration) {
                currentFrame = (currentFrame + 1) % frames.length;  // Cambiar al siguiente frame
                stateTime = 0f;  // Resetear el tiempo
            }

            // Movimiento hacia el destino
            Vector2 currentPosition = new Vector2(getX(), getY());
            Vector2 direction = destination.cpy().sub(currentPosition);
            float distance = direction.len();

            if (distance > 1f) {  // Si aún no hemos llegado al destino
                direction.nor();  // Normalizar la dirección
                setPosition(getX() + direction.x * 500f * delta, getY() + direction.y * 500f * delta);
            } else {
                // Si hemos llegado al destino, detener el movimiento y animación
                setPosition(destination.x, destination.y);
                animating = false;  // Detener animación
                moveStarted = false;  // El movimiento ha terminado
            }

            // Reducir escala para simular que se aleja
            scale -= 0.01f;  // Reducir la escala poco a poco
            if (scale < 0.25f) {  // El mínimo tamaño de la pelota
                scale = 0.25f;
            }

            setScale(scale);  // Aplicar la nueva escala
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Dibujar el frame actual de la animación en la posición de la pelota
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

    // Método para iniciar la animación (cuando se toca la pelota)
    public void startAnimation() {
        this.animating = true;  // Iniciar animación
        this.stateTime = 0f;  // Resetear el tiempo
        this.currentFrame = 0;  // Empezar desde el primer frame
        this.moveStarted = true;  // El movimiento ha comenzado
    }

    // Establecer el destino al que se moverá la pelota
    public void setDestination(Vector2 destination) {
        this.destination.set(destination);
        animating = true;  // Iniciar animación al establecer el destino
        this.moveStarted = true;  // El movimiento ha comenzado
    }

    // Comprobar si la pelota está animándose
    public boolean isAnimating() {
        return animating;
    }

    // Establecer si la pelota está en movimiento
    public boolean isMoving() {
        return moveStarted;
    }
}
