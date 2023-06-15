package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class Utils {
    private static final Random random = new Random();

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
        float diff = b - a;
        return (float) (Math.random() * diff) + a;

//        return (random.nextFloat() * (b - a)) + a;
    }

    public static int randInt(int a, int b) {
//        int difference = b - a;
//        int randomOffset = random.nextInt(difference + 1);
//        return a + randomOffset;
        int diff = b - a;
        return (int) (Math.floor(Math.random() * (diff + 1)) + a);
    }

    public static boolean randBoolean() {
        return Math.random() >= 0.500001;
    }

    public static float getAngle(Vector2 point, Vector2 position) {
        // Находим вектор от объекта до точки point
        Vector2 targetDirection = point.cpy().sub(position);

        // Находим угол между вектором от объекта до точки и осью x
        float angle = (float) Math.atan2(targetDirection.y, targetDirection.x);

        // Приводим угол к градусам
        angle = (float) Math.toDegrees(angle);

        return angle;
    }

    public static Color getGradientColor(Vector2 topLeftPos, Color topLeftColor, Vector2 bottomRightPos, Color bottomRightColor, Vector2 point) {
        // Получаем ширину и высоту экрана
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        // Рассчитываем коэффициенты для перевода координат из пикселей в диапазон [0, 1]
        float xFactor = 1f / screenWidth;
        float yFactor = 1f / screenHeight;

        // Координаты цветовых точек в диапазоне [0, 1]
        float x1 = topLeftPos.x * xFactor;
        float y1 = (screenHeight - topLeftPos.y) * yFactor;
        float x2 = bottomRightPos.x * xFactor;
        float y2 = (screenHeight - bottomRightPos.y) * yFactor;

        // Создаем два цвета-вершины градиента
        Color color1 = new Color(topLeftColor);
        Color color2 = new Color(bottomRightColor);

        // Рассчитываем длину вектора градиента
        float gradientLength = new Vector2(x2 - x1, y2 - y1).len();

        // Рассчитываем расстояние от точки до начала и конца градиента
        float distanceFromStart = new Vector2(point.x * xFactor - x1, (screenHeight - point.y) * yFactor - y1).len();
        float distanceFromEnd = new Vector2(point.x * xFactor - x2, (screenHeight - point.y) * yFactor - y2).len();

        // Рассчитываем позицию точки на градиенте
        float positionOnGradient = 1f - (distanceFromStart / gradientLength);

        // Создаем новый цвет с учетом позиции на градиенте
        Color color = new Color();
        color.set(color1);
        color.lerp(color2, positionOnGradient);

        return color;
    }

    public static float median(float a, float b) {
        return (a + b) / 2;
    }

    public static <T extends GameObject, T2 extends GameObject> T2 checkForCollision(T obj, ArrayList<T2> objects) {
        System.out.println("Collision check called by " + obj.id);
        if (obj.collider == null || objects.size() == 0) {
            System.out.println(obj.collider == null ? "No collider!" : "No objects to check in " + objects.getClass().getName());
            return null;
        }

        for (int i = 0; i < objects.size(); i++) {
            if (obj.collider.overlaps(objects.get(i).collider)) {
                System.out.println("Collision with " + objects.get(i).id);
                return objects.get(i);
            }
        }
        System.out.println("No collisions with objects from " + objects.getClass().getName());
        return null;
    }


}
