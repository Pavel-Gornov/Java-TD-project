package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Nebula extends GameObject{
    public Color color;
    public Nebula(Vector2 position, float rotation, Vector2 size, Vector2 scale, Vector2 originPoint, TextureRegion texture, boolean flipX, boolean flipY, Color color) {
        super(position, rotation, size, scale, originPoint, texture, flipX, flipY);
        this.color=color;
    }

    public static Nebula makeNebula(Vector2 vec1, Vector2 vec2, Color color1, Color color2){
        float randScale = Utils.randFloat(0.5f, 2.5f);
        float globalNebulaScaleModifier=1;
        TextureRegion rTexture = data.nebulaTextures.get(Utils.randInt(0, data.nebulaTextures.size()-1));
        Vector2 rPos=new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), Utils.randFloat(0, Gdx.graphics.getHeight()));
        Color rGrColor = Utils.getGradientColor(vec1, color1, vec2, color2, rPos);
        rGrColor.a=Utils.randFloat(0.5f, 1f);
        return new Nebula(rPos, Utils.randFloat(0, 360), new Vector2(rTexture.getRegionWidth(), rTexture.getRegionHeight()),
                new Vector2(randScale*globalNebulaScaleModifier, randScale*globalNebulaScaleModifier),
                new Vector2(32*globalNebulaScaleModifier, 32*globalNebulaScaleModifier),
                rTexture,
                Utils.randBoolean(), Utils.randBoolean(), rGrColor);
    }
    public void draw(Batch batch){
        batch.setColor(color);
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
