package com.space_td.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class data {
    //vars
    public static ArrayList<TextureRegion> enemyTextures = Utils.splitRegion(new Texture("enemies.png"), 16, 16);
    public static ArrayList<TextureRegion> nebulaTextures = Utils.splitRegion(new Texture("nebulas_white.png"), 64, 64);
}
