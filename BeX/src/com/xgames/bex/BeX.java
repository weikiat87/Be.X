package com.xgames.bex;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Application.ApplicationType;
import com.xgames.screens.FinalScoreScreen;
import com.xgames.screens.GameScreen;
import com.xgames.screens.MenuScreen;
import com.xgames.screens.MusicScreen;
import com.xgames.screens.SplashScreen;

/*<Summmary>
 * The BeX Class is used as the main application 
 * for the Program. Its main responsibility is to
 * handle the Screens for the program. (by extending the 
 * Game class from libGDX)
 * 
 * It also catches the hardware menu, and back button on the
 * phone.
 * </Summary>
 */
public class BeX extends Game
{
	
	@Override
	public void create()
	{	
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.debug("Main Source: ", "Initlizing..");
		// Wei Kiat Code;
		// Catching hardware Inputs and enabling debugging;
		if(Gdx.app.getType() == ApplicationType.Android)
		{
			Gdx.app.debug("Main Source: ", "Enabling Button Catches;");
			Gdx.input.setCatchBackKey(true);
			Gdx.input.setCatchMenuKey(true);
		}
		loadFile();
		
		setScreen(getSplashScreen());
		SoundManager.load();
	}

	@Override
	public void dispose() 
	{
		Gdx.app.debug("Main Source: ", "Disposing..");
		super.dispose();
	}

	@Override
	public void render() 
	{		
		super.render();
	}

	@Override
	public void resize(int width, int height) 
	{
		super.resize(width, height);
	}

	@Override
	public void pause() 
	{
		Gdx.app.debug("Main Source: ", "Pausing..");
		super.pause();
	}

	@Override
	public void resume() 
	{
		Gdx.app.debug("Main Source: ", "Resuming..");
		super.resume();
	}
	
	@Override
	public void setScreen(Screen screen)
	{
		Gdx.app.debug("Main Source: ", "Setup Screen " + screen.toString() );
		super.setScreen(screen);
	}
	
	private void loadFile()
	{
		Gdx.app.debug("Main Source: ", "Loading Files..");
		Global.highscore = 0;
		Global.musicOn 	 = true;
		Global.sfxOn	 = true;
		
		Preferences pref = Gdx.app.getPreferences("bex");
		// Wei Kiat Code;
		// Persistent Music and SFX controls;
		if(pref.contains("Music"))		Global.musicOn	= pref.getBoolean("Music");
		if(pref.contains("Sfx"))		Global.sfxOn	= pref.getBoolean("Sfx");
		
		if(pref.contains("highscore"))	Global.highscore = pref.getInteger("highscore");
	}
	
	public void saveFile()
	{
		Gdx.app.debug("Main Source: ", "Saving Files..");
		
		Preferences pref = Gdx.app.getPreferences("bex");
		
		// Wei Kiat Code;
		// Persistent Music and SFX controls;
		pref.putBoolean("Music", Global.musicOn);
		pref.putBoolean("Sfx", Global.sfxOn);
		
		pref.putInteger("highscore", Global.highscore);
		pref.flush();
	}
	
	//Screen methods
	public Screen getSplashScreen()		{	return new SplashScreen(this);		}
	public Screen getGameScreen()		{	return new GameScreen(this);		}
	public Screen getMenuScreen() 		{	return new MenuScreen(this);		}
	public Screen getFinalScoreScreen()	{	return new FinalScoreScreen(this);	}
	public Screen getMusicScreen()		{	return new MusicScreen(this);		}
}
