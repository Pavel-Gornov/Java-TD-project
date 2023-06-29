package com.space_td.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.security.PublicKey;
import java.util.ArrayList;


public class GameMain extends Game {
    public static String debugData;
    public Screen scene_main;

    @Override
    public void create() {
        scene_main=new Scene_main();
        System.out.println("Main scene created");
        setScreen(scene_main);
    }




    @Override
    public void render() {
        getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }


//    public static Vector2 randScreenPoint(Camera camera){
//
//    }
}
