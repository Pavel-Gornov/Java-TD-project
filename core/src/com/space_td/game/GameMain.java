package com.space_td.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class GameMain extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture planet;
    private Texture TEMP;
    public float ScrWidth;
    public float ScrHeight;
    public float planet_x;
    public float planet_y;
    OrthographicCamera camera = new OrthographicCamera();


    @Override
    public void create() {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        planet = new Texture("planet.png");
        TEMP = new Texture("planet.png");
        batch = new SpriteBatch();
        ScrHeight=Gdx.graphics.getHeight();
        ScrWidth=Gdx.graphics.getWidth();
        batch.setProjectionMatrix(camera.combined);
        planet_x = Gdx.graphics.getWidth()/2f;
        planet_y = Gdx.graphics.getHeight()/2f;
        camera.update();
    }


    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(planet, planet_x, planet_y);
        batch.end();
        System.out.println("W: "+Gdx.graphics.getWidth()+" H: "+Gdx.graphics.getHeight());
        camera.update();
    }
//    @Override
//    public void resize(int width, int height){
//        ScrHeight=Gdx.graphics.getHeight();
//        ScrWidth=Gdx.graphics.getWidth();
//    }

    @Override
    public void dispose() {
        batch.dispose();
        planet.dispose();
    }
}
