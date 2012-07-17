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
		Texture text;
		text = new Texture(Gdx.files.internal(levelfile));
		Pixmap pixmap = new Pixmap(Gdx.files.internal(levelfile));
		
		int altura = pixmap.getHeight();
		int anchura = pixmap.getWidth();
		tiles = new Tile[altura][anchura];
		
		
		for (int y = 0; y < pixmap.getHeight(); y++) { 
            for (int x = 0; x < pixmap.getWidth(); x++) {
            	tiles[x][y] = null;
            	procesaSegunColor(pixmap.getPixel(x, y),x,y); 
            }
		}
		pixmap.dispose();    
	}
	
	private static void procesaSegunColor(int pixel,int x, int y)
	{
		long mask1 = (long) pixel & 0xFFFFFFFFL;
		float r = (mask1 & 0xFF000000) / 255.0f;
		float g = (mask1 & 0x00FF0000) / 255.0f;
		float b = (mask1 & 0x0000FF00) / 255.0f;
		float a = (mask1 & 0x000000FF);
		if (a < 1.0)
		{
			if (r == 1.0f && g == 1.0f && b == 1.0f)
			{
				tiles[x][y] = tilefactory.createTile(x, y, TileType.NORMALPLATFORM);
			}		
			else
			{
				if (r == 1.0f)
				{
					tiles[x][y] = tilefactory.createTile(x, y, TileType.NORMALOBSTACLE);
				}
				
				if (g == 1.0f)
				{
					tiles[x][y] = tilefactory.createTile(x, y, TileType.SPRINGPLATFORM);
				}
			}
		}					
	}
	
	public static void load () 
	{		
		spritesheet = loadTexture("spritesheet512x512.png");
		fondogeneral = new TextureRegion(spritesheet, 0, 64, 320, 480);
	}

}
/*
 Color color = new Color(0, 0, 0, 0);
		// for (int y = 0; y < pixmap.getHeight(); y++) {
		// for (int x = 0; x < pixmap.getWidth(); x++) {
		// // int color = array[x + y * pixmap2.getWidth()];
		// // long mask1 = (long) color & 0xFFFFFFFFL;
		// // float r = (mask1 & 0xFF000000) / 255.0f;
		// // float g = (mask1 & 0x00FF0000) / 255.0f;
		// // float b = (mask1 & 0x0000FF00) / 255.0f;
		// // float a = (mask1 & 0x000000FF);
 * */
/*
 public void create () {
                // Create an empty dynamic pixmap
                pixmap = new Pixmap(800, 480, Pixmap.Format.RGBA8888); // Pixmap.Format.RGBA8888);

                // Create a texture to contain the pixmap
                texture = new Texture(1024, 1024, Pixmap.Format.RGBA8888); // Pixmap.Format.RGBA8888);
                texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
                texture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);

                pixmap.setColor(1.0f, 0.0f, 0.0f, 1.0f); // Red
                pixmap.drawLine(0, 0, 100, 100);

                pixmap.setColor(0.0f, 0.0f, 1.0f, 1.0f); // Blue
                pixmap.drawLine(100, 100, 200, 0);

                pixmap.setColor(0.0f, 1.0f, 0.0f, 1.0f); // Green
                pixmap.drawLine(100, 0, 100, 100);

                pixmap.setColor(1.0f, 1.0f, 1.0f, 1.0f); // White
                pixmap.drawCircle(400, 300, 100);

                // Blit the composited overlay to a texture
                texture.draw(pixmap, 0, 0);
                region = new TextureRegion(texture, 0, 0, 800, 480);
                batch = new SpriteBatch();

                Pixmap pixmap = new Pixmap(512, 1024, Pixmap.Format.RGBA8888);
                for (int y = 0; y < pixmap.getHeight(); y++) { // 1024
                        for (int x = 0; x < pixmap.getWidth(); x++) { // 512
                                pixmap.getPixel(x, y);
                        }
                }
                pixmap.dispose();
        }

 * */
 */