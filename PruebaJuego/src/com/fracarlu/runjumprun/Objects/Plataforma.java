package com.fracarlu.runjumprun.Objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fracarlu.runjumprun.Asset.Assets;

public class Plataforma extends Tile
{
	public static final TextureRegion ASSET = Assets.platform;
	public static TextureRegion asset;	

	public Plataforma(float x, float y, float width, float height) {		
		super(x, y, width, height);
	}
	
	public Plataforma(float x, float y, int type) {
		super(x, y, TILE_WIDTH, TILE_HEIGHT);
		this.type = type;		
	}	
}
