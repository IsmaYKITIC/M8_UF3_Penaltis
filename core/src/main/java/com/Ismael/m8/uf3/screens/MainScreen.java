package com.Ismael.m8.uf3.screens;

import static com.Ismael.m8.uf3.helpers.AssetManager.goal;
import static com.Ismael.m8.uf3.helpers.AssetManager.music;
import static com.Ismael.m8.uf3.helpers.AssetManager.soundStadium;

import com.Ismael.m8.uf3.TheLastKickerGame;
import com.Ismael.m8.uf3.actors.Ball;
import com.Ismael.m8.uf3.actors.Banner;
import com.Ismael.m8.uf3.actors.Goal;
import com.Ismael.m8.uf3.helpers.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Texture;

public class MainScreen implements Screen, InputProcessor {

    private TheLastKickerGame game;
    private Stage stage;
    private SpriteBatch batch;
    private Goal goal;
    private Ball ball;
    private Banner banner;
    private  Music backgroundMusic;

    public MainScreen(TheLastKickerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        backgroundMusic = soundStadium;
        if (!backgroundMusic.isPlaying()) {
            backgroundMusic.play();
        }
        // Inicializar el Stage y SpriteBatch
        stage = new Stage();
        batch = new SpriteBatch();

        // Establecer el procesador de entradas para escuchar los toques
        Gdx.input.setInputProcessor(this);

        // Crear la pelota y añadirla al stage
        Texture ballTexture1 = new Texture("Sprites/Balls/ballpix.png");
        Texture ballTexture2 = new Texture("Sprites/Balls/ballpix2.png");
        ball = new Ball(ballTexture1, ballTexture2);

        // Crear y añadir el Banner
        banner = new Banner(AssetManager.banner, 100); // velocidad 100 px/s
        banner.setPosition(0, 460); // posición inferior, puedes ajustar la Y
        stage.addActor(banner);

        // Ajustar la posición de la pelota al centro de la pantalla
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        ball.setPosition(screenWidth / 2 - ball.getWidth() / 2, screenHeight / 2 - ball.getHeight() / 2);
        // Añadir la pelota al stage
        stage.addActor(ball);

        // Crear la portería usando la textura cargada en AssetManager
        goal = new Goal(AssetManager.goal);
        // Colocar la portería en una posición específica, por ejemplo en el centro
        goal.setPosition(0, 200);
        // Añadir la portería al stage
        stage.addActor(goal);

    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Comienza el batch para dibujar
        batch.begin();

        // Dibujar el fondo a tamaño de pantalla completa
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        batch.draw(AssetManager.backGroundGame, 0, 0, screenWidth, screenHeight);

        batch.end();

        // Actualiza el stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        music.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Convertir las coordenadas de la pantalla a las coordenadas del stage
        Vector2 touch = stage.screenToStageCoordinates(new Vector2(screenX, screenY));

        // Pasar el destino a la pelota
        ball.setDestination(touch);  // Aquí es donde pasas la nueva posición de destino

        return true;
    }

    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char character) { return false; }
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchCancelled(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }
    public boolean scrolled(int amount) { return false; }
}
