package com.space_td.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class GameMain extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture planet;
    //Screen = Scr
    public float ScrWidth;
    public float ScrHeight;


    @Override
    public void create() {
        planet = new Texture("planet.png");
        batch = new SpriteBatch();
    }
    @Override
    public void resize(int width, int height){
        ScrWidth=width;
        ScrHeight=height;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(planet, ScrWidth / 2, ScrHeight / 2);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        planet.dispose();
    }
}
