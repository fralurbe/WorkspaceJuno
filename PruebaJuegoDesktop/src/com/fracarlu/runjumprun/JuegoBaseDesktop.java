package com.fracarlu.runjumprun;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.fracarlu.runjumprun.Engine.Juego;

public class JuegoBaseDesktop {
	public static final int ancho = 320;
	public static final int alto = 480;
	public static void main (String [] args)
	{
		new LwjglApplication(new Juego(), "TituloJuego", ancho, alto, false);
	}
}
