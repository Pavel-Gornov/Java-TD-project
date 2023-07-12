package com.space_td.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;


public class GameMain extends Game {


    public Screen MainMenuScreen;

    @Override
    public void create() {
        MainMenuScreen = new MainMenuScreen();
        setScreen(MainMenuScreen);
    }

    @Override
    public void render() {
        getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        getScreen().resize(width, height);
    }

    @Override
    public void dispose() {
        getScreen().dispose();
    }
}
