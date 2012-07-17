package com.fracarlu.runjumprun.Objects;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.fracarlu.runjumprun.Asset.Assets;


public class SpringPlatform extends Platform {		
	public static final Vector2 force = new Vector2(0, 5f);

	public SpringPlatform(float x, float y, float width, float height) {
		super(x, y, width, height);
		tiletype  = TileType.SPRINGPLATFORM;
	}

	public SpringPlatform(float x, float y) {
		super(x, y);
		tiletype  = TileType.SPRINGPLATFORM;
	}	
	
	public TextureRegion getAsset()
	{
		return Assets.quad_3;
	}	
}
