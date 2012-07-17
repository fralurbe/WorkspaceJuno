package com.fracarlu.runjumprun.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fracarlu.runjumprun.Asset.*;
import com.fracarlu.runjumprun.Objects.*;
import com.fracarlu.runjumprun.Tools.LevelParser;

public class WorldRenderer {
	static final float FRUSTUM_WIDTH = 10;
	static final float FRUSTUM_HEIGHT = 15;
	World world;
	OrthographicCamera cam;
	SpriteBatch batcher;
	TextureRegion fondo;
	
	public WorldRenderer (SpriteBatch batch, World mundo) {
		this.world = mundo;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		this.batcher = batch;
		this.fondo = Assets.fondogeneral;
	}

	public void render () {
		try
		{
			if (world.corredor.position.x > cam.position.x) 
			{
				cam.position.x = world.corredor.position.x;				
			}
			cam.update();
			batcher.setProjectionMatrix(cam.combined);
			renderBackground();
			renderObjectos();
		}
		catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}
	}
	
	public void renderBackground() {
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
	
	public void renderObjects() {
		try
		{
			batcher.disableBlending();
			batcher.begin();
			renderCorredor();
			renderPlatforms();
			renderObstacles();
				
			batcher.end();
		}
		catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}
	}
	
	public void renderPlatforms() {
		try
		{
			int len = world.plataformas.size();
			for (int i = 0; i < len; i++) 
			{
				TextureRegion keyFrame = Assets.platform;
				Platform plataforma = world.plataformas.get(i);
				
				batcher.draw(
						keyFrame,
						plataforma.position.x,
						plataforma.position.y,
						plataforma.TILE_WIDTH,
						plataforma.TILE_HEIGHT);				
			}
		}
		catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}		
	}
	
	public void renderObstacles()
	{				
		for (int i = 0; i < LevelParser.obstacles.length; i++)
		{
			batcher.draw(
					LevelParser.obstacles[i].getAsset(),
					LevelParser.obstacles[i].position.x,
					LevelParser.obstacles[i].position.y,
					Tile.TILE_WIDTH,
					Tile.TILE_HEIGHT);
		}		
	}
	
	
	public void renderCorredor() {
		try
		{
			TextureRegion keyFrame;
			switch (world.corredor.estado) {				
				case Runner.CORREDOR_STATE_JUMP:
					keyFrame = Assets.corredorSaltar.getKeyFrame(
							world.corredor.estadoTime,
							Animacion.ANIMACION_NONLOOPING);
					break;
				case Runner.CORREDOR_STATE_HIT:
					keyFrame = Assets.corredorHit.getKeyFrame(
							world.corredor.estadoTime,
							Animacion.ANIMACION_NONLOOPING);
					break;				
				default:
					keyFrame = Assets.corredorCorrer.getKeyFrame(
							world.corredor.estadoTime, 
							Animacion.ANIMACION_LOOPING);
			}			
			
			batcher.draw(
					keyFrame, 
					world.corredor.position.x , 
					world.corredor.position.y , 1, 1);			
		}
		catch (Exception ex)
		{
			Gdx.app.log("error", ex.toString());
		}		
	}	
}
