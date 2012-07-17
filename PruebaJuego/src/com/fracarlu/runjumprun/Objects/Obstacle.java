package com.fracarlu.runjumprun.Objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fracarlu.runjumprun.Asset.Assets;


public class Obstacle 	extends Tile {
	
	public Obstacle(float x, float y, float width, float height) {
		super(x, y, width, height);
		tiletype = TileType.NORMALOBSTACLE;
	}
	
	public Obstacle(float x, float y) {
		super(x, y);
		this.tiletype = TileType.NORMALOBSTACLE;
	}
	
	public TextureRegion getAsset()
	{
		return Assets.quad_2;
	}
}
