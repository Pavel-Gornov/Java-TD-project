package com.space_td.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Vector;

public class Planet extends GameObject {
    public float hp;
    public float armor;
    public Circle collider;
    Enemy collidedEnemy;
    boolean isEnemyCollided = false;
    public Vector2 center;

    public Planet(Vector2 position, float rotation, Vector2 scale, TextureRegion texture, boolean flipX, boolean flipY,
                  float hp, float armor) {
        super(position, rotation, scale, texture, flipX, flipY);
        this.collider = new Circle(position.x, position.y, Utils.median(this.size.x * this.scale.x, this.size.y * this.scale.y));
        this.hp = hp;
        this.armor = armor;
        center = new Vector2(0, 0);

    }


    public void draw(Batch batch, ArrayList<Enemy> enemies) {
        collider.setPosition(position.x, position.y);
        collider.radius = Utils.median(this.size.x * this.scale.x, this.size.y * this.scale.y) / 2;

        center.x = this.size.x * this.scale.x / 2;
        center.y = this.size.y * this.scale.y / 2;

        collider.setPosition(position.x + collider.radius / 3,
                position.y + collider.radius / 3);
        this.collidedEnemy = checkEnemyCollision(enemies);
        if (isEnemyCollided) {
            System.out.println("Collision! Velocity: " + collidedEnemy.velocity + "\n" + "Position: " + collidedEnemy.position.x + " " + collidedEnemy.position.y);
            this.hp -= (collidedEnemy.damage - this.armor) < 0 ? 0 : collidedEnemy.damage - this.armor;
            collidedEnemy.hp = 0;
            isEnemyCollided = false;
        }
        super.draw(batch);
    }

    public Enemy checkEnemyCollision(ArrayList<Enemy> enemies) {
        if (enemies.size() > 0)
            for (int i = 0; i < enemies.size(); i++) {
                if (collider.overlaps(enemies.get(i).collider)) {
                    isEnemyCollided = true;
                    return enemies.get(i);
                }
            }
        return null;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void onShowColliders() {

    }
}
