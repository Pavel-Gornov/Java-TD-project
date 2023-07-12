package com.space_td.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class Planet extends Actor implements Disposable {
    public Vector2 position;
    public Vector2 size;
    public Vector2 scale;
    public TextureRegion texture;
    public  Vector2 originPoint;
    public float rotation;

    public Planet(Texture t, Vector2 p, float r, Vector2 s){
        texture = new TextureRegion(t);
        position = p;
        rotation = r;
        size = new Vector2(t.getHeight(), t.getWidth());
        scale = s;
        originPoint = new Vector2(size.x / 2f, size.y / 2f);
    }
    public void draw(Batch batch){
        batch.draw(texture, position.x - size.x / 2f, position.y - size.y / 2f, originPoint.x, originPoint.y, size.x, size.y, scale.x, scale.y, rotation);
    }
    @Override
    public void dispose() {
    }
}
