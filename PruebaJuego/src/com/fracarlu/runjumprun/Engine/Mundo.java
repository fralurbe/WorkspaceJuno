package com.fracarlu.runjumprun.Engine;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.math.Vector2;
import com.fracarlu.runjumprun.Objects.*;
import com.fracarlu.runjumprun.Tools.*;

public class Mundo {	
	public interface MundoListener 
	{
		public void saltar ();
		public void doblesalto ();
		public void chocar ();
	}
	public static final int TILE_WIDTH = 1 ;
	public static final int TILE_HEIGHT = 1 ;
	public static final int TILES_PER_SCREEN = 10 ;
	public static final int SCREENS_PER_LEVEL = 5;
	public static final int MUNDO_WIDTH = TILES_PER_SCREEN * SCREENS_PER_LEVEL;
	public static final int MUNDO_HEIGHT = 15;
	public static final int MUNDO_END = MUNDO_WIDTH-TILES_PER_SCREEN/2;
	public static final int MUNDO_STATE_RUNNING = 0;
	public static final int MUNDO_STATE_NEXT_LEVEL = 1;
	public static final int MUNDO_STATE_GAME_OVER = 2;
	public static final Vector2 gravedad = new Vector2(0, -12);	
	
	public Corredor corredor;	
	
	public final ArrayList<Plataforma> plataformas;
	//public final ArrayList<ObstaculoNormal> obstaculos;
	public final Random rand;
	public final MundoListener listener;	

	public float distanciaConseguida;
	public int puntuacion;
	public int estado;
	
	public Mundo (MundoListener listener) {
		this.corredor = new Corredor(1, 5);
		this.plataformas = new ArrayList<Plataforma>();
		//this.obstaculos =  new ArrayList<ObstaculoNormal>();
				
		this.listener = listener;
		rand = new Random();
		GenerarNivel();

		this.distanciaConseguida = 0;
		this.puntuacion = 0;
		this.estado = MUNDO_STATE_RUNNING;

	}	
	
	private void GenerarNivel()
	{		
		GenerarPlataformas();
	}
	
	private void GenerarPlataformas()
	{		
		for (int i = 0; i < MUNDO_WIDTH ; i++)		
		{
			Plataforma plataforma = new Plataforma(i * TILE_WIDTH, 0);
			plataformas.add(plataforma);			
		}
	}
	
	public void update (float deltaTime, float accelX,float accelY)
	{
		updateCorredor (deltaTime, accelX, accelY); 		
	}
	
	private void updateCorredor (float deltaTime, float accelX, float accelY) 
	{
		if (corredor.estado != Corredor.CORREDOR_STATE_UPON_PLATFORM)
		{
			for (int i = 0; i < plataformas.size(); i++)
			{
				if (Utils.RectanguloPisandoOtro( corredor.bounds, plataformas.get(i).bounds))
				{
					corredor.velocity.y = 0;
					corredor.estado = Corredor.CORREDOR_STATE_UPON_PLATFORM;
					break;
				}			
			}
		}
		
		if (corredor.estado == Corredor.CORREDOR_STATE_JUMP)
		{
			corredor.velocity.y = accelY;
			corredor.estado = Corredor.CORREDOR_STATE_RUN;
		}
		
		if (checkHitObstaculo() != -1)
		{
			corredor.estado = Corredor.CORREDOR_STATE_HIT;
			estado = MUNDO_STATE_GAME_OVER;
			corredor.velocity.x = -5f;
			corredor.velocity.y = 5f;
		}
		
		corredor.update(deltaTime);
		if (corredor.position.x >= MUNDO_END )
		{
			estado = MUNDO_STATE_NEXT_LEVEL;
		}
		distanciaConseguida = Math.max(corredor.position.x, distanciaConseguida);
	}
	
	// @Devuelve el numero de obstaculo con el que se ha chocado el corredor
	// @Devuelve -1 si no se ha chocado	
	private int checkHitObstaculo()
	{
		int initpostocheck = (int)Math.round(corredor.position.x);
		for (int i =  0; i < LevelParser. obstaculos.length; i++ )
		{
			if (corredor.bounds.overlaps(LevelParser. obstaculos[i].bounds))
			{
				return i;
			}
		}
		return -1;
	}
	
	private void checkGameOver()
	{
		if (distanciaConseguida - 7.5f > corredor.position.x) 
		{
			estado = MUNDO_STATE_GAME_OVER;
		}
	}		
}
