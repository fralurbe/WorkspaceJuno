package com.fracarlu.runjumprun.Objects;

public enum TileTypes {	
		NORMALPLATFORM  (0),
		SPRINGPLATFORM  (1),
		NORMALOBSTACLE  (10);		
        
		private int value;

        private TileTypes(int value) 
        {
                this.value = value;
        }
}
