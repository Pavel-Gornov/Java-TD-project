package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Vector;



public class data {
    //TODO: не удалять data
    //TODO: не удалять этот TODO и выше
    public static float gameDifficulty;
    public static float gameSpeed=1f;
    public static boolean partyMode=false;
    public static Vector2 mousePos=new Vector2(0, 0);
    public static String githubData="ScenesAndUI: 1edfd91";
//    public static ArrayList<String> splashes=new ArrayList<>();

    public static String[] splashes;
    public static void init(){
//        splashes.add(githubData);
//        splashes.add("Not enough bugs!");
//        splashes.add("\"opensource\"");
//        splashes.add("Give me Unity now! I hate this game engine!");
//        splashes.add("Red is sus!");
//        splashes.add()
//        AssetManager assetManager = new AssetManager();
//        assetManager.load("file.txt", TextFile.class);
//        assetManager.finishLoading();

//        FileHandle fileHandle = Gdx.files.internal("splashes.txt");
//        data.splashes = fileHandle.readString().split("\\r?\\n");
        //TODO: дать паше по шее если он удалит этот почти рабочий код или разкоментит его. Дать миску риса если починит
        /*
        AssetManager assetManager = new AssetManager();
        assetManager.load("splashes.txt", Text.class);
        assetManager.finishLoading();

        FileHandle fileHandle = assetManager.get("splashes.txt");
        String[] lines = fileHandle.readString().split("\\r?\\n");


        assetManager.dispose();
        */


// Теперь у вас есть массив строк, разделенных из .txt файла

// Освободите ресурсы assetManager, если они больше не нужны
//        assetManager.dispose();
    }
}