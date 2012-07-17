package com.fracarlu.runjumprun.Engine;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.math.Vector2;
import com.fracarlu.runjumprun.Objects.*;
import com.fracarlu.runjumprun.Tools.*;

public class World 
{	
	public interface WorldListener 
	{
		public void saltar ();
		public void doblesalto ();
		public void chocar ();
	}
	
	public static final int TILE_WIDTH = 1 ;
	public static final int TILE_HEIGHT = 1 ;
	public static final int TILES_PER_SCREEN = 10 ;
	public static final int SCREENS_PER_LEVEL = 5;
	public static final int WORLD_WIDTH = TILES_PER_SCREEN * SCREENS_PER_LEVEL;
	public static final int WORLD_HEIGHT = 15;
	public static final int WORLD_END = WORLD_WIDTH - TILES_PER_SCREEN/2;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	
	public static final Vector2 gravedad = new Vector2(0, -10);	
	
	public Runner corredor;	
	
	public final ArrayList<Platform> plataformas;
	// public final ArrayList<ObstaculoNormal> obstaculos;
	public final Random rand;
	public final WorldListener listener;	

	public float distanciaConseguida;
	public int puntuacion;
	public int estado;
	
	
	public World (WorldListener listener) {
		this.corredor = new Runner(1, 5);
		this.plataformas = new ArrayList<Platform>();
		//this.obstaculos =  new ArrayList<ObstaculoNormal>();
				
		this.listener = listener;
		rand = new Random();
		GenerarNivel();

		this.distanciaConseguida = 0;
		this.puntuacion = 0;
		this.estado = WORLD_STATE_RUNNING;

	}	
	
	private void GenerarNivel()
	{		
		GenerarPlataformas();
	}
	
	private void GenerarPlataformas()
	{		
		for (int i = 0; i < WORLD_WIDTH ; i++)		
		{
			Platform plataforma = new Platform(i * TILE_WIDTH, 0, TileTypes.NORMALOBSTACLE);
			plataformas.add(plataforma);			
		}
	}
	
	public void update (float deltaTime, float accelX,float accelY)
	{
		updateCorredor (deltaTime, accelX, accelY); 		
	}
	
	private void updateCorredor (float deltaTime, float accelX, float accelY) 
	{
		if (corredor.estado != Runner.CORREDOR_STATE_UPON_PLATFORM)
		{
			if (corredor.estado != Runner.CORREDOR_STATE_HIT)
			{
				int initpostocheck = (int)Math.round(corredor.position.x);
				if (initpostocheck > 1)
				{
					for (int i = 0; i < plataformas.size(); i++)
					{
						if (Utils.RectanguloPisandoOtro( corredor.bounds, plataformas.get(i).bounds))
						{
							corredor.velocity.y = 0;
							corredor.estado = Runner.CORREDOR_STATE_UPON_PLATFORM;
							break;
						}			
					}
				}
			}
		}
		
		if (corredor.estado == Runner.CORREDOR_STATE_JUMP)
		{
			corredor.velocity.y = accelY;
			corredor.estado = Runner.CORREDOR_STATE_RUN;
		}
		
		if (checkHitObstaculo() != -1)
		{
			corredor.estado = Runner.CORREDOR_STATE_HIT;			
			corredor.velocity.x = -5f;
			corredor.velocity.y = 5f;			
		}
		
		corredor.update(deltaTime);
		if (corredor.position.x >= WORLD_END )
		{
			estado = WORLD_STATE_NEXT_LEVEL;
		}
		distanciaConseguida = Math.max(corredor.position.x, distanciaConseguida);
	}
	
	// @Devuelve el numero de obstaculo con el que se ha chocado el corredor
	// @Devuelve -1 si no se ha chocado	
	private int checkHitObstaculo()
	{
		int initpostocheck = (int)Math.round(corredor.position.x);
		if (initpostocheck > 1)
		{
			for (int i =  initpostocheck - 1 ; i < LevelParser.obstacles.length; i++ )
			{
				if (corredor.bounds.overlaps(LevelParser.obstacles[i].bounds))
				{
					return i;
				}
			}
		}
		return -1;
	}
	
	private void checkGameOver()
	{
		if (distanciaConseguida - 7.5f > corredor.position.x) 
		{
			estado = WORLD_STATE_GAME_OVER;
		}
	}
	
}
