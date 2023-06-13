package com.space_td.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class Utils {

    public static Vector2 calcCenter(float sizeX, float sizeY) {
        return new Vector2(sizeX / 2f, sizeY / 2f);
    }

    public static Texture getTextureFromRegion(TextureRegion region) {
        TextureData data = region.getTexture().getTextureData();
        data.prepare();
        Texture texture = new Texture(data);
        data.disposePixmap();

        return texture;
    }

    public static ArrayList<Texture> splitTexture(Texture texture, int sizeX, int sizeY) {
        ArrayList<Texture> textures = new ArrayList<Texture>();
        int rows = texture.getHeight() / sizeY;
        int cols = texture.getWidth() / sizeX;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                textures.add(Utils.getTextureFromRegion(new TextureRegion(texture, i * sizeX, j * sizeY, sizeX, sizeY)));
            }
        }
        return textures;
    }
    public static ArrayList<TextureRegion> splitRegion(Texture texture, int sizeX, int sizeY) {
        ArrayList<TextureRegion> textures = new ArrayList<TextureRegion>();
        int rows = texture.getHeight() / sizeY;
        int cols = texture.getWidth() / sizeX;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                textures.add(new TextureRegion(texture, i * sizeX, j * sizeY, sizeX, sizeY));
            }
        }
        return textures;
    }

    public static float randFloat(float a, float b) {
        if (a > b) {
            float temp = a;
            a = b;
            b = temp;
        }
        if (a == b)
            return a;
        Random random = new Random();
        return (random.nextFloat() * (b - a)) + a;
    }

    public static int randInt(int a, int b) {
        Random random = new Random();
        int difference = b - a;
        int randomOffset = random.nextInt(difference + 1);
        return a + randomOffset;
    }
    public static boolean randBoolean(){
        Random random = new Random();
        return random.nextBoolean();
    }
}
