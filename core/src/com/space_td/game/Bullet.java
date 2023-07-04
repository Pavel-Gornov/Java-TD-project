package com.space_td.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Bullet extends GameObject {
    public float damage;
    public boolean reusable;
    public float speed;
    public Color color = new Color(1, 1, 1, 1);
    public float colliderSize;
    public Circle collider;

    public Bullet(Vector2 position, float rotation, Vector2 scale, TextureRegion texture, boolean flipX, boolean flipY, float damage, boolean reusable, float speed, float colliderSize) {
        super(position, rotation, scale, texture, flipX, flipY);
        this.damage = damage;
        this.reusable = reusable;
        this.speed = speed;
        this.colliderSize = colliderSize;
        collider = new Circle(this.position.x, this.position.y, colliderSize);
    }

    public void draw(Batch batch, ArrayList<Enemy> enemies) {
        collider.setX(position.x);
        collider.setY(position.y);
        moveForward(speed * Utils.getDTime());


        super.draw(batch);
        if (enemies.size() > 0) {
            for (int i = 0; i < enemies.size(); i++) {
                if (this.collider.overlaps(enemies.get(i).collider)){
                    if (!reusable){
                        enemies.get(i).hp-=damage;
                        isDestroyed=true;

                    }
                    else {
                        if (enemies.get(i).hp>=damage){
                            enemies.get(i).hp-=damage;
                            isDestroyed=true;
                        }
                        else {
                            enemies.get(i).hp-=damage;
                            this.damage=enemies.get(i).hp*-1;
                        }
                    }
                }
            }
        }
        if (damage<=0){
            super.destroy();
        }
    }

    @Override
    public void update(float delta) {

    }


    @Override
    public void onShowColliders() {

    }
}
