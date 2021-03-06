package com.fracarlu.runjumprun.Asset;

import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fracarlu.runjumprun.Engine.TileFactory;
import com.fracarlu.runjumprun.Objects.Tile;
import com.fracarlu.runjumprun.Objects.TileType;

public class ImageLevelParser {

	public static Tile tiles[][];
	public static TileFactory tilefactory;
	
	
	public static void loadTexture (String levelfile) 
	{
		
		try
		{
			Pixmap pixmap = new Pixmap(Gdx.files.internal(levelfile));
			
			int altura = pixmap.getHeight();
			int anchura = pixmap.getWidth();			
			tilefactory = new TileFactory(); 
			tiles = new Tile[altura][anchura];
			
			java.nio.ByteBuffer bb =  ByteBuffer.allocateDirect(altura * anchura * 4);
			Pixmap.Format format = pixmap.getFormat();
 			bb = pixmap.getPixels();
			
			for (int i = 0; i < altura * anchura; i++)
			{
				int  r = bb.get() & 0xff;;
				int  g = bb.get()& 0xff;
				int  b = bb.get()& 0xff;
				int  a = bb.get()& 0xff;
				procesaSegunColor(r,  g,  b, i, anchura, altura);
			}
			
			pixmap.dispose();			
		}
		catch(Exception ex)
		{
			String error = ex.getMessage();			
		}
	}


	private static void procesaSegunColor(int r, int g, int b,int index,int anchura, int altura)
	{
		try
		{		
			int x, y;
			x = index % altura;
			y = index / anchura;
			
			

			if (r > 0 || g > 0 || b > 0)//es blanco puro
			{
				tiles[x][y] = tilefactory.createTile(x, y, TileType.NORMALPLATFORM);
			}		
			
			{
				if (r == 1)//es rojo
				{
					tiles[x][y] = tilefactory.createTile(x, y, TileType.NORMALOBSTACLE);
				}
				
				else 
					if (g == 1)//es verde
					{
						tiles[x][y] = tilefactory.createTile(x, y, TileType.SPRINGPLATFORM);
					}
					else 
						if (b == 1)//es azul
						{		
							String traza = "azul";
							traza += "azul";
						}
						else
							if (r == 0 && g == 0 && b == 0)//es negro puro
							{
								String traza = "negro";
								traza += "negro";
							}
			}
			
		}
		catch (Exception ex)
		{
			String error = ex.getMessage();		
		}
	}
}