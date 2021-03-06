package com.fracarlu.runjumprun.Engine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.fracarlu.runjumprun.Asset.*;
import com.fracarlu.runjumprun.Screens.*;

public class Juego implements ApplicationListener
{
	Pantalla actualPantalla;

	public Pantalla damePantallaInicial()
	{
		return new MenuPantalla(this);
	};

	public void ponPantalla(Pantalla estaPantalla)
	{
		actualPantalla.pause();
		actualPantalla.dispose();
		actualPantalla = estaPantalla;
	}

	@Override
	public void create()
	{
		Assets.load();
		actualPantalla = damePantallaInicial();
	}

	@Override
	public void dispose()
	{
		actualPantalla.dispose();
	}

	@Override
	public void pause()
	{
		actualPantalla.pause();
	}

	@Override
	public void render()
	{
		actualPantalla.update(Gdx.graphics.getDeltaTime());
		actualPantalla.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int arg0, int arg1)
	{
	}

	@Override
	public void resume()
	{
		actualPantalla.resume();
	}

}
