package com.fracarlu.runjumprun.Objects;


import com.badlogic.gdx.math.Vector2;

/**
 * 
 * @author Fran
 *
 *Plataforma que tiene una fuerza hacia arriba
 */

public class PlatformSpring extends Platform {		
	public static final Vector2 force = new Vector2(0, 5f);

	public PlatformSpring(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public PlatformSpring(float x, float y, TileTypes tiletype) {
		super(x, y, TileTypes.SPRINGPLATFORM);
		// TODO Auto-generated constructor stub
	}

	public PlatformSpring(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}	
	
}
