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
import java.util.Vector;

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
    public float freezeTime;


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
        this.showColliders = false;
        this.colliderSizes = new Vector2(0.1f, 0.1f);
//        this.colliderSizes.x = this.size.x * this.colliderSizeModifier.x;
//        this.colliderSizes.y = this.size.y * this.colliderSizeModifier.y;
        this.colliderSizes.x = this.size.x / 2;
        this.colliderSizes.y = this.size.y / 2;

//        this.collider = new Rectangle(originPoint.x,
//                originPoint.y,
//                colliderSizes.x, colliderSizes.y);
        this.collider = new Circle((this.position.x + (this.size.x * this.scale.x)) / 2, (this.position.y + (this.size.y * this.scale.y)) / 2, Utils.median(colliderSizes.x, colliderSizes.y));
        try {
            estimatedMooveTime = position.dst(points.get(0));
        } catch (IndexOutOfBoundsException e) {
            this.points.add(new Vector2(0, 0));
            estimatedMooveTime = position.dst(points.get(0));
        }
        freezeTime = 1;


    }

    public void addPoint(Vector2 point) {
        points.add(point);
    }

    public void draw(Batch batch) {
        if (freezeTime > 0) freezeTime -= Gdx.graphics.getDeltaTime() * data.gameSpeed;
        if (freezeTime <= 0) {
            if (points.size() == 0) {
                points.add(this.position);
                isPathCompleted = true;
            }
//        collider.setX(((this.position.x+(this.size.x*this.scale.x))/2)+this.collider.radius);
//        collider.setY(((this.position.y+(this.size.y*this.scale.y))/2)+this.collider.radius);
            if (!isPathCompleted) {
                rotation = Utils.getAngle(points.get(0), position);
                if (estimatedMooveTime <= 0) {
                    estimatedMooveTime = position.dst(points.get(0));
                    points.remove(0);
                }
                moveForward((Gdx.graphics.getDeltaTime() * data.gameSpeed) * speed);
                estimatedMooveTime -= (Gdx.graphics.getDeltaTime() * data.gameSpeed) * speed;
            }

            switch (type) {
                case BASIC:
                    batch.setColor(1, 0.2f, 0.01f, 1);
                    break;
                case SPEEDY:
                    batch.setColor(1, 1, 0.01f, 1);
                    break;
            }
//        collider.setPosition(position.x - colliderSizes.x / 2,
//                position.y - colliderSizes.y / 2);
        }
        if (hp <= 0) freezeTime = 0;
        collider.setPosition(position.x + collider.radius,
                position.y + collider.radius);

        super.draw(batch);

        batch.setColor(1, 1, 1, 1);

    }

    public void renderColliders(ShapeRenderer renderer) {


        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.RED);
        //renderer.rect(collider.x, collider.y, collider.getWidth(), collider.getHeight());
        renderer.circle(collider.x, collider.y, collider.radius);
        renderer.point(collider.x, collider.y, 0);


    }

    public static Enemy spawnEnemy(Planet planet, ArrayList<TextureRegion> textureRegions) {
        byte side = (byte) Utils.randInt(0, 3);
        Vector2 pos = new Vector2();
        ArrayList<Vector2> points = new ArrayList<>();


        if (side == 0) {
//            pos.y = Gdx.graphics.getHeight() - 1;
//            pos.x = Utils.randFloat(0, Gdx.graphics.getWidth());
            pos=new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), Gdx.graphics.getHeight());
            points.add(new Vector2(pos.x, pos.y - 10));
        }
        if (side == 1) {
//            pos.y = Utils.randFloat(0, Gdx.graphics.getHeight());
//            pos.x = Gdx.graphics.getWidth() - 1;
            pos = new Vector2(Gdx.graphics.getWidth(), Utils.randFloat(0, Gdx.graphics.getHeight()));
            points.add(new Vector2(pos.x - 10, pos.y));
        }
        if (side == 2) {
//            pos.y = 1;
//            pos.x = Utils.randFloat(0, Gdx.graphics.getWidth());
            pos = new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), 0f);
            points.add(new Vector2(pos.x, pos.y + 10));
        }
        if (side == 3) {
//            pos.y = Utils.randFloat(0, Gdx.graphics.getHeight());
//            pos.x = 1;
            pos = new Vector2(0f, Utils.randFloat(0, Gdx.graphics.getHeight()));
            points.add(new Vector2(pos.x + 10, pos.y));
        }
        float hp = Utils.randFloat(10, 50 * data.gameDifficulty);
        float armor = Utils.randFloat(0, 3 * data.gameDifficulty);
        float damage = Utils.randFloat(1, 5 * data.gameDifficulty);
        float speed = Utils.randFloat(40, 60);
        int tier = 0;
        if (hp <= 100) {
            Utils.doNothing(); //TODO: не трогать
        } else if (hp <= 200) {
            tier = 1;
        } else if (hp <= 500) {
            tier = 2;
        } else if (hp <= 1000) {
            tier = 3;
        } else if (hp <= 3000) {
            tier = 4;
        } else if (hp <= 7000) {
            tier = 5;
        } else if (hp <= 15000) {
            tier = 6;
        } else tier = 7;
        EnemyTypes type = EnemyTypes.BASIC;
        if (data.gameDifficulty < 100 & data.gameDifficulty > 50) {
            int typeInt = Utils.randInt(0, 1);
            type = typeInt == 0 ? EnemyTypes.BASIC : EnemyTypes.SPEEDY;
        }

        points.add(new Vector2(planet.position));
        points.add(new Vector2(planet.position));
        points.add(new Vector2(planet.position));
        points.add(new Vector2(planet.position));
        Enemy enem = new Enemy(pos, 0, new Vector2(2, 2), textureRegions.get(tier), false, false, hp, armor, damage, speed, tier, type, new ArrayList<>(points), new Vector2(1, 1));
//        Enemy enem = new Enemy(new Vector2(10, 10), 0, new Vector2(2, 2), textureRegions.get(tier), false, false, hp, armor, damage, speed, tier, type, new ArrayList<>(points), new Vector2(1, 1));
        enem.isPathCompleted = false;


        enem.estimatedMooveTime = enem.position.dst(points.get(0));
        return enem;

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void onShowColliders() {


    }
}
