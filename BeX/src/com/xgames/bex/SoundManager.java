package com.xgames.bex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/* <Summary>
 * The Sound Manager class will store and handle
 * all the Music, and SFX of the game.
 * </Summary>
 */
public class SoundManager 
{

	public static Sound button;
	public static Music music1, music2, music3, music4;
	public static Sound right, wrong, clear;
	
	public static boolean isMusicOn, isSoundOn;

	public static void load() 
	{
		Gdx.app.debug("Sound Manager:", "Loading Files..");
		// Wei Kiat Code;
		// Refactored to be persistent
        isMusicOn = Global.musicOn;
        isSoundOn = Global.sfxOn;
        
        music1 = Gdx.audio.newMusic(Gdx.files.internal("data/music1.mp3"));      
        music2 = Gdx.audio.newMusic(Gdx.files.internal("data/music2.mp3"));       
        music3 = Gdx.audio.newMusic(Gdx.files.internal("data/music3.mp3"));        
        music4 = Gdx.audio.newMusic(Gdx.files.internal("data/music4.mp3"));       
        
        music1.setLooping(false);    
        music2.setLooping(false);
        music3.setLooping(false); 
        music4.setLooping(false);
        
        right = Gdx.audio.newSound(Gdx.files.internal("data/right.mp3"));
        wrong = Gdx.audio.newSound(Gdx.files.internal("data/wrong.mp3"));
        clear = Gdx.audio.newSound(Gdx.files.internal("data/clear.mp3"));
     }
	
	// Wei Kiat Code;
	// Refactored for ease of Reading;
	public static void play(Music music)				{	if(isMusicOn)	music.play();	}
	public static void play(Sound sound)				{	if(isSoundOn)	sound.play();	}
	public static void play(Sound sound, float volume)	{	if(isSoundOn)	sound.play(volume);	}
}
