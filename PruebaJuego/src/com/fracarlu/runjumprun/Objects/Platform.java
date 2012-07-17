package com.fracarlu.runjumprun.Objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fracarlu.runjumprun.Asset.Assets;

public class Platform extends Tile
{

	public Platform(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public Platform(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public Platform(float x, float y, TileType tiletype) {
		super(x, y, TileType.NORMALPLATFORM);
		// TODO Auto-generated constructor stub
	}	
}
