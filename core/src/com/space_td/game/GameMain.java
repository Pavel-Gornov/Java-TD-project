package com.space_td.game;

import com.badlogic.gdx.Application;
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
import java.util.Vector;


public class GameMain extends Game {
    public static String debugData;
    public static ArrayList<String> messages = new ArrayList<>();
    public Screen scene_main;
    public Screen scene_menu;

    //    Vector2 pos;
    @Override
    public void create() {
//        if (Gdx.app.getType()== Application.ApplicationType.Desktop){
//
//        }
        data.init();
//        scene_main=new Scene_main();
        scene_menu = new scene_menu();
//        System.out.println("Main scene created");
        setScreen(scene_menu);
//        System.out.println(pos.x*10);
    }


    @Override
    public void render() {
        if (messages.size() > 0) {
            for (int i = 0; i < messages.size(); i++) {
                if (messages.get(i).equals("open scene_main")) {
                    scene_main = new Scene_main();
                    setScreen(scene_main);
                    messages.remove(i);
                    break;
                }
            }
        }
        getScreen().render(Gdx.graphics.getDeltaTime());
        debugData = String.valueOf(Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void resize(int width, int height) {
        getScreen().resize(width, height);
    }

    @Override
    public void dispose() {
        getScreen().dispose();
    }


//    public static Vector2 randScreenPoint(Camera camera){
//
//    }
}
