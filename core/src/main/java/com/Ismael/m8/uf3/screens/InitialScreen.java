package com.Ismael.m8.uf3.screens;

import static com.Ismael.m8.uf3.helpers.AssetManager.ball;

import com.Ismael.m8.uf3.TheLastKickerGame;
import com.Ismael.m8.uf3.helpers.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InitialScreen implements Screen {
    private final TheLastKickerGame game;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private boolean jocIniciat;

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
                    game.setScreen(new MainScreen(game, batch, camera));
                    jocIniciat = true;
                }
                return true;
            }
        });
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        camera.update();
        batch.setProjectionMatrix(camera.combined);


        batch.begin();
        batch.draw(AssetManager.backGroundMenu, 0, 0, 1024, 768);
        AssetManager.font.getData().setScale(3f); // Doble de tamaño
        AssetManager.font.draw(batch, "THE LAST KICKER", 100, 400);
        batch.draw(ball, 875, 300, 150, 150);
        batch.draw(ball, 0, 500, 150, 150);
        batch.draw(ball, 500, 500, 150, 150);
        batch.draw(ball, 0, 100, 150, 150);
        batch.draw(ball, 400, 0, 150, 150);
        batch.draw(ball, 900, 600, 150, 150);


        AssetManager.font2.draw(batch, "\nFes clic per començar!", 650, 150);
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

    }
}
