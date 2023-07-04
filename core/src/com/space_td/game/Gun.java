package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Gun extends GameObject{
    public boolean followCursor;
    public float damage;
    public float shootCD;
    public float shootsPerSecond;
    public boolean reusableBullets;
    public float bulletSpeed;
    public float bulletSize;

    public Gun(Vector2 position, float rotation, Vector2 scale, TextureRegion texture, boolean flipX, boolean flipY, boolean followCursor, float damage, float shootsPerSecond, boolean reusableBullets, float bulletSpeed) {
        super(position, rotation, scale, texture, flipX, flipY);
        this.followCursor=followCursor;
        this.damage=damage;
        this.shootCD=1/shootsPerSecond;
        this.shootsPerSecond=shootsPerSecond;
        this.reusableBullets=reusableBullets;
        this.bulletSpeed=bulletSpeed;
        this.bulletSize=1;
    }

    @Override
    public void update(float delta) {

    }


    public void draw(Batch batch, ArrayList<Bullet> bullets, TextureRegion bulletTexture, Sound shootSound, float shootSoundVolme) {
        if (followCursor){
            float cursorX = Gdx.input.getX();
            float cursorY = Gdx.input.getY();

            Vector2 direction = new Vector2(cursorX - position.x, cursorY - position.y);
            direction.nor();

            float angle = MathUtils.atan2(direction.y, direction.x) * MathUtils.radiansToDegrees;

            rotation=angle*-1;
        }
        shootCD-=Utils.getDTime();
        if(shootCD<=0){
            bullets.add(new Bullet(new Vector2(this.position), Float.valueOf(this.rotation), new Vector2(1, 1), bulletTexture, false, false, Float.valueOf(this.damage), this.reusableBullets, this.bulletSpeed, bulletTexture.getRegionWidth()*bulletSize));
            shootCD=1/shootsPerSecond;
            long soundID = shootSound.play();
            shootSound.setVolume(soundID, shootSoundVolme);
        }
        super.draw(batch);
    }

    @Override
    public void onShowColliders() {

    }
}
