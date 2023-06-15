package com.space_td.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;


public abstract class GameObject implements Disposable {
    public Vector2 position;
    public float rotation;
    public Vector2 size;
    public Vector2 scale;
    public Vector2 originPoint;
    public TextureRegion texture;
    public Animation<Texture> animation;
    boolean flipX;
    boolean flipY;
    boolean showColliders = false;
    Rectangle collider;
    public String id;

    public GameObject(Vector2 position, float rotation, Vector2 size, Vector2 scale, Vector2 originPoint, TextureRegion texture, boolean flipX, boolean flipY) {
        this.position = position;
        this.rotation = rotation;
        this.size = size;
        this.scale = scale;
        this.originPoint = originPoint;
        this.texture = texture;
        this.flipX = flipX;
        this.flipY = flipY;
        this.id = this.getClass().getName() + Utils.randInt(0, 10000)+"";
    }

    public GameObject(Vector2 position, float rotation, Vector2 size, Vector2 scale, TextureRegion texture, boolean flipX, boolean flipY) {
        this.position = position;
        this.rotation = rotation;
        this.size = size;
        this.scale = scale;
        this.originPoint = new Vector2(size.x / 2, size.y / 2);
        this.texture = texture;
        this.flipX = flipX;
        this.flipY = flipY;
        this.id = this.getClass().getName() + Utils.randInt(0, 10000)+"";
    }

    public GameObject(Vector2 position, float rotation, Vector2 scale, TextureRegion texture, boolean flipX, boolean flipY) {
        this.position = position;
        this.rotation = rotation;
        this.size = new Vector2(texture.getRegionWidth(), texture.getRegionHeight());
        this.scale = scale;
        this.originPoint = new Vector2(size.x / 2, size.y / 2);
        this.texture = texture;
        this.flipX = flipX;
        this.flipY = flipY;
        this.id = this.getClass().getName() + Utils.randInt(0, 10000)+"";
    }

    public GameObject(Vector2 position, float rotation, TextureRegion texture, Vector2 scale, Vector2 originPoint, boolean flipX, boolean flipY) {
        this.position = position;
        this.rotation = rotation;
        this.size = new Vector2(texture.getRegionWidth(), texture.getRegionHeight());
        this.scale = scale;
        this.originPoint = originPoint;
        this.texture = texture;
        this.flipX = flipX;
        this.flipY = flipY;
        this.id = this.getClass().getName() + Utils.randInt(0, 10000)+"";
    }

    public abstract void update(float delta);

    public void draw(Batch batch) {
        if (animation != null) {
            batch.draw(animation.getKeyFrame(0f), position.x, position.y, size.x, size.y);
        } else {
            if (!batch.isDrawing())
                batch.begin();
            if (this.flipX & this.scale.x > 0)
                this.scale.x *= -1;

            if (this.flipY & this.scale.y > 0)
                this.scale.y *= -1;

            batch.draw(texture, this.position.x, this.position.y, this.originPoint.x, this.originPoint.y, this.size.x, this.size.y, this.scale.x, this.scale.y, this.rotation);
            if (this.showColliders) {
                onShowColliders();
            }
        }
    }

    public abstract void onShowColliders();


    @Override
    public void dispose() {
    }

    public void moveForward(float dist) {

        float angleRad = (float) Math.toRadians(this.rotation);


        float x = this.position.x + dist * (float) Math.cos(angleRad);
        float y = this.position.y + dist * (float) Math.sin(angleRad);

        this.position.set(x, y);
    }
}
