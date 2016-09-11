package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	public static final float STEP = 1 / 60f;
	private float accum;

	public static final String title = "Block Wow Bunny!";
	public static final int V_WIDTH = 320;
	public static final int V_HEIGHT = 240;
	public static final int SCALE = 2 ;

	private SpriteBatch sb;
	Texture img;
	private OrthographicCamera cam, hudCam;
	private GameStateManager gsm;

	@Override
	public void create () {
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		//img = new Texture("badlogic.jpg");
		gsm = new GameStateManager(this);
	}

	@Override
	public void render () {
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		sb.draw(img, 0, 0);
		sb.end();*/

		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP) {
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
		}
	}
	
	@Override
	public void dispose () {
		sb.dispose();
		img.dispose();
	}

	public SpriteBatch getSpriteBatch() {return sb; }
	public OrthographicCamera getCamera() { return cam; }
	public OrthographicCamera getHUDCamera() { return hudCam; }
}
