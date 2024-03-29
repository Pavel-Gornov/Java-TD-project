package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Star extends GameObject {
    //TODO: сделать цветные звёзды
    public float alpha;
    public float alphaMax;
    public float alphaMin;
    public float alphaGoal;
    public float lifetime;
    public boolean alphaGoalDirection;
    public boolean isDestroyed = false;
    public float alphaChangeSpeed;
    public float alphaChangePause;

    public Star(Vector2 position, float rotation, Vector2 size, Vector2 scale, Vector2 originPoint, TextureRegion texture, boolean flipX, boolean flipY,
                float alpha, float alphaMax, float alphaMin, float lifetime, float alphaChangePause, float alphaChangeSpeed, boolean alphaGoalDirection, float alphaGoal) {
        super(position, rotation, size, scale, originPoint, texture, flipX, flipY);
        this.alpha = alpha;
        this.alphaMax = alphaMax;
        this.alphaMin = alphaMin;
        this.lifetime = lifetime;
        this.alphaGoal = alphaGoal;
        this.alphaGoalDirection = alphaGoalDirection;
        this.alphaChangePause = alphaChangePause;
        this.alphaChangeSpeed = alphaChangeSpeed;

    }

    public static Star makeStar(ArrayList<TextureRegion> textures) {
        float rScale = Utils.randFloat(0.3f, 2.5f);
        float rMinAlpha = Utils.randFloat(0, 0.8f);
        float rMaxAplha = Utils.randFloat(rMinAlpha, 0.8f);
        float rAlpha = Utils.randFloat(rMinAlpha, rMaxAplha);
        float rGoal = Utils.randFloat(rMinAlpha, rMaxAplha);
        boolean aDir = rAlpha < rGoal;
        float globalStarModifier = 1;
        return new Star(
                new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), Utils.randFloat(0, Gdx.graphics.getHeight())),
                Utils.randFloat(0, 360), new Vector2(8 * globalStarModifier, 8 * globalStarModifier), new Vector2(rScale, rScale),
                new Vector2(4 * globalStarModifier, 4 * globalStarModifier), textures.get(Utils.randInt(0, textures.size() - 1)),
                Utils.randBoolean(), Utils.randBoolean(), rAlpha, rMaxAplha, rMinAlpha, Utils.randFloat(7f, 60f), 0f,
                Utils.randFloat(0.3f, 2.5f), aDir, rGoal);

    }

    public void draw(Batch batch) {
        if (this.lifetime <= 0 & this.alpha <= 0) this.isDestroyed = true;
        if (!this.isDestroyed) {
            batch.setColor(1f, 1f, 1f, alpha);
            super.draw(batch);
            batch.setColor(1f, 1f, 1f, 1f);
        }
        lifetime -= Utils.getDTime();
        if (lifetime <= 0) {
            this.alphaGoalDirection = false;
            this.alphaGoal = 0f;
            this.alphaChangePause = 0f;
            this.alphaMin = 0;
        }
        if ((this.alpha >= this.alphaGoal & this.alphaGoalDirection) || (this.alpha <= this.alphaGoal & !this.alphaGoalDirection)) {
            this.alphaChangePause = Utils.randFloat(0.1f, 1.5f);
            this.alphaGoal = Utils.randFloat(this.alphaMin, this.alphaMax);
            this.alphaGoalDirection = this.alpha < this.alphaGoal;
            this.alphaChangeSpeed = Utils.randFloat(0.7f, 1.7f);
        }
        this.alphaChangePause -= Utils.getDTime();
        if (this.alphaChangePause <= 0) {
            this.alpha += Utils.getDTime() * this.alphaChangeSpeed * (this.alphaGoalDirection ? 1 : -1);
        }


    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void onShowColliders() {

    }
}
