package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MainMenuScreen implements Screen {
    public Planet planet;
    public Planet cursor;
    public TextureRegion logo;
    public OrthographicCamera camera;
    public SpriteBatch batch;
    public Button play_button;
    public Stage stage;

    boolean hide_logo = false;
    float temp;

    @Override
    public void show() {
        planet = new Planet(new Texture("planet.png"), new Vector2(500, 500), 0, new Vector2(4.5f, 4.5f));
        logo = new TextureRegion(new Texture("logo.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 1000);
        batch = new SpriteBatch();
        stage = new Stage(new ExtendViewport(1000, 1000, 1000, 1000, camera), batch);
        Gdx.input.setInputProcessor(stage);
        Pixmap pixmap = new Pixmap(Gdx.files.internal("cursor.png"));
        int xHotspot = 15, yHotspot = 15;
        Cursor cursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);
        pixmap.dispose();
        Gdx.graphics.setCursor(cursor);
        com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle button_style = new com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle();
        Skin s = new Skin();
        s.add("buttonPlay", new Texture("button_up.png"));
        s.add("buttonPlay_down", new Texture("button_down.png"));
        button_style.up = s.getDrawable("buttonPlay");
        button_style.down = s.getDrawable("buttonPlay_down");
        play_button = new Button(button_style);
        play_button.setPosition(500 - 32, 500 - 32);
        play_button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide_logo = true;
                System.out.println("Clicked!");
                play_button.setVisible(false);
            }
        });
        stage.addActor(play_button);
        temp = 1000 - logo.getRegionHeight() * 0.9f;
        camera.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        planet.rotation += Gdx.graphics.getDeltaTime() * 4;
        batch.begin();

        if (hide_logo) {
            temp += 200 * Gdx.graphics.getDeltaTime();
        }
        planet.draw(batch);
        batch.draw(logo, 500 - logo.getRegionWidth() / 2f, temp,
                logo.getRegionWidth() / 2f, logo.getRegionHeight() / 2f, logo.getRegionWidth(), logo.getRegionHeight(),
                0.8f, 0.8f, 0);
        batch.end();
        stage.draw();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
