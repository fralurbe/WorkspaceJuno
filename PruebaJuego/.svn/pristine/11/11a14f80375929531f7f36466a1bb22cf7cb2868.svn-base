package com.fracarlu.runjumprun.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Utils
{
	public static final float margenaltura = 0.1f;
	public static final String  ficheroniveles = "niveles.txt";
	// @Comprueba si r1 está dentro de r2
	public static boolean SuperposicionRectangulos (Rectangle r1, Rectangle r2) {
		if (r1.x < r2.x + r2.width && r1.x + r1.width > r2.x &&
			r1.y < r2.y + r2.height && r1.y + r1.height > r2.y)
			return true;
		else
			return false;
	}
	 
	public static boolean puntoEnRectangulo (Rectangle r, Vector2 p) {
	
		return 	(r.x <= p.x && r.x + r.width >= p.x &&
				p.y  >= r.y && p.y < r.y + r.height);				
	}
	
	public static boolean puntoEnRectangulo (Rectangle r, float x, float y) {	
		return 	(r.x <= x && r.x + r.width >= x && 
				y >= r.y && y  < r.y + r.height);
	}
	
	public static boolean RectanguloEncimaDeOtro (Rectangle rencima, Rectangle rdebajo) {
		if ((rencima.y+rencima.height < rdebajo.y)&&
				rencima.x <= rdebajo.x && rencima.x + rencima.width >= rdebajo.x)
			return true;
		return false;
	}
	
	public static boolean RectanguloPisandoOtro (Rectangle rencima, Rectangle rdebajo) {	
		float floor_rencima  = (rencima.y + rencima.height);
		float ceil_rdebajo = rdebajo.y + rdebajo.height;
		
		if ( (floor_rencima    > ceil_rdebajo  ) &&
			 (floor_rencima    < (ceil_rdebajo  - margenaltura )))			
			return true;
		return false;
	}
	
	public static String LeerTxt() 
	{
		
		String fileContent;
		//String path = "com/fracarlu/runjumprun/assets/niveles.txt";
        //FileHandle  handle = Gdx.files.classpath(path);   
		String path = "levels.txt";
		try
		{
	        FileHandle  handle = Gdx.files.internal(path);	       	       
	        fileContent = handle.readString();
		}
		catch (Exception ex)
		{return "";}
        return fileContent;	   
	}		
}
