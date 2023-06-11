package com.space_td.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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
    public ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    @Override
    public void create() {
//        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        planet = new Texture("planet.png");
//        TEMP = new Texture("planet.png");
//        batch = new SpriteBatch();
//        ScrHeight = Gdx.graphics.getHeight();
//        ScrWidth = Gdx.graphics.getWidth();
//        batch.setProjectionMatrix(camera.combined);
//        planet_x = Gdx.graphics.getWidth() / 2f;
//        planet_y = Gdx.graphics.getHeight() / 2f;
//        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
//        gameObjects.add(new GameObject(camera.viewportWidth / 2f, camera.viewportHeight / 2f, new Texture("planet.png"), 1f, 1f, 0f, batch));
//
//        camera.update();
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
        gameObjects.add(new GameObject(camera.viewportWidth / 2f, camera.viewportHeight / 2f, new Texture("planet.png"), 1f, 1f, 0f, batch));

        gameObjects.add(new GameObject(camera.viewportWidth / 2f, camera.viewportHeight / 2f, new Texture("planet.png"), 1f, 1f, 0f, batch));
        camera.update();
    }


    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//        batch.draw(planet, camera.viewportWidth / 2f, camera.viewportHeight / 2f);
//        batch.end();
        if (gameObjects.get(0).sceneSpriteBatch != null) {
//            for (GameObject gameObject : gameObjects
//            ) {
//                gameObject.draw();
//                debugData = "draw!";
//            }
            for (int i = 0; i < gameObjects.size(); i++) {
                gameObjects.get(i).draw();
            }
        } else {
            for (int i = 0; i < gameObjects.size(); i++) {
                if (gameObjects.get(i).sceneSpriteBatch == null) {
                    GameObject go = gameObjects.get(i);
                    go.sceneSpriteBatch = batch;
                    gameObjects.set(i, go);
                }
            }
        }
//        System.out.println("W: " + Gdx.graphics.getWidth() + " H: " + Gdx.graphics.getHeight());
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
        for (GameObject gameObject : gameObjects
        ) {
            gameObject.dispose();
        }
    }
}
