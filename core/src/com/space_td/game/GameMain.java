package com.space_td.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;


public class GameMain extends ApplicationAdapter {
    public static String debugData;
    private SpriteBatch batch;
    private Texture planet;
    public float ScrWidth;
    public float ScrHeight;
    public float planet_x;
    public float planet_y;
    public OrthographicCamera camera;
    DummyGameObject dg;
    ArrayList<Star> stars = new ArrayList<>();
    ArrayList<TextureRegion> starTextures = new ArrayList<>();
    int starsLimit;
    long windowSpace;
    float starCountModifier = 1f;
    float screenBordersMedian;

    @Override
    public void create() {
        ScrHeight = Gdx.graphics.getHeight();
        ScrWidth = Gdx.graphics.getWidth();
        camera = new OrthographicCamera(ScrWidth, ScrHeight);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        planet = new Texture("planet.png");
        starTextures = Utils.splitRegion(new Texture("stars.png"), 8, 8);
        recalcStarCount();
        fixStarsArraySize();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        planet_x = ScrWidth / 2f;
        planet_y = ScrHeight / 2f;
        dg = new DummyGameObject(new Vector2(ScrWidth / 2, ScrHeight / 2), 0, new Vector2(1, 1), new TextureRegion(new Texture("planet.png")), false, false);
        camera.update();
    }


    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (int i = 0; i < stars.size(); i++) {
            if (stars.get(i).isDestroyed) stars.set(i, Star.makeStar(starTextures));
            stars.get(i).draw(batch);
            debugData = "Stars count: " + stars.size() + "\n";
        }
        dg.draw(batch);
        dg.position.x += Gdx.graphics.getDeltaTime();

        if (batch.isDrawing()) batch.end();

        camera.update();
        debugData += "\nFPS: " + (int) (1 / Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
        recalcStarCount();
        fixStarsArraySize();
    }

    @Override
    public void dispose() {
        batch.dispose();
        planet.dispose();
        dg.dispose();
        for (int i = 0; i < stars.size(); i++) {
            stars.get(i).dispose();
        }
    }

    public void recalcStarCount() {
        windowSpace = (long) Gdx.graphics.getHeight() * Gdx.graphics.getWidth();
        screenBordersMedian = (Gdx.graphics.getHeight() + Gdx.graphics.getWidth()) >> 1;
        starsLimit = (int) ((windowSpace / screenBordersMedian) * starCountModifier);
    }

    public void fixStarsArraySize() {
        if (stars.size() < starsLimit) {
            for (int i = 0; i < starsLimit - stars.size(); i++) {
                stars.add(Star.makeStar(starTextures));
            }
        }
        if (stars.size() > starsLimit) {
            for (int i = starsLimit - 1; i < stars.size() - 1; i++) {
                stars.get(i).lifetime = 0;
            }
        }
    }
}
