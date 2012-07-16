package com.fracarlu.runjumprun.Objects;

public enum Objetos {	
		NORMALPLATFORM  (0),
		SPRINGPLATFORM  (1),
		NORMALOBSTACLE  (10);		
        
		private int value;

        private Objetos(int value) 
        {
                this.value = value;
        }
}
