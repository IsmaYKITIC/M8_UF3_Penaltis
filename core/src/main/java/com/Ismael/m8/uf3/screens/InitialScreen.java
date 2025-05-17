package com.Ismael.m8.uf3.screens;

import static com.Ismael.m8.uf3.helpers.AssetManager.ball;
import static com.Ismael.m8.uf3.helpers.AssetManager.music;

import com.Ismael.m8.uf3.TheLastKickerGame;
import com.Ismael.m8.uf3.helpers.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class InitialScreen implements Screen {
    private final TheLastKickerGame game;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private boolean jocIniciat;
    public static Music backgroundMusic;

//Propiedades para la animación del texto
    private float blinkAlpha = 1f;
    private float blinkSpeed = 2f;
    private boolean decreasing = true;

    public InitialScreen(TheLastKickerGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 768);
        this.jocIniciat = false;


        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                if (!jocIniciat) {
                    game.setScreen(new MainScreen(game));
                    jocIniciat = true;
                    backgroundMusic.stop();
                }
                return true;
            }
        });
    }
    @Override
    public void show() {
        backgroundMusic = music;
        if (!backgroundMusic.isPlaying()) {
            backgroundMusic.play();
        }

        //return false;
    }

    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Fondo
        batch.begin();
        batch.draw(AssetManager.backGroundMenu, 0, 0, 1024, 768);
        batch.end();

        // Rectángulo negro semitransparente
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 0.4f);
        shapeRenderer.rect(90, 340, 790, 75);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        // Aquí actualizas el alpha ANTES de batch.begin()
        if (decreasing) {
            blinkAlpha -= blinkSpeed * delta;
            if (blinkAlpha <= 0.2f) {
                blinkAlpha = 0.2f;
                decreasing = false;
            }
        } else {
            blinkAlpha += blinkSpeed * delta;
            if (blinkAlpha >= 1f) {
                blinkAlpha = 1f;
                decreasing = true;
            }
        }

        batch.begin();
        AssetManager.font.getData().setScale(3f);
        AssetManager.font.draw(batch, "THE LAST KICKER", 100, 400);

        batch.draw(ball, 875, 300, 150, 150);
        batch.draw(ball, 0, 500, 150, 150);
        batch.draw(ball, 500, 500, 150, 150);
        batch.draw(ball, 0, 100, 150, 150);
        batch.draw(ball, 400, 0, 150, 150);
        batch.draw(ball, 900, 600, 150, 150);

        //Aplicar el parpadeo al texto
        AssetManager.font2.setColor(1, 1, 1, blinkAlpha);
        AssetManager.font2.draw(batch, "\nFes clic per començar!", 650, 150);
        AssetManager.font2.setColor(1, 1, 1, 1f);

        batch.end();
    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    music.dispose();
    }
}
