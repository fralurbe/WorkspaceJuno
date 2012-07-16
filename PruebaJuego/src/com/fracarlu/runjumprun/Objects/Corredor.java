package com.fracarlu.runjumprun.Objects;

import com.fracarlu.runjumprun.Engine.Mundo;

public class Corredor extends DynamicGameObject
{
	public static final int CORREDOR_STATE_JUMP = 0;
	public static final int CORREDOR_STATE_RUN = 1;
	public static final int CORREDOR_STATE_UPON_PLATFORM = 2;
	public static final int CORREDOR_STATE_HIT = 2;
	
	public static final float CORREDOR_JUMP_VELOCITY = 5;
	public static final float CORREDOR_MOVE_VELOCITY = 10;
	
	public static final float CORREDOR_WIDTH = 1f;
	public static final float CORREDOR_HEIGHT = 1f;
	
	public static final float CORREDOR_JUMP_IMPULSE = 5f;
	
	public int estado;
	public float estadoTime;

	public Corredor(float x, float y) 
	{
		super(x, y, CORREDOR_WIDTH , CORREDOR_HEIGHT);
		estado = CORREDOR_STATE_RUN;
		estadoTime = 0;
		velocity.x = CORREDOR_MOVE_VELOCITY;
	}
	
	public void update (float deltaTime) 
	{
		velocity.add(Mundo.gravedad.x * deltaTime, Mundo.gravedad.y * deltaTime);
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
                
		if (!CheckEndOfTheWorld())
		{
			//velocity.x += Mundo.gravedad.x * deltaTime;	
			
			UpdateYAxis(deltaTime);			
			bounds.x = position.x ;
			bounds.y = position.y ;	
			estadoTime += deltaTime;
		}
		else
		{
			velocity.x = 0;
			position.x = Mundo.MUNDO_WIDTH;
		}	
	}
	
	private void UpdateYAxis(float deltaTime)
	{
		if(position.y > 1f)
		{
			velocity.y += Mundo.gravedad.y * deltaTime;			
		}
		else
		{			
			velocity.y = 0;
			position.y = 1;
		}
		position.add(velocity.x * deltaTime, velocity.y*deltaTime);
	}	
	
	private Boolean CheckEndOfTheWorld()
	{
		if (position.x >= Mundo.MUNDO_WIDTH-Mundo.TILES_PER_SCREEN / 2)
		{			
			return true;
		}
		return false;
	}
	
	public void hitObstaculo () {
		//velocity.y = CORREDOR_JUMP_VELOCITY * 1.5f;
		velocity.y = 1f;
		estado = CORREDOR_STATE_HIT;
		estadoTime = 0;
	}
	
	public void Jump () {
		velocity.y = CORREDOR_JUMP_IMPULSE;
		estado = CORREDOR_STATE_JUMP;
	}
	
	public void Hit()
	{		
		estado = CORREDOR_STATE_HIT;
		estadoTime = 0;
	}
}
