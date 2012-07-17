package com.fracarlu.runjumprun.Screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.math.Rectangle;
import com.fracarlu.runjumprun.Asset.Assets;
import com.fracarlu.runjumprun.Engine.Config;
import com.fracarlu.runjumprun.Engine.Juego;
import com.fracarlu.runjumprun.Engine.World;
import com.fracarlu.runjumprun.Engine.World.WorldListener;
import com.fracarlu.runjumprun.Engine.WorldRenderer;
import com.fracarlu.runjumprun.Tools.*;

public class JuegoPantalla extends Pantalla
{
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	private int estado;
	private Rectangle pauseBounds;
	private Rectangle resumeBounds;
	private Rectangle quitBounds;

	private int lastScore;
	private String scoreString;

	World mundo;
	WorldListener mundoListener;
	WorldRenderer pintador;

	public JuegoPantalla(Juego juego)
	{
		super(juego);
		mundoListener = new WorldListener()
		{
			@Override
			public void saltar()
			{
				Assets.playSound(Assets.jumpSound);
			}

			@Override
			public void doblesalto()
			{
				Assets.playSound(Assets.highJumpSound);
			}

			@Override
			public void chocar()
			{
				Assets.playSound(Assets.hitSound);
			}
		};
		mundo = new World(mundoListener);
		pintador = new WorldRenderer(batcher, mundo);

		pauseBounds = new Rectangle(Config.anchoTotal - Config.anchoBoton, 0,
				Config.anchoBoton, Config.altoBoton);

		resumeBounds = new Rectangle(Config.anchoTotal / 2 - Config.anchoBoton / 2,
				Config.altoTotal / 2 + Config.altoBoton, 
				Config.anchoBoton,
				Config.altoBoton);

		quitBounds = new Rectangle(Config.anchoTotal / 2 - Config.anchoBoton / 2,
				Config.altoTotal / 2 - Config.altoBoton, 
				Config.anchoBoton,
				Config.altoBoton);

		lastScore = 0;
		scoreString = "SCORE: 0";
	}

	@Override
	public void update(float deltaTime)
	{
		if (deltaTime > 0.1f)
			deltaTime = 0.1f;

		switch (estado)
		{
			case GAME_READY:
				updateReady();
				break;
			case GAME_RUNNING:
				updateRunning(deltaTime);
				break;
			case GAME_PAUSED:
				updatePaused();
				break;
			case GAME_LEVEL_END:
				updateLevelEnd();
				break;
			case GAME_OVER:
				updateGameOver();
				break;
		}
	}

	private void updateReady()
	{
		if (Gdx.input.justTouched())
		{
			estado = GAME_RUNNING;
		}
	}

	private void updatePaused()
	{
		if (Gdx.input.justTouched())
		{
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (Utils.puntoEnRectangulo(resumeBounds, touchPoint.x,
					touchPoint.y))
			{
				Assets.playSound(Assets.clickSound);
				estado = GAME_RUNNING;
				return;
			}

			if (Utils.puntoEnRectangulo(quitBounds, touchPoint.x,
					touchPoint.y))
			{
				Assets.playSound(Assets.clickSound);
				juego.ponPantalla(new MenuPantalla(juego));
				return;
			}
		}
	}

	private void updateLevelEnd()
	{
		if (Gdx.input.justTouched())
		{
			mundo = new World(mundoListener);
			pintador = new WorldRenderer(batcher, mundo);
			mundo.puntuacion = lastScore;
			estado = GAME_READY;
		}
	}

	private void updateGameOver()
	{
		if (Gdx.input.justTouched())
		{
			juego.ponPantalla(new MenuPantalla(juego));
		}
	}

	private void updateRunning(float deltaTime)
	{
		if (Gdx.input.justTouched())
		{
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			mundo.update(deltaTime, mundo.gravedad.x,mundo.gravedad.y);

			if (Utils.puntoEnRectangulo(pauseBounds, touchPoint.x,
					touchPoint.y))
			{
				Assets.playSound(Assets.clickSound);
				estado = GAME_PAUSED;
				return;
			}
		}

		if (Gdx.app.getType() == Application.ApplicationType.Android)
		{
			mundo.update(deltaTime, Gdx.input.getAccelerometerX(),Gdx.input.getAccelerometerY());
		} 
		else
		{
			float accelx = 0;
			float accely = 0;
			//if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT))
			//	accelx = 2f;			
			if (Gdx.input.isKeyPressed(Keys.DPAD_UP))
			{
				//accely = Corredor.CORREDOR_JUMP_IMPULSE;
				//mundo.corredor.estado = mundo.corredor.CORREDOR_STATE_JUMP;
				mundo.corredor.Jump();
			}
			
			mundo.update(deltaTime, accelx,accely);
		}
		if (mundo.puntuacion != lastScore)
		{
			lastScore = mundo.puntuacion;
			scoreString = "SCORE: " + lastScore;
		}
		if (mundo.estado == World.WORLD_STATE_NEXT_LEVEL)
		{
			estado = GAME_LEVEL_END;
		}
		if (mundo.estado == World.WORLD_STATE_GAME_OVER)
		{
			estado = GAME_OVER;
			/*
			 * if (lastScore >= Settings.highscores[4]) scoreString =
			 * "NEW HIGHSCORE: " + lastScore; else scoreString = "SCORE: " +
			 * lastScore; Settings.addScore(lastScore); Settings.save();
			 */
		}

	}

	@Override
	public void render(float deltaTime)
	{
		GLCommon gl = Gdx.gl;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		pintador.render();

		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		batcher.enableBlending();
		batcher.begin();
		switch (estado)
		{
			case GAME_READY:
				renderReady();
				break;
			case GAME_RUNNING:
				renderRunning();
				break;
			case GAME_PAUSED:
				renderPaused();
				break;
			case GAME_LEVEL_END:
				renderLevelEnd();
				break;
			case GAME_OVER:
				renderGameOver();
				break;
		}
		batcher.end();

	}

	private void renderReady()
	{
		batcher.draw(Assets.ready, Config.anchoTotal / 2 - Config.anchoBoton / 2,
				Config.altoTotal / 2 - Config.altoBoton / 2, Config.anchoBoton,
				Config.altoBoton);
	}

	private void renderRunning()
	{
		batcher.draw(Assets.pause, pauseBounds.x, pauseBounds.y,
				pauseBounds.width, pauseBounds.height);

		Assets.fuente.draw(batcher, scoreString, 16, Config.altoTotal - 16);
	}

	private void renderPaused()
	{
		batcher.draw(Assets.quit, quitBounds.x, quitBounds.y, quitBounds.width,
				quitBounds.height);

		batcher.draw(Assets.resume, resumeBounds.x, resumeBounds.y,
				resumeBounds.width, resumeBounds.height);

		Assets.fuente.draw(batcher, scoreString, 16, Config.altoTotal - 16);
	}

	private void renderLevelEnd()
	{
		String topText = "THE END";
		String bottomText = "OF LEVEL";
		float topWidth = Assets.fuente.getBounds(topText).width;
		float bottomWidth = Assets.fuente.getBounds(bottomText).width;
		Assets.fuente.draw(batcher, topText,
				Config.anchoTotal / 2 - topWidth / 2, Config.altoTotal - 40);
		Assets.fuente.draw(batcher, bottomText, 
				Config.anchoTotal / 2 - bottomWidth / 2, 40);
	}

	private void renderGameOver()
	{
		batcher.draw(
				Assets.gameover,
				Config.anchoTotal / 2 - Config.anchoBoton / 2, 
				Config.altoTotal / 2 - Config.altoBoton / 2,
				Config.anchoBoton, 
				Config.altoBoton);
		
		float scoreWidth = Assets.fuente.getBounds(scoreString).width;
		Assets.fuente.draw(
				batcher, 
				scoreString, 
				Config.anchoTotal / 2 - scoreWidth / 2, 
				Config.altoTotal - 20);
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

}
