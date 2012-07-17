package com.fracarlu.runjumprun.Engine;

import com.fracarlu.runjumprun.Objects.Obstacle;
import com.fracarlu.runjumprun.Objects.Platform;
import com.fracarlu.runjumprun.Objects.SpringPlatform;
import com.fracarlu.runjumprun.Objects.Tile;
import com.fracarlu.runjumprun.Objects.TileType;

public class TileFactory {
	public Tile createTile(float x, float y, TileType tiletype)
	{
		switch(tiletype)
		{
			case NORMALOBSTACLE:
				return new Obstacle(x,y);			
			case NORMALPLATFORM:
				return new Platform(x,y);			
			case SPRINGPLATFORM:		
				return new SpringPlatform(x,y);
			default:
				break;	
		}
		return null;		
	}

}
