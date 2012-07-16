package com.fracarlu.runjumprun.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fracarlu.runjumprun.Asset.*;
import com.fracarlu.runjumprun.Objects.*;
import com.fracarlu.runjumprun.Tools.LevelParser;

public class MundoPintador {
	static final float FRUSTUM_WIDTH = 10;
	static final float FRUSTUM_HEIGHT = 15;
	Mundo mundo;
	OrthographicCamera cam;
	SpriteBatch batcher;
	TextureRegion fondo;
	
	public MundoPintador (SpriteBatch batch, Mundo mundo) {
		this.mundo = mundo;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		this.batcher = batch;
		this.fondo = Assets.fondogeneral;
	}

	public void render () {
		try
		{
			if (mundo.corredor.position.x > cam.position.x) 
			{
				cam.position.x = mundo.corredor.position.x;				
			}
			cam.update();
			batcher.setProjectionMatrix(cam.combined);
			renderFondo();
			renderObjectos();
		}
		catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}
	}
	
	public void renderFondo() {
		try
		{
			batcher.disableBlending();
			batcher.begin();
			batcher.draw(fondo, 0, 0);
			batcher.end();
		}
		catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}
	}
	
	public void renderObjectos() {
		try
		{
			batcher.disableBlending();
			batcher.begin();
			renderCorredor();
			renderPlataformas();
			renderObstaculos();
				
			batcher.end();
		}
		catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}
	}
	
	public void renderPlataformas() {
		try
		{
			int len = mundo.plataformas.size();
			for (int i = 0; i < len; i++) 
			{
				TextureRegion keyFrame = Assets.platform;
				Plataforma plataforma = mundo.plataformas.get(i);
				
				batcher.draw(
						keyFrame,
						plataforma.position.x,
						plataforma.position.y,
						Plataforma.PLATAFORMA_WIDTH,
						Plataforma.PLATAFORMA_HEIGHT);				
			}
		}
		catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}		
	}
	
	public void renderObstaculos()
	{		
		int len = LevelParser.obstaculos.length;
		for (int i = 0; i < len; i++)
		{
			batcher.draw(
					LevelParser.obstaculos[i].getAsset(),
					LevelParser.obstaculos[i].position.x,
					LevelParser.obstaculos[i].position.y,
					Tile.TILE_WIDTH,
					Tile.TILE_HEIGHT);
		}		
	}
	
	
	public void renderCorredor() {
		try
		{
			TextureRegion keyFrame;
			switch (mundo.corredor.estado) {				
				case Corredor.CORREDOR_STATE_JUMP:
					keyFrame = Assets.corredorSaltar.getKeyFrame(
							mundo.corredor.estadoTime,
							Animacion.ANIMACION_NONLOOPING);
					break;				
				default:
					keyFrame = Assets.corredorCorrer.getKeyFrame(
							mundo.corredor.estadoTime, 
							Animacion.ANIMACION_LOOPING);
			}			
			
			batcher.draw(
					keyFrame, 
					mundo.corredor.position.x , 
					mundo.corredor.position.y , 1, 1);			
		}
		catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}		
	}	
}
