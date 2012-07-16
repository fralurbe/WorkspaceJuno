package com.fracarlu.runjumprun.Screens;

import com.fracarlu.runjumprun.Engine.*;
import com.fracarlu.runjumprun.Asset.*;
import com.fracarlu.runjumprun.Tools.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MenuPantalla extends Pantalla{	
	Rectangle playBounds;
	Rectangle soundBounds;    
    Rectangle highscoresBounds;
    Rectangle helpBounds;    
    
    public MenuPantalla(Juego juego)
    {
    	super(juego);    	
    	playBounds = new Rectangle (
	    		Config.centroanchoTotal - Config.anchoBoton / 2,
	    		64,
	    		Config.anchoBoton,
	    		Config.altoBoton);
        soundBounds = new Rectangle (
        		Config.centroanchoTotal - Config.anchoBoton / 2,
        		164,
        		Config.anchoBoton,
        		Config.altoBoton);
        highscoresBounds = new Rectangle (
        		Config.centroanchoTotal - Config.anchoBoton / 2,
        		264,
        		Config.anchoBoton,
        		Config.altoBoton);
        helpBounds = new Rectangle (
        		Config.centroanchoTotal - Config.anchoBoton / 2,
        		364,
        		Config.anchoBoton,
        		Config.altoBoton);        
    }

	@Override
	public void update(float deltaTime) 
	{
		try
		{
			if (Gdx.input.justTouched()) {
				guiCam.unproject(
						touchPoint.set(
								Gdx.input.getX(), Gdx.input.getY(), 0));	
				ActuaSegunPulsado();
			}		
		}
		catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}
	}

	@Override
	public void render(float deltaTime) {
		try
		{
			GLCommon gl = Gdx.gl;
			gl.glClearColor(1, 0, 0, 1);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			guiCam.update();
			batcher.setProjectionMatrix(guiCam.combined);
			
			batcher.disableBlending();
			batcher.begin();
			batcher.draw(Assets.fondogeneral, 0, 0, Config.anchoTotal, Config.altoTotal);
			batcher.end();
	
			batcher.enableBlending();
			batcher.begin();
			batcher.draw(Assets.play,playBounds.x,playBounds.y);
			batcher.draw(Assets.sound,soundBounds.x,soundBounds.y);
			batcher.draw(Assets.highscores,highscoresBounds.x,highscoresBounds.y);
			batcher.draw(Assets.help,helpBounds.x,helpBounds.y);
			batcher.end();
		
		}
		catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}
	}

	@Override
	public void pause() {
			
	}

	@Override
	public void resume() {
	
	}

	@Override
	public void dispose() {	
	}
	
	private void ActuaSegunPulsado()
	{
		if (Utils.puntoEnRectangulo(playBounds, touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.clickSound);
			juego.ponPantalla(new JuegoPantalla(juego));			
			return;
		}
		if (Utils.puntoEnRectangulo(highscoresBounds, touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.clickSound);
			juego.ponPantalla(new HighScoresPantalla(juego));
			return;
		}
		if (Utils.puntoEnRectangulo(helpBounds, touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.clickSound);
			juego.ponPantalla(new AyudaPantalla(juego));
			return;
		}
		if (Utils.puntoEnRectangulo(soundBounds, touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.clickSound);
			Config.sonidoActivado = !Config.sonidoActivado;
			if (Config.sonidoActivado)
				Assets.music.play();
			else
				Assets.music.pause();
		}
	}
}
