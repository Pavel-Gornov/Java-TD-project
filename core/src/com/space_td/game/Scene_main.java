package com.space_td.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import org.ietf.jgss.GSSContext;

import java.math.RoundingMode;
import java.util.ArrayList;

public class Scene_main implements Screen, InputProcessor {
    private SpriteBatch batch;
    private Planet planet;
    public TextureRegion planetTexture;
    public Vector2 planetPos;
    public Vector2 planetScale;
    private ArrayList<TextureRegion> starTextures;
    private ArrayList<TextureRegion> enemyTextures;
    private ArrayList<TextureRegion> nebulaTextures;
    public float ScrWidth;
    public float ScrHeight;
    public OrthographicCamera camera;
    public Stage stage;
    public ExtendViewport viewport;
    InputProc inputProc;

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
    public Color gradientColor2 = new Color(1, 0, 0.83f, 0.7f);
    public ShapeRenderer shapeRenderer;
    float counter;
    public Mouse mouse;
    TextureRegion mouseTexture;
    public float mouseDamage;
    public float mouseAttacksPerSecond;

    public Table table_topRight;
    Label infoData;
    BitmapFont font_default = new BitmapFont();

    @Override
    public void show() {

        System.out.println("Shown scene: main");
        if (mouseDamage==0){
            mouseDamage=5;
        }
        if (mouseAttacksPerSecond==0){
            mouseAttacksPerSecond=5;
        }
        mouseTexture=new TextureRegion(new Texture("mouseSphere.png"));
        mouse=new Mouse(new Vector2(0, 0), 0, new Vector2(8, 8), new Vector2(1, 1), new Vector2(0,0), mouseTexture, false, false, 16, mouseDamage, mouseAttacksPerSecond);
        inputProc = new InputProc();
//        Gdx.input.setInputProcessor(stage);
        data.init();
//        gradientColor1 = Utils.randColor();
//        gradientColor2 = Utils.randColor();
        ScrHeight = Gdx.graphics.getHeight();
        ScrWidth = Gdx.graphics.getWidth();
        viewport=new ExtendViewport(ScrWidth, ScrHeight);
        camera = new OrthographicCamera(ScrWidth, ScrHeight);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);


        load_textures();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        recalcStarCount();
        fixStarsArraySize();
        recalcNebulaCount();
        fixNebulaArraySize();

//        ArrayList<Vector2> points = new ArrayList<>();
//        for (int j = 0; j < 5; j++) {
//
//            points.add(new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), Utils.randFloat(0, Gdx.graphics.getHeight())));
//            for (int i = 0; i < 100; i++) {
//                Vector2 vec = new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), Utils.randFloat(0, Gdx.graphics.getHeight()));
//                while ((points.size() != 0) & (points.get(points.size() - 1).dst(vec) < 10)) {
//                    vec = new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), Utils.randFloat(0, Gdx.graphics.getHeight()));
//                }
//                points.add(vec);
//            }
//            enemies.add(new Enemy(new Vector2(10, 10), 0, new Vector2(2, 2), enemyTextures.get(0), false, false, 1, 1, 1, 50, 1, Enemy.EnemyTypes.BASIC, new ArrayList<>(points), new Vector2(1, 1)));
//            points.clear();
//        }

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        stage = new Stage(viewport, batch);

//        dg = new DummyGameObject(new Vector2(ScrWidth / 2, ScrHeight / 2), 0, new Vector2(1, 1), new TextureRegion(new Texture("planet.png")), false, false);
//        dg.scale.set(new Vector2(4, 4));
        planetScale = new Vector2(3, 3);
        planetPos = new Vector2(ScrWidth / 2, ScrHeight / 2);
        planet = new Planet(planetPos, 0, planetScale, planetTexture, false, false, 10000, 0);

        camera.update();

        table_topRight=new Table();
        table_topRight.top().right();
        infoData = new Label("This. Is. LABEL!", new Label.LabelStyle(font_default, new Color(1, 1, 1, 1)));
        infoData.setSize(256, 128);

        table_topRight.add(infoData).padTop(10).padRight(10);
        infoData.setPosition(0, ScrHeight-80);
        stage.addActor(table_topRight);
        stage.addActor(infoData);
    }
    protected void load_textures() {
        planetTexture = new TextureRegion(new Texture("planet.png"));
        enemyTextures = Utils.splitRegion(new Texture("enemies.png"), 16, 16);
        starTextures = Utils.splitRegion(new Texture("stars.png"), 8, 8);
        nebulaTextures = Utils.splitRegion(new Texture("nebulas_white.png"), 64, 64);
    }

    @Override
    public void render(float delta) {

        Gdx.input.setInputProcessor(this);
        infoData.setText("Planet hp: "+Utils.roundFloat(planet.hp, 2, RoundingMode.DOWN)+"\nGame difficulty: "+Utils.roundFloat(data.gameDifficulty, 2, RoundingMode.DOWN));
        batch.begin();
        shapeRenderer.begin();
        //System.out.println("Render called: main scene");
        counter += Utils.getDTime();

        ScreenUtils.clear(0, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);
        Vector2 worldMousePos = new Vector2(mousePos.x, mousePos.y);
//        System.out.println("Mouse position: " + mousePos.x + " " + mousePos.y);
//        dg.rotation += (Gdx.graphics.getDeltaTime()*data.gameSpeed) * 2f;



        for (int i = 0; i < nebulas.size(); i++) {
            nebulas.get(i).draw(batch);
        }
        for (int i = 0; i < stars.size(); i++) {
            if (stars.get(i).isDestroyed) stars.set(i, Star.makeStar(starTextures));
            stars.get(i).draw(batch);
            GameMain.debugData = "Stars count: " + stars.size() + "\n";
        }
        planet.draw(batch, enemies);
        //TODO: не трогать, рендер коллайдеров
//        shapeRenderer.circle(planet.collider.x, planet.collider.y, planet.collider.radius);
//        shapeRenderer.point(planet.collider.x, planet.collider.y, 0);

        for (int i = 0; i < enemies.size(); i++) {

            enemies.get(i).draw(batch);
            //TODO: не трогать. коллайдеры!
//            enemies.get(i).renderColliders(shapeRenderer);
            if (enemies.get(i).isDestroyed) {
                enemies.remove(i);
            }
//            if (enemies.get(i).showColliders)


        }
        if (enemies.size() < (7 * (data.gameDifficulty / 100)) || (enemies.size() < 7 & data.gameDifficulty < 100)) {
            enemies.add(Enemy.spawnEnemy(planet, enemyTextures));


        }
        mouse.draw(batch, enemies);
//        dg.draw(batch);
        //TODO: не удалять. рендер коллайдеров
//        shapeRenderer.setColor(0, 0.5f, 1, 1);
//
//        shapeRenderer.point(planet.position.x, planet.position.y, 0);
//        shapeRenderer.setColor(1, 0, 0, 1);

        stage.act(delta);
        if (batch.isDrawing()) batch.end();

        stage.draw();
        if (shapeRenderer.isDrawing()) shapeRenderer.end();

        camera.update();
        GameMain.debugData += "\nFPS: " + (int) (1 / Utils.getDTime());


        if (counter >= 0.3 & data.partyMode) {
            gradientColor1 = Utils.randColor();
            gradientColor2 = Utils.randColor();

//            if (gradientColor1.r > 1) gradientColor1.r -= Math.random();
//            else gradientColor1.r += Math.random();
//            if (gradientColor1.g > 1) gradientColor1.r -= Math.random();
//            else gradientColor1.g += Math.random();
//            if (gradientColor1.b > 1) gradientColor1.r -= Math.random();
//            else gradientColor1.b += Math.random();
//            if (gradientColor2.r > 1) gradientColor1.r -= Math.random();
//            else gradientColor2.r += Math.random();
//            if (gradientColor2.g > 1) gradientColor1.r -= Math.random();
//            else gradientColor2.g += Math.random();
//            if (gradientColor2.b > 1) gradientColor1.r -= Math.random();
//            else gradientColor2.b += Math.random();

            counter = 0;
            for (int i = 0; i < nebulas.size(); i++) {
                Vector2 topLeft = new Vector2(camera.position.x - camera.viewportWidth / 2, camera.position.y + camera.viewportHeight / 2);
                Vector2 bottomRight = new Vector2(camera.position.x + camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2);

                nebulas.get(i).updateColor(topLeft, bottomRight, gradientColor1, gradientColor2);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
        viewport.setMinWorldWidth(width);
        viewport.setMinWorldHeight(height);
        stage.setViewport(viewport);
        recalcStarCount();
        fixStarsArraySize();
        recalcNebulaCount();
        fixNebulaArraySize();
        planetPos = new Vector2(ScrWidth / 2, ScrHeight / 2);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        planet.dispose();
        dg.dispose();
        for (int i = 0; i < stars.size(); i++) {
            stars.get(i).dispose();
        }
        shapeRenderer.dispose();
        stage.dispose();
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
            while (nebulas.size() > nebulaLimit) {
                nebulas.remove(0);
            }
        }
    }

    public void makeTestEnemies() {
        ArrayList<Vector2> points = new ArrayList<>();
        for (int j = 0; j < 5; j++) {

            points.add(new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), Utils.randFloat(0, Gdx.graphics.getHeight())));
            for (int i = 0; i < 100; i++) {
                Vector2 vec = new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), Utils.randFloat(0, Gdx.graphics.getHeight()));
                while ((points.size() != 0) & (points.get(points.size() - 1).dst(vec) < 10)) {
                    vec = new Vector2(Utils.randFloat(0, Gdx.graphics.getWidth()), Utils.randFloat(0, Gdx.graphics.getHeight()));
                }
                points.add(vec);
            }
            enemies.add(new Enemy(new Vector2(10, 10), 0, new Vector2(2, 2), enemyTextures.get(0), false, false, 1, 1, 1, 50, 1, Enemy.EnemyTypes.BASIC, new ArrayList<>(points), new Vector2(1, 1)));
            points.clear();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        data.mousePos=new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println("touch down");

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        data.mousePos=new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
