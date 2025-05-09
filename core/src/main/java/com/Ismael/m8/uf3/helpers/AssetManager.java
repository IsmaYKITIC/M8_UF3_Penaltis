package com.Ismael.m8.uf3.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
    public static Texture goalKeeper;;
    public static Texture goal;
    public static Texture banner;
    // Sons
    public static Music music;
    public static Sound soGol;
    public static Sound soFail;
    public static Music soundStadium;

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
       // goalKeeper = new Texture(Gdx.files.internal("Sprites/keeper.png"));
        banner = new Texture(Gdx.files.internal("Sprites/publicidad.png"));

        // Sons
        music = Gdx.audio.newMusic(Gdx.files.internal("Sounds/musicainit.mp3"));
        music.setLooping(true);
        music.setVolume(0.9f);

        /*soGol = Gdx.audio.newSound(Gdx.files.internal("sons/gota.wav"));
        soFail = Gdx.audio.newSound(Gdx.files.internal("sons/gota.wav"));*/
        soundStadium= Gdx.audio.newMusic(Gdx.files.internal("Sounds/stadium.mp3"));
        music.setLooping(true);
        music.setVolume(0.8f);
    }

}
