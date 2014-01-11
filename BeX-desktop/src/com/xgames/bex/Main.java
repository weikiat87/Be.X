package com.xgames.bex;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/* <Summary>
 * This is used for the desktop version of the game.
 * </Summary>
 */

public class Main 
{
	public static void main(String[] args) 
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Be.X";
		cfg.useGL20 = true;
		cfg.width = 600;
		cfg.height = 360;
		
		new LwjglApplication(new BeX(), cfg);
	}
}
