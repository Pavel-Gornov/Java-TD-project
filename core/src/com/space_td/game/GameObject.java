package com.space_td.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

//игровые объекты
public abstract class GameObject implements Disposable {
    public Vector2 position; //положение
    public float rotation; //вращение ПРОТИВ часовой стрелки
    public Vector2 size; //размеры в пикселях
    public Vector2 scale; //просто на что домножаются размеры
    public Vector2 originPoint; //то обо что вращается
    public TextureRegion texture; //текстура
    public Animation<Texture> animation;
    boolean flipX;
    boolean flipY;
    boolean showColliders = false;

    public GameObject(Vector2 position, float rotation, Vector2 size, Vector2 scale, Vector2 originPoint, TextureRegion texture, boolean flipX, boolean flipY) {
        this.position = position;
        this.rotation = rotation;
        this.size = size;
        this.scale = scale;
        this.originPoint = originPoint;
        this.texture = texture;
        this.flipX = flipX;
        this.flipY = flipY;
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
    }

    public abstract void update(float delta);

    public void draw(Batch batch) {
        if (animation != null) {
            batch.draw(animation.getKeyFrame(0f), position.x, position.y, size.x, size.y);
        } else {
            if (!batch.isDrawing())
                batch.begin();

//            TextureRegion tr = new TextureRegion(this.texture, 0, 0, this.texture.getWidth(), this.texture.getHeight());
//            batch.draw(this.texture, this.position.x, this.position.y, this.originPoint.x, this.originPoint.y, this.size.x, this.size.y, this.scale.x, this.scale.y, this.rotation, 0, 0, this.texture.getWidth(), this.texture.getHeight(), this.flipX, this.flipY);
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
//        this.shapeRenderer.dispose();
//        this.texture.dispose();
//        this.texture.dispose();

    }
}
