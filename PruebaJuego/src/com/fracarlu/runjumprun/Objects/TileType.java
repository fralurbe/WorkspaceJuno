package com.fracarlu.runjumprun.Objects;

public enum TileType {	
		NORMALPLATFORM  (0),
		SPRINGPLATFORM  (1),
		NORMALOBSTACLE  (10);		
        
		private int value;

        private TileType(int value) 
        {
                this.value = value;
        }
}
