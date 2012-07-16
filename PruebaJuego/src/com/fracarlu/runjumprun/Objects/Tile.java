package com.fracarlu.runjumprun.Objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fracarlu.runjumprun.Asset.Assets;

public class Tile extends GameObject 
{
	public static final float TILE_WIDTH = 1f;
	public static final float TILE_HEIGHT = 1f;
	public static final int TILE_TYPE = 0;	
		
	public  int type = TILE_TYPE;		

	public Tile(float x, float y, float width, float height) {
		super(x, y, width, height);		
 	}
	
	public Tile(float x, float y, int type) {
		super(x, y, TILE_WIDTH, TILE_HEIGHT);
		this.type = type;		
	}	
	
	public TextureRegion getAsset()
	{
		switch (type)
		{		
			case 1:			
			{return Assets.quad_1;}
			case 2:
			{return Assets.quad_2;}
			case 3:
			{return Assets.quad_3;}		
			case 4:
			{return Assets.quad_4;}		
			case 5:
			{return Assets.quad_5;}		
			case 6:
			{return Assets.quad_6;}		
			case 7:
			{return Assets.quad_7;}		
			case 8:
			{return Assets.quad_8;}		
			default:
			{return Assets.platform;}				
		}
	}
}
