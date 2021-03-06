package com.fracarlu.runjumprun.Tools;

import java.util.ArrayList;
import com.fracarlu.runjumprun.Engine.*;
import com.fracarlu.runjumprun.Objects.Obstacle;
import com.fracarlu.runjumprun.Objects.Obstacle;
import com.fracarlu.runjumprun.Objects.Platform;
import com.fracarlu.runjumprun.Objects.Tile;

public class LevelParser 
{
	public static final int ST_BEGIN_LEVEL = 0;
	public static final int ST_END_LEVEL = 1;
	public static final int ST_IN_LEVEL = 2;
	public static final int ST_BEGIN_TILE  = 3;
	public static final int ST_END_TILE  = 4;	
	
	public static Obstacle[] obstacles;
	public static Platform[] platforms;
	public static Tile[] tiles;
	private TileFactory tf;
	
	public static  ArrayList<String> levelsalist;
		
	public  LevelParser(String contenidoFicheroNiveles)
	{
		ParseFichero(contenidoFicheroNiveles);
		tf = new TileFactory();
	}
	
		
	// Devuelve un array de strings con los todos los niveles 		
	private void  ParseFichero(String nivel)
	{		
		char[] canivel = nivel.toCharArray();
		char[] ca_aux = new char[World.WORLD_WIDTH];
		int indexca_aux = 0;
		int estado = -1;		
		
		levelsalist = new ArrayList<String>();
		
		for (int i = 0; i < World.WORLD_WIDTH; i++)
		{		
			char c = canivel[i];
			if (c == '<')
			{
				estado = ST_BEGIN_LEVEL; 
			}
			if (c == '>')
			{
				estado = ST_END_LEVEL; 
			}
			switch(estado)
			{
				case ST_BEGIN_LEVEL:
				{									
					estado = ST_IN_LEVEL;
					ca_aux = new char[World.WORLD_WIDTH];
					indexca_aux = 0;
				}
				break;
				case ST_END_LEVEL:
				{
					ca_aux[indexca_aux] = '\0';
					levelsalist.add(new String(ca_aux));					
				}
				break;
				case ST_IN_LEVEL:
				{
					if (c != ' ')
						ca_aux[indexca_aux] = c;
					indexca_aux++;
				}
				break;
			}					
		}		
	}	  

	// <10,1,0; 20,1,0; 30,2,0; 40,1,1>
	public void ParseLevel (int numnivel)	
	{
		String nivel = levelsalist.get(numnivel).toLowerCase().trim();					
		String[] astrtiles = nivel.split("\\;");		
		
		tiles = new Tile[astrtiles.length];
		
		for (int i = 0; i < astrtiles.length ; i++)
		{			
			String[] tiledataseparado = new String[3];
			tiledataseparado = astrtiles[i].split("\\,");		
			try
			{
				int x = Integer.parseInt(tiledataseparado[0]);
				int y = Integer.parseInt(tiledataseparado[1]);
				int type = Integer.parseInt(tiledataseparado[2]);
				
				Obstacle obstaculo = new Obstacle(x, y);
				obstacles[i] = obstaculo;
				tiledataseparado = null;				
			}
			catch (Exception ex)
			{				
				ex.getMessage();				
			}			
		}
	}
		
}
