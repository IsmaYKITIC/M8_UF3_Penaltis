package com.Ismael.m8.uf3;

import com.Ismael.m8.uf3.screens.MainScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class TheLastKickerGame extends Game {
    private SpriteBatch batch;
    private Texture image;

    @Override
    public void create () {
        batch = new SpriteBatch();
        setScreen(new MainScreen(this));
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public void dispose () {
        batch.dispose();
        getScreen().dispose();
    }


}
