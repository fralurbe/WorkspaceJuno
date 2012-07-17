package com.fracarlu.runjumprun.Objects;


import com.badlogic.gdx.math.Vector2;

/**
 * 
 * @author Fran
 *
 *Plataforma que tiene una fuerza hacia arriba
 */

public class SpringPlatform extends Platform {		
	public static final Vector2 force = new Vector2(0, 5f);

	public SpringPlatform(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public SpringPlatform(float x, float y, TileType tiletype) {
		super(x, y, TileType.SPRINGPLATFORM);
		// TODO Auto-generated constructor stub
	}

	public SpringPlatform(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}	
	
}
