package com.Ismael.m8.uf3.screens;


import static com.Ismael.m8.uf3.helpers.AssetManager.soundStadium;


import com.Ismael.m8.uf3.TheLastKickerGame;
import com.Ismael.m8.uf3.actors.Ball;
import com.Ismael.m8.uf3.actors.Banner;
import com.Ismael.m8.uf3.actors.Goal;
import com.Ismael.m8.uf3.actors.Goalkeeper;
import com.Ismael.m8.uf3.helpers.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainScreen implements Screen, InputProcessor {

    private TheLastKickerGame game;
    private Stage stage;
    private SpriteBatch batch;
    private Goal goal;
    private Ball ball;
    private Goalkeeper goalkeeper;
    private Banner banner;


    private Sound whistleSound;
    private Sound kickSound ;
    private Music goalSound;
    private Music backgroundMusic;
    private ShapeRenderer shapeRenderer;


    private float screenWidth = Gdx.graphics.getWidth();
    private float screenHeight = Gdx.graphics.getHeight();

    private boolean goalScored = false;
    private float goalTimer = 0;
    private final float GOAL_DISPLAY_TIME = 2f;
    private boolean whistlePlayed = false;


    public MainScreen(TheLastKickerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        backgroundMusic = soundStadium;
        if (!backgroundMusic.isPlaying()) {
            backgroundMusic.play();
        }

        stage = new Stage();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);

        whistleSound = AssetManager.whistle;
        kickSound = AssetManager.kick;
        goalSound = AssetManager.goalSound;

        banner = new Banner(AssetManager.banner, 100);
        banner.setPosition(0, 460);
        stage.addActor(banner);

        Texture goalTexture = new Texture("Sprites/porteria.png");
        goal = new Goal(goalTexture);
        goal.setSize(goal.getWidth() * 2.5f, goal.getHeight() * 2.5f);
        goal.setPosition(screenWidth / 2 - goal.getWidth() / 2, 175);
        stage.addActor(goal);

        Texture ballTexture1 = new Texture("Sprites/Balls/ballpix.png");
        Texture ballTexture2 = new Texture("Sprites/Balls/ballpix2.png");
        ball = new Ball(ballTexture1, ballTexture2);
        ball.setSize(ball.getWidth() * 0.5f, ball.getHeight() * 0.5f);
        ball.setInitialPosition(screenWidth / 2 - ball.getWidth() / 2, 0);
        stage.addActor(ball);

        goalkeeper = new Goalkeeper(
            AssetManager.goalkeeperPrepareAnimation,
            AssetManager.gkLeftUp,
            AssetManager.gkLeftDown,
            AssetManager.gkRightUp,
            AssetManager.gkRightDown
        );

        goalkeeper.setSize(goalkeeper.getWidth() * 0.5f, goalkeeper.getHeight() * 0.5f);
        goalkeeper.setPosition( screenWidth/2 - goalkeeper.getWidth() / 2, 80);
        stage.addActor(goalkeeper);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(AssetManager.backGroundGame, 0, 0, screenWidth, screenHeight);
        batch.end();

        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();

        if (!ball.isAnimating() && !ball.isMoving()) {
            // Pelota quieta, portero se prepara
            if (goalkeeper.getState() != Goalkeeper.State.PREPARING) {
                goalkeeper.prepare();
            }
        } else {
            // Pelota en movimiento o animándose, portero decide dirección (defiende)
            if (goalkeeper.getState() == Goalkeeper.State.PREPARING) {
                goalkeeper.decideDirection();
            }
        }


        if (!goalScored && !ball.isAnimating()) {
            Rectangle goalArea = goal.getGoalArea();
            Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
            if (goalArea.overlaps(ballRect)) {
                goalScored = true;
                goalTimer = GOAL_DISPLAY_TIME;
            }
        } else if (goalScored) {
            goalTimer -= delta;
            if (goalTimer <= 0) {
                goalScored = false;
            }
        }

        if (!ball.isAnimating() && !ball.isMoving()) {
            ball.setInitialPosition(screenWidth / 2 - ball.getWidth() / 2, 80);
            if (!whistlePlayed) {
                playWhistle();
                whistlePlayed = true;
            }
        } else {

            whistlePlayed = false;
        }


        if (goalScored) {
            // Crear GlyphLayout para medir el texto
            AssetManager.font.getData().setScale(7f);
            GlyphLayout layout = new GlyphLayout(AssetManager.font, "GOOOAL!!");
            goalSound.play();

            float textWidth = layout.width;
            float textHeight = layout.height;

            // Calcular coordenadas centradas
            float textX = (screenWidth - textWidth) / 2f;
            float textY = (screenHeight + textHeight) / 2f;

            float paddingX = 40f;
            float paddingY = 30f;

            Gdx.gl.glEnable(GL20.GL_BLEND);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, 0.6f); // Negro con transparencia
            shapeRenderer.rect(
                textX - paddingX,
                textY - textHeight - paddingY / 2f,
                textWidth + paddingX * 2,
                textHeight + paddingY
            );
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

            // Dibujar el texto encima del fondo
            batch.begin();
            AssetManager.font.draw(batch, layout, textX, textY);
            batch.end();
            goalkeeper.resetposition();
        }

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(1, 0, 0, 1);
//        Rectangle rect = goal.getGoalArea();
//        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
//        shapeRenderer.end();
    }

    private void playWhistle() {
        if (whistleSound != null) {
            whistleSound.play();
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        backgroundMusic.dispose();
        shapeRenderer.dispose();
        whistleSound.dispose();
        kickSound.dispose();
        goalSound.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!ball.isAnimating()) {
            Vector2 touch = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            ball.setDestination(touch);
            kickSound.play();
            goalkeeper.decideDirection();
            return true;
        }
        return false;
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
