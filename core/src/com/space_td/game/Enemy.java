package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

import java.util.ArrayList;

public class Enemy extends GameObject {
    public ArrayList<Vector2> points;
    public float hp;
    public float damage;
    public float armor;
    public float speed;
    public int tier;
    public EnemyTypes type;
    public float estimatedMooveTime = 0;
    boolean isPathCompleted = false;
    public Vector2 colliderSizeModifier;
    public Vector2 colliderSizes;
    public Circle collider;


    public enum EnemyTypes {
        BASIC,
        SPEEDY,
    }

    public Enemy(Vector2 position, float rotation, Vector2 scale,
                 TextureRegion texture, boolean flipX, boolean flipY,
                 float hp, float armor, float damage, float speed, int tier, EnemyTypes type, ArrayList<Vector2> points, Vector2 colliderSizeModifier) {
        super(position, rotation, scale, texture, flipX, flipY);
        this.hp = hp;
        this.damage = damage;
        this.armor = armor;
        this.speed = speed;
        this.tier = tier;
        this.type = type;
        this.points = points;
        this.colliderSizeModifier = colliderSizeModifier;
        this.showColliders=false;
        this.colliderSizes = new Vector2(0.1f, 0.1f);
        this.colliderSizes.x = this.size.x * this.colliderSizeModifier.x * this.scale.x;
        this.colliderSizes.y = this.size.y * this.colliderSizeModifier.y * this.scale.y;

//        this.collider = new Rectangle(originPoint.x,
//                originPoint.y,
//                colliderSizes.x, colliderSizes.y);
        this.collider=new Circle(this.position.x, this.position.y, Utils.median(this.size.x * this.scale.x*this.colliderSizeModifier.x, this.size.y * this.scale.y*this.colliderSizeModifier.y));
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
        collider.setPosition(position.x - colliderSizes.x / 2,
                position.y - colliderSizes.y / 2);
        super.draw(batch);
        batch.setColor(1, 1, 1, 1);

    }
    public void renderColliders(ShapeRenderer renderer){



        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.RED);
        //renderer.rect(collider.x, collider.y, collider.getWidth(), collider.getHeight());
        renderer.circle(collider.x, collider.y, collider.radius);


    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void onShowColliders() {


    }
}
