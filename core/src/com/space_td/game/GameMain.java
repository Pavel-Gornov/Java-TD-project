package com.space_td.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Window;


public class GameMain extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture planet;
    private Texture TEMP;

    //Screen = Scr
    public float ScrWidth;
    public float ScrHeight;
    //Мы размещаем всё в пределах 400х300 экрана и умножаем на ssm{x или y}.
    public float dfWinSizeX=640;
    public float dfWinSizeY=480;
    //модификатор размера окна.
    public float ssmX;
    public float ssmY;
    OrthographicCamera camera = new OrthographicCamera();


    @Override
    public void create() {

        camera.setToOrtho(false, ScrWidth, ScrHeight);
        planet = new Texture("planet.png");
        //замени потом на плэйсхолдер. только прошу, не из пустоти иди (VoidiGo)
        TEMP = new Texture("planet.png");
        batch = new SpriteBatch();
        ScrHeight=Gdx.graphics.getHeight();
        ScrWidth=Gdx.graphics.getWidth();
        calcSSM();
    }


    @Override
    public void render() {

        camera.update();

        //batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
//        batch.draw(planet, 200*ssmX, 150*ssmY);
//        batch.draw(TEMP, 16*ssmX, 128*ssmY);
        batch.draw(planet, Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f);
        System.out.println("W: "+Gdx.graphics.getWidth()+" H: "+Gdx.graphics.getHeight());
        batch.end();
    }
    @Override
    public void resize(int width, int height){
//        ScrWidth=width;
//        ScrHeight=height;
        ScrHeight=Gdx.graphics.getHeight();
        ScrWidth=Gdx.graphics.getWidth();
        calcSSM();
    }

    @Override
    public void dispose() {
        batch.dispose();
        planet.dispose();
        TEMP.dispose();
    }
    public void calcSSM(){
        ssmX=ScrWidth/dfWinSizeX;
        ssmY=ScrHeight/dfWinSizeY;

    }

}
