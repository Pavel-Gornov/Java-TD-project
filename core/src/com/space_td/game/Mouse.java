package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.bcel.internal.generic.PUSH;

import java.util.ArrayList;

public class Mouse extends GameObject {
    public Circle collider;
    public float colliderRadius;
    public float damage;
    public float attackCD;
    public float attacksPerSecond;

    public Mouse(Vector2 position, float rotation, Vector2 size, Vector2 scale, Vector2 originPoint, TextureRegion texture, boolean flipX, boolean flipY, float colliderRadius, float damage, float attacksPerSecond) {
        super(position, rotation, size, scale, originPoint, texture, flipX, flipY);
        this.colliderRadius=colliderRadius;
        this.collider=new Circle(position, colliderRadius);
        this.damage=damage;
        this.attackCD=0;
        this.attacksPerSecond=attacksPerSecond;
    }


    public void draw(Batch batch, ArrayList<Enemy> enemies) {
        position.x=data.mousePos.x-((this.scale.x*this.size.x)/2);
        position.y=data.mousePos.y-((this.scale.y*this.size.y)/2);
        collider.radius=colliderRadius;
        collider.setX(position.x);
        collider.setY(position.y);
        onShowColliders();
        checkCollisions(enemies);
        attackCD-= Utils.getDTime();
        batch.setColor(1, 1, 1, 0.3f);
        super.draw(batch);
        batch.setColor(1, 1, 1, 1);
    }
    public void checkCollisions(ArrayList<Enemy> enemies){
        if (enemies.size() > 0)
            for (int i = 0; i < enemies.size(); i++) {
                if (collider.overlaps(enemies.get(i).collider)) {
                    if (attackCD<=0) {
                        enemies.get(i).hp -= damage;
                        attackCD = 1 / attacksPerSecond;
                    }
                }
            }

    }
    @Override
    public void update(float delta) {

    }

    @Override
    public void onShowColliders() {

    }
}
