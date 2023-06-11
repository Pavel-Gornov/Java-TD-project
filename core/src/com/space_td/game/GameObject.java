package com.space_td.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

import org.w3c.dom.Text;

//игровые объекты
public class GameObject implements Disposable {
    public Texture texture;
    public float sizeX;
    public float sizeY;
    public float x;
    public float y;
    public float rotation;
    private ShapeRenderer shapeRenderer;
    public Rectangle collider;
    public boolean showCollider;
    SpriteBatch sceneSpriteBatch;
    TextureRegion spriteRegion;




    public GameObject(float x, float y, Texture texture, float sizeX, float sizeY, float rotation, Rectangle collider, boolean showCollider, SpriteBatch sceneSpriteBatch) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.rotation = rotation;
        this.collider = collider;
        this.showCollider = showCollider;
        this.shapeRenderer=new ShapeRenderer();
        this.sceneSpriteBatch=sceneSpriteBatch;
        this.spriteRegion= new TextureRegion(this.texture);
    }

    public GameObject(float x, float y, Texture texture, Rectangle collider, SpriteBatch sceneSpriteBatch) {
        construct(x, y, texture, 1, 1, 0, collider, false, sceneSpriteBatch);
    }
    public GameObject(float x, float y, Texture texture, float sizeX, float sizeY, float rotation, SpriteBatch sceneSpriteBatch) {
        construct(x, y, texture, 1, 1, rotation, new Rectangle(x, y, texture.getWidth()*sizeX, texture.getWidth()*sizeY), false, sceneSpriteBatch);
    }

    public GameObject construct(float x, float y, Texture texture, float sizeX, float sizeY, float rotation, Rectangle collider, boolean showCollider, SpriteBatch sceneSpriteBatch) {
        return new GameObject(x, y, texture, sizeX, sizeY, rotation, collider, showCollider, sceneSpriteBatch);
    }

    public void setPosition(float x, float y){
        this.collider.setPosition(x, y);
        this.x=x;
        this.y=y;
    }
    public void setRotation(float degrees) {
        this.rotation=degrees;
    }
    public void setSize(float width, float height){
        this.sizeX=width;
        this.sizeY=height;
        this.collider.setSize(width, height);

    }
    public void draw(){
        if (this.shapeRenderer==null){
            this.shapeRenderer=new ShapeRenderer();
        }

        if (this.showCollider){
            this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            this.shapeRenderer.rect(this.collider.x, this.collider.y, this.collider.width, this.collider.height);
            this.shapeRenderer.end();
        }



        this.sceneSpriteBatch.begin();
        this.sceneSpriteBatch.draw(this.spriteRegion, this.x, this.y, this.x/2, this.y/2, this.sizeX, this.sizeY, this.texture.getWidth(), this.texture.getWidth(), this.rotation);
        this.sceneSpriteBatch.end();

    }
    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
        this.texture.dispose();
    }
}
