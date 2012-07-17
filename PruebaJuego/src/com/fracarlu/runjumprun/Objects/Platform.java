package com.fracarlu.runjumprun.Objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fracarlu.runjumprun.Asset.Assets;

public class Platform extends Tile
{

	public Platform(float x, float y) {
		super(x, y);		
		tiletype = TileType.NORMALPLATFORM;
	}

	public Platform(float x, float y, float width, float height) {
		super(x, y, width, height);
		tiletype = TileType.NORMALPLATFORM;
	}
	
	public TextureRegion getAsset()
	{				
		return Assets.platform;}
	}	
}
