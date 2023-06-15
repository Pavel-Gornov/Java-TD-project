package com.space_td.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;


public class GameMain extends ApplicationAdapter {
    public static String debugData;
    private SpriteBatch batch;
    private Texture planet;
    private ArrayList<TextureRegion> starTextures;
    private ArrayList<TextureRegion> enemyTextures;
    private ArrayList<TextureRegion> nebulaTextures;
    public float ScrWidth;
    public float ScrHeight;
    public float planet_x;
    public float planet_y;
    public OrthographicCamera camera;
    DummyGameObject dg;
    ArrayList<Star> stars = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Nebula> nebulas = new ArrayList<>();
    int starsLimit;
    int nebulaLimit;
    long windowSpace;
    float starCountModifier = 1f;
    float nebulaCountModifier = 1f;
    float screenBordersMedian;
    public Color gradientColor1 = new Color(0.01f, 0.9f, 0.8f, 0.7f);
    public Color gradientColor2 = new Color(1, 0, 0.83f,0.7f);

    @Override
    public void create() {
        ScrHeight = Gdx.graphics.getHeight();
        ScrWidth = Gdx.graphics.getWidth();
        camera = new OrthographicCamera(ScrWidth, ScrHeight);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        load_textures();

        recalcStarCount();
        fixStarsArraySize();
        recalcNebulaCount();
        fixNebulaArraySize();
        ArrayList<Vector2> points = new ArrayList<>();
        for (int j = 0; j < 10; j++) {

            points.add(new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), Utils.randFloat(0, Gdx.graphics.getHeight())));
            for (int i = 0; i < 100; i++) {
                Vector2 vec = new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), Utils.randFloat(0, Gdx.graphics.getHeight()));
                while ((points.size() != 0) & (points.get(points.size() - 1).dst(vec) < 10)) {
                    vec = new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), Utils.randFloat(0, Gdx.graphics.getHeight()));
                }
                points.add(vec);
            }
            enemies.add(new Enemy(new Vector2(10, 10), 0, new Vector2(2, 2), enemyTextures.get(0), false, false, 1, 1, 1, 50, 1, Enemy.EnemyTypes.BASIC, new ArrayList<>(points)));
            points.clear();
        }
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        planet_x = ScrWidth / 2f;
        planet_y = ScrHeight / 2f;
        dg = new DummyGameObject(new Vector2(ScrWidth / 2, ScrHeight / 2), 0, new Vector2(1, 1), new TextureRegion(new Texture("planet.png")), false, false);
        dg.scale.set(new Vector2(4, 4));
        camera.update();
    }
    protected void load_textures(){
        planet = new Texture("planet.png");
        enemyTextures = Utils.splitRegion(new Texture("enemies.png"), 16, 16);
        starTextures = Utils.splitRegion(new Texture("stars.png"), 8, 8);
        nebulaTextures = Utils.splitRegion(new Texture("nebulas_white.png"), 64, 64);
    }


    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);
        Vector2 worldMousePos = new Vector2(mousePos.x, mousePos.y);
        System.out.println("Mouse position: "+mousePos.x+" "+mousePos.y);
        dg.rotation += Gdx.graphics.getDeltaTime() * 2f;
        batch.begin();
        for (int i = 0; i < nebulas.size(); i++) {
            nebulas.get(i).draw(batch);
        }
        for (int i = 0; i < stars.size(); i++) {
            if (stars.get(i).isDestroyed) stars.set(i, Star.makeStar(starTextures));
            stars.get(i).draw(batch);
            debugData = "Stars count: " + stars.size() + "\n";
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(batch);
        }
        dg.draw(batch);
        if (batch.isDrawing()) batch.end();

        camera.update();
        debugData += "\nFPS: " + (int) (1 / Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
        recalcStarCount();
        fixStarsArraySize();
        recalcNebulaCount();
        fixNebulaArraySize();
    }

    @Override
    public void dispose() {
        batch.dispose();
        planet.dispose();
        dg.dispose();
        for (int i = 0; i < stars.size(); i++) {
            stars.get(i).dispose();
        }
    }

    public void recalcStarCount() {
        windowSpace = (long) Gdx.graphics.getHeight() * Gdx.graphics.getWidth();
        screenBordersMedian = (Gdx.graphics.getHeight() + Gdx.graphics.getWidth()) >> 1;
        starsLimit = (int) ((windowSpace / screenBordersMedian) * starCountModifier);
    }

    public void fixStarsArraySize() {
        if (stars.size() < starsLimit) {
            for (int i = 0; i < starsLimit - stars.size(); i++) {
                stars.add(Star.makeStar(starTextures));
            }
        }
        if (stars.size() > starsLimit) {
            for (int i = starsLimit - 1; i < stars.size() - 1; i++) {
                stars.get(i).lifetime = 0;
            }
        }
    }

    public void recalcNebulaCount() {
        windowSpace = (long) Gdx.graphics.getHeight() * Gdx.graphics.getWidth();
        screenBordersMedian = (Gdx.graphics.getHeight() + Gdx.graphics.getWidth()) >> 1;
        nebulaLimit = (int) ((windowSpace / screenBordersMedian) * nebulaCountModifier);
    }

    public void fixNebulaArraySize() {
        Vector2 topLeft = new Vector2(camera.position.x - camera.viewportWidth / 2, camera.position.y + camera.viewportHeight / 2);
        Vector2 bottomRight = new Vector2(camera.position.x + camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2);
        if (nebulas.size() < nebulaLimit) {
            for (int i = 0; i < nebulaLimit - nebulas.size(); i++) {
                nebulas.add(Nebula.makeNebula(topLeft, bottomRight, gradientColor1, gradientColor2, nebulaTextures.get(Utils.randInt(0, nebulaTextures.size() - 1))));
            }
        }
        if (nebulas.size() > nebulaLimit) {
            while(nebulas.size()>nebulaLimit) {
                nebulas.remove(0);
            }
        }
    }
//    public static Vector2 randScreenPoint(Camera camera){
//
//    }
}
