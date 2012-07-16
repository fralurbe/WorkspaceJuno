package com.fracarlu.runjumprun.Objects;


import com.badlogic.gdx.math.Vector2;

/**
 * 
 * @author Fran
 *
 *Plataforma que tiene una fuerza hacia arriba
 */

public class PlataformaSpring extends Plataforma {
	public static final int SPRING_TYPE = 1;
	
	public static final Vector2 force = new Vector2(0, 5f);	
	
	public PlataformaSpring (float x, float y) {
		super(x, y, SPRING_TYPE);		
	}		
}
