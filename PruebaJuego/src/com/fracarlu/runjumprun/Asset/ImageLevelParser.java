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
			Pixmap.Format format = pixmap.getFormat();
			if (format == Pixmap.Format.RGBA8888)
			{
				tiles = new Tile[altura][anchura];
						
				for (int x = 0; x < anchura; x++)
				{
					for (int y = 0; y < altura ; y++) 				             
		            {	            	
		            	procesaSegunColor(pixmap.getPixel(x, y),x,y); 
		            }
				}
				pixmap.dispose();
			}
		}
		catch(Exception ex)
		{
			String error = ex.getMessage();			
		}
	}

	private static void procesaSegunColor(int pixel,int x, int y)
	{
		try
		{
			long mask1 = (long) pixel & 0xFFFFFFFFL;
			float r = (mask1 & 0xFF000000) / 255.0f;
			float g = (mask1 & 0x00FF0000) / 255.0f;
			float b = (mask1 & 0x0000FF00) / 255.0f;
			float a = (mask1 & 0x000000FF) / 255.0f;
			if (a == 1.0)//no es transparente
			{
				if (r != 0f && g != 0f && b != 0f)//no es negro
				{
					if (r == 1.0f && g == 1.0f && b == 1.0f)//es blanco puro
					{
						tiles[x][y] = tilefactory.createTile(x, y, TileType.NORMALPLATFORM);
					}		
					else
					{
						if (r == 1.0f)//es rojo
						{
							tiles[x][y] = tilefactory.createTile(x, y, TileType.NORMALOBSTACLE);
						}
						
						if (g == 1.0f)//es verde
						{
							tiles[x][y] = tilefactory.createTile(x, y, TileType.SPRINGPLATFORM);
						}
					}
				}
			}
		}
		catch (Exception ex)
		{
			String error = ex.getMessage();		
		}
	}
}