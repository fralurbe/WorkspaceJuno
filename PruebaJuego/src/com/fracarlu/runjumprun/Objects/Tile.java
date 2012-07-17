package com.fracarlu.runjumprun.Objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fracarlu.runjumprun.Asset.Assets;

public abstract  class Tile extends GameObject 
{
	public static final float TILE_WIDTH = 1f;
	public static final float TILE_HEIGHT = 1f;	
			
	protected TileType tiletype = TileType.NORMALPLATFORM;
	
	public Tile(float x, float y) {
		super(x, y, TILE_WIDTH, TILE_HEIGHT);		
	}
	
	public Tile(float x, float y, TileType tiletype) {
		super(x, y, TILE_WIDTH, TILE_HEIGHT);
		this.tiletype  = tiletype ;		
	}
	
	public Tile(float x, float y, float width, float height) {
		super(x, y, width, height);		
 	}
		
	public TextureRegion getAsset()
	{
		switch (tiletype)
		{		
			case NORMALPLATFORM:			
				{return Assets.platform;}
			case SPRINGPLATFORM:
				{return Assets.quad_1;}
			case NORMALOBSTACLE:
				{return Assets.quad_2;}
			default:
				return Assets.quad_3;
		}
	}
}
