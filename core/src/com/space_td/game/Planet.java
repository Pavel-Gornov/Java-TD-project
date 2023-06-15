package com.space_td.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Planet extends GameObject{
    public float hp;
    public float armor;
    public Circle collider;
    Enemy collidedEnemy;
    boolean isEnemyCollided = false;

    public Planet(Vector2 position, float rotation, Vector2 scale, TextureRegion texture, boolean flipX, boolean flipY,
                  float hp, float armor) {
        super(position, rotation, scale, texture, flipX, flipY);
        this.collider = new Circle(position.x, position.y, Utils.median(this.size.x * this.scale.x, this.size.y * this.scale.y));
        this.hp = hp;
        this.armor = armor;

    }


    public void draw(Batch batch, ArrayList<Enemy> enemies) {
        collider.setPosition(position.x, position.y);
        this.collidedEnemy = Utils.checkForCollision(this, enemies);
        if (Utils.checkForCollision(this, enemies) != null) isEnemyCollided = true;
        if (isEnemyCollided) {
            System.out.println("Collision!");
            this.hp -= (collidedEnemy.damage - this.armor) < 0 ? 0 : collidedEnemy.damage - this.armor;
            collidedEnemy.hp = 0;
            isEnemyCollided = false;
        }
        super.draw(batch);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void onShowColliders() {

    }
}
