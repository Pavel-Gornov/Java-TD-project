package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Enemy extends GameObject {
    public ArrayList<Vector2> points;
    public float hp;
    public float damage;
    public float armor;
    public float speed;
    public int tier;
    public EnemyTypes type;
    float estimatedMooveTime = 0;
    boolean isPathCompleted = false;

    public enum EnemyTypes {
        BASIC,
        SPEEDY,
    }

    public Enemy(Vector2 position, float rotation, Vector2 scale,
                 TextureRegion texture, boolean flipX, boolean flipY,
                 float hp, float armor, float damage, float speed, int tier, EnemyTypes type, ArrayList<Vector2> points) {
        super(position, rotation, scale, texture, flipX, flipY);
        this.hp = hp;
        this.damage = damage;
        this.armor = armor;
        this.speed = speed;
        this.tier = tier;
        this.type = type;
        this.points = points;
        try {
            estimatedMooveTime = position.dst(points.get(0));
        } catch (IndexOutOfBoundsException e) {
            this.points.add(new Vector2(0, 0));
            estimatedMooveTime = position.dst(points.get(0));
        }


    }

    public void addPoint(Vector2 point) {
        points.add(point);
    }

    public void draw(Batch batch) {
        if (points.size() == 0) {
            points.add(this.position);
            isPathCompleted = true;
        }
        if (!isPathCompleted) {
            rotation = Utils.getAngle(points.get(0), position);
            if (estimatedMooveTime <= 0) {
                estimatedMooveTime = position.dst(points.get(0));
                points.remove(0);
            }
            moveForward(Gdx.graphics.getDeltaTime() * speed);
            estimatedMooveTime -= Gdx.graphics.getDeltaTime() * speed;
        }

        switch (type) {
            case BASIC:
                batch.setColor(1, 0.2f, 0.01f, 1);
                break;
            case SPEEDY:
                batch.setColor(1, 1, 0.01f, 1);
                break;
        }
        super.draw(batch);
        batch.setColor(1, 1, 1, 1);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void onShowColliders() {

    }
}
