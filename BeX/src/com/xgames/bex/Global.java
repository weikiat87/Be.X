package com.xgames.bex;

import java.util.Random;

/* <Summary>
 * The Global class is used to store global variables
 * as well as storing utilities function like random
 * </Summary>
 */
public class Global 
{
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 720;
	
	public static int score;
	public static int highscore;
	public static int song;
	
	public static boolean musicOn;
	public static boolean sfxOn;
	public static boolean isPaused;
	public static Random random = new Random();		// Wei Kiat Code; Added for utilities;
	public enum ScreenType { splash,menu,levelSelection,game,finalScore }
}
