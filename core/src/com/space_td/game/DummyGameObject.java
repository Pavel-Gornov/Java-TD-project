package com.space_td.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class DummyGameObject extends GameObject{

    public DummyGameObject(Vector2 position, float rotation, Vector2 size, Vector2 scale, Vector2 originPoint, TextureRegion texture, boolean flipX, boolean flipY) {
        super(position, rotation, size, scale, originPoint, texture, flipX, flipY);
    }

    public DummyGameObject(Vector2 position, float rotation, Vector2 size, Vector2 scale, TextureRegion texture, boolean flipX, boolean flipY) {
        super(position, rotation, size, scale, texture, flipX, flipY);
    }

    public DummyGameObject(Vector2 position, float rotation, Vector2 scale, TextureRegion texture, boolean flipX, boolean flipY) {
        super(position, rotation, scale, texture, flipX, flipY);
    }

    public DummyGameObject(Vector2 position, float rotation, TextureRegion texture, Vector2 scale, Vector2 originPoint, boolean flipX, boolean flipY) {
        super(position, rotation, texture, scale, originPoint, flipX, flipY);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void onShowColliders() {

    }
}
