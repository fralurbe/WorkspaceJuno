package com.fracarlu.runjumprun.Objects;


public class Obstacle 	extends Tile {

	public Obstacle(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}


	public Obstacle(float x, float y, TileType tiletype) {
		super(x, y, TileType.NORMALOBSTACLE);
		// TODO Auto-generated constructor stub
	}

	public Obstacle(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}							
}
