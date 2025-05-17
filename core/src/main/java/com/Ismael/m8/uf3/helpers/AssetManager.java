package com.Ismael.m8.uf3.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AssetManager {

    //BackGroundMenu
    public static TextureRegion textureRegionMenu;
    public static Texture backGroundMenu;
    //BackGroundGame
    public static TextureRegion textureRegionGame;
    public static Texture backGroundGame;

    // Fonts
    public static BitmapFont font;
    public static BitmapFont font2;

    // Actors
    public static Texture ball;
    public static Texture goalKeeper;
    public static Texture goal;
    public static Texture banner;

    //Animations
    public static Animation<TextureRegion> goalkeeperPrepareAnimation;
    public static TextureRegion goalkeeperDiveLeft;
    public static TextureRegion goalkeeperDiveRight;

    // Sons
    public static Music music;
    public static Sound soGol;
    public static Sound soFail;
    public static Music soundStadium;
    public static Sound whistle;
    public static TextureRegion gkLeftUp, gkLeftDown, gkRightUp, gkRightDown;
    public static Sound kick;
    public static Music goalSound;


    public static void load() {
        // Background
        backGroundMenu = new Texture(Gdx.files.internal("Backgrounds/main.jpg"));
        textureRegionMenu = new TextureRegion(backGroundMenu);

        backGroundGame = new Texture(Gdx.files.internal("Backgrounds/game.jpg"));
        textureRegionGame = new TextureRegion(backGroundGame);

        // Fonts
        font = new BitmapFont(Gdx.files.internal("Fonts/fontjoc.fnt"));
        font2 = new BitmapFont(Gdx.files.internal("Fonts/subtitle.fnt"));

        // Imatges
        ball = new Texture(Gdx.files.internal("Sprites/Balls/ballpix.png"));
        goal =  new Texture(Gdx.files.internal("Sprites/porteria.png"));
        //Porteros
       // goalKeeper = new Texture(Gdx.files.internal("Sprites/keeper.png"));
        gkLeftUp = new TextureRegion(new Texture("Sprites/GK/5lu.png"));
        gkLeftDown = new TextureRegion(new Texture("Sprites/GK/5ld.png"));
        gkRightUp = new TextureRegion(new Texture("Sprites/GK/5ru.png"));
        gkRightDown = new TextureRegion(new Texture("Sprites/GK/5rd.png"));


        banner = new Texture(Gdx.files.internal("Sprites/publicidad.png"));


        //animacions
        //Porteros
        // goalKeeper = new Texture(Gdx.files.internal("Sprites/keeper.png"));
        gkLeftUp = new TextureRegion(new Texture("Sprites/GK/5lu.png"));
        gkLeftDown = new TextureRegion(new Texture("Sprites/GK/5ld.png"));
        gkRightUp = new TextureRegion(new Texture("Sprites/GK/5ru.png"));
        gkRightDown = new TextureRegion(new Texture("Sprites/GK/5rd.png"));

        // Cargar texturas individuales
        TextureRegion frame1 = new TextureRegion(new Texture("Sprites/GK/1.png"));
        TextureRegion frame2 = new TextureRegion(new Texture("Sprites/GK/2.png"));
        TextureRegion frame3 = new TextureRegion(new Texture("Sprites/GK/3.png"));
        TextureRegion frame4 = new TextureRegion(new Texture("Sprites/GK/4.png"));

        // Crear la animación
        Array<TextureRegion> frames = new Array<>();
        frames.add(frame1);
        frames.add(frame2);
        frames.add(frame3);
        frames.add(frame4);

        goalkeeperPrepareAnimation = new Animation<>(0.3f, frames, Animation.PlayMode.NORMAL);

        // Sons
        music = Gdx.audio.newMusic(Gdx.files.internal("Sounds/musicainit.mp3"));
        music.setLooping(true);
        music.setVolume(0.9f);
        whistle = Gdx.audio.newSound(Gdx.files.internal("Sounds/silvato.mp3"));
        kick = Gdx.audio.newSound(Gdx.files.internal("Sounds/chute.mp3"));
        goalSound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/gol.mp3"));
        goalSound.setVolume(2f);



        /*soGol = Gdx.audio.newSound(Gdx.files.internal("sons/gota.wav"));
        soFail = Gdx.audio.newSound(Gdx.files.internal("sons/gota.wav"));*/
        soundStadium= Gdx.audio.newMusic(Gdx.files.internal("Sounds/stadium.mp3"));
        music.setLooping(true);
        music.setVolume(0.8f);


    }

}
