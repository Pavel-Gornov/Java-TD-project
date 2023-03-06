package com.space_td.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameMain extends ApplicationAdapter {
	private TiledMap tmap;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	@Override
	public void create () {
		tmap = new TiledMap();
		renderer = new OrthogonalTiledMapRenderer(tmap, 1 / 16f);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 30, 20);
		camera.update();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.7f, 0.7f, 1.0f, 1);
		camera.update();
		renderer.setView(camera);
		renderer.render();
	}
	
	@Override
	public void dispose () {
	}
}
