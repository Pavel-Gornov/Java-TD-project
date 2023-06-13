package com.space_td.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;


public class GameMain extends ApplicationAdapter {
    public static String debugData;
    private SpriteBatch batch;
    private Texture planet;
    private Texture TEMP;
    public float ScrWidth;
    public float ScrHeight;
    public float planet_x;
    public float planet_y;
    public OrthographicCamera camera;
    DummyGameObject dg;

    @Override
    public void create() {
        ScrHeight = Gdx.graphics.getHeight();
        ScrWidth = Gdx.graphics.getWidth();
        camera = new OrthographicCamera(ScrWidth, ScrHeight);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        planet = new Texture("planet.png");
        TEMP = new Texture("planet.png");
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        planet_x = ScrWidth / 2f;
        planet_y = ScrHeight / 2f;
        dg = new DummyGameObject(new Vector2(ScrWidth / 2, ScrHeight / 2), 0, new Vector2(1, 1), new Texture("planet.png"), false, false);
        camera.update();
    }


    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        dg.draw(batch);
        dg.position.x+=Gdx.graphics.getDeltaTime();

        if (batch.isDrawing()) batch.end();

        camera.update();
    }
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        planet.dispose();
        dg.dispose();
    }
}
