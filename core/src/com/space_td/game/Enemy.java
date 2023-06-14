package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Enemy extends GameObject {
    public ArrayList<Vector2> points = new ArrayList<>();
    public float hp;
    public float damage;
    public float armor;
    public float speed;
    public int tier;
    public EnemyClasses _class;
    float estimatedMooveTime = 0;
    boolean isPathCompleted = false;

    public enum EnemyClasses {
        BASIC
    }

    public Enemy(Vector2 position, float rotation, Vector2 scale,
                 TextureRegion texture, boolean flipX, boolean flipY,
                 float hp, float armor, float damage, float speed, int tier, EnemyClasses _class, ArrayList<Vector2> points
    ) {
        super(position, rotation, scale, texture, flipX, flipY);
        this.hp = hp;
        this.damage = damage;
        this.armor = armor;
        this.speed = speed;
        this.tier = tier;
        this._class = _class;
        this.points = points;
        try {
            this.estimatedMooveTime = position.dst(points.get(0));
        } catch (IndexOutOfBoundsException e) {
            this.points.add(new Vector2(0, 0));
            this.estimatedMooveTime = position.dst(points.get(0));
        }


    }

    public void addPoint(Vector2 point) {
        this.points.add(point);
    }

    public void draw(Batch batch) {
        if (this.points.size() == 0) {
            this.points.add(this.position);
            this.isPathCompleted = true;
        }
        if (!this.isPathCompleted) {
            this.rotation = Utils.getRotation(this.points.get(0), this.position);
            if (this.estimatedMooveTime <= 0) {
                this.estimatedMooveTime = this.position.dst(this.points.get(0));
                this.points.remove(0);
            }
            moveForward(Gdx.graphics.getDeltaTime() * this.speed);
            this.estimatedMooveTime -= Gdx.graphics.getDeltaTime() * this.speed;
        }
        //НА if НЕ ЗАМЕНЯТЬ, КОД ТУТ НЕ ЧИСТИТЬ, ВСЁ ПОЛЕТИТ К ЧЕРТЯМ!!!
        //TODO: не чистить тут код, добавить больше энумов
        switch (this._class) {
            case BASIC:
                batch.setColor(1, 0.2f, 0.01f, 1);
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
