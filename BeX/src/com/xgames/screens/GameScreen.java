package com.xgames.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.xgames.bex.BeX;
import com.xgames.bex.Global;
import com.xgames.bex.Sequence;
import com.xgames.bex.SoundManager;
import com.xgames.bex.TextureManager;

/*<Summary>
 * The GameScreen is where the actual game is played
 * it have its texture Manager to hold the images
 * </Summary>
 */
public class GameScreen extends AbstractScreen
{
	protected Texture background1, background2;
	protected Image bg1Image, bg2Image;

	protected float gameTime;
	protected float duration, time;

	protected static Sequence sequence;
	protected static TextureManager textures;

	protected Texture pauseUp, pauseDown, pauseChecked, restartUp, restartDown;
	protected Texture youtubeUp, youtubeDown, itunesUp, itunesDown, googleUp, googleDown, amazonUp, amazonDown;
	protected Music music;

	protected Label score, nowPlaying, timer, paused;

	public GameScreen(BeX game) 	
	{	
		super(game);	
		Gdx.app.debug("Game Screen: ", "Initalizing..");
	}

	@Override
	public void show()
	{
		screenType = Global.ScreenType.game;
		super.show();
		
		
		Gdx.app.debug("Game Screen: ", "Showing Game Screen..");
		// Init variables
		gameTime 	 = 0;
		Global.score = 0;

		// Init Texture Manager
		textures = new TextureManager();

		// Texture Setup
		pauseUp			= new Texture("playMenu/buttons/pause_norm.png");
		pauseDown		= new Texture("playMenu/buttons/pause_over.png");
		pauseChecked 	= new Texture("playMenu/buttons/resume_norm.png");
		youtubeUp 		= new Texture("playMenu/buttons/youtube_norm.png");
		youtubeDown 	= new Texture("playMenu/buttons/youtube_over.png");
		itunesUp 		= new Texture("playMenu/buttons/itunes_norm.png");
		itunesDown 		= new Texture("playMenu/buttons/itunes_over.png");
		googleUp		= new Texture("playMenu/buttons/googleplay_norm.png");
		googleDown 		= new Texture("playMenu/buttons/googleplay_over.png");
		amazonUp 		= new Texture("playMenu/buttons/amazon_norm.png");
		amazonDown 		= new Texture("playMenu/buttons/amazon_over.png");
		restartUp 		= new Texture("playMenu/buttons/replay_norm.png");
		restartDown		= new Texture("playMenu/buttons/replay_over.png");
		background1 	= new Texture("playMenu/playbg1.png");
		background2 	= new Texture("playMenu/playbg2.png");

		// Setup background
		bg1Image = new Image(background1);	bg1Image.setPosition(0, 0);
		bg2Image = new Image(background2);	bg2Image.setPosition(1189, 0);
		
		stage.addActor(bg1Image);	// Add Bg Image to screen
		stage.addActor(bg2Image);	// Add Bg Image to screen
		
		sequence = new Sequence(5, textures, stage);	// Create new Sequence
		
		// Creating Labels
		paused = new Label("-Paused-", skin, "calibri70", Color.WHITE);
		paused.setPosition(550, 400);
		score = new Label("" + Global.score, skin, "calibri50", Color.WHITE);
		score.setPosition(287, 640);
		nowPlaying = new Label("Gimme All (Ring My Bell)", skin, "calibri30", Color.WHITE);
		nowPlaying.setPosition(365, 470);

		// Wei Kiat Code;
		// Replaced to switch to make it more readable;
		switch(Global.song)
		{
		case 1:	music = SoundManager.music1;	break;
		case 2:	music = SoundManager.music2;	break;
		case 3: music = SoundManager.music3;	break;
		case 4: music = SoundManager.music4;	break;
		}
		duration = 60;
		nowPlaying.setText("Song " + Global.song);
		
		// Creating Buttons 
		final Button pause	= new Button(new TextureRegionDrawable( new TextureRegion(pauseUp) ), 		// Button Up
							 			 new TextureRegionDrawable( new TextureRegion(pauseDown) ), 	// Button Down
							 			 new TextureRegionDrawable( new TextureRegion(pauseChecked) ));	// Button Checked
		Button youtube		= new Button(new TextureRegionDrawable( new TextureRegion(youtubeUp) ), 	// Button Up
										 new TextureRegionDrawable( new TextureRegion(youtubeDown) ));	// Button Down
		Button itunes		= new Button(new TextureRegionDrawable( new TextureRegion(itunesUp) ), 		// Button Up
									 	 new TextureRegionDrawable( new TextureRegion(itunesDown) ));	// Button Down
		Button google		= new Button(new TextureRegionDrawable( new TextureRegion(googleUp) ), 		// Button Up
									 	 new TextureRegionDrawable( new TextureRegion(googleDown) ));	// Button Down
		Button amazon		= new Button(new TextureRegionDrawable( new TextureRegion(amazonUp) ), 		// Button Up
									 	 new TextureRegionDrawable( new TextureRegion(amazonDown) ));	// Button Down 
		Button restart		= new Button(new TextureRegionDrawable( new TextureRegion(restartUp) ), 	// Button Up
						 			 	 new TextureRegionDrawable( new TextureRegion(restartDown) ));	// Button Down
		
		// Setup Button Boundaries
		pause.setBounds(13, 610, 95, 95);
		youtube.setBounds(13, 504, 95, 95);
		itunes.setBounds(13, 398, 95, 95);
		google.setBounds(13, 292, 95, 95);
		amazon.setBounds(13, 186, 95, 95);
		restart.setBounds(13, 78, 95, 95);
		// Setup Button Listeners
		pause.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button)
			{
				Gdx.app.debug("touch pause:", "pausing");
				Global.isPaused = !Global.isPaused;
				if(Global.isPaused)
				{
					music.pause();
					stage.addActor(paused);
					sequence.setBellsNotesVisiblity(false);
				}
				else
				{
					SoundManager.play(music);
					sequence.setBellsNotesVisiblity(true);
					paused.remove();
				}
			}
		});

		restart.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
			{
				Global.isPaused = true;
				music.stop();
				stage.addAction( sequence(fadeOut( 0.75f ),
						new Action() {
					public boolean act( float delta ) {
						game.setScreen(game.getMusicScreen());
						return true; // returning true consumes the event
					}
				} )
						);
			}
		});

		youtube.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				if(!Global.isPaused )
				{
					pause.setChecked(true);
					pause.fire(event);
				}
				Gdx.net.openURI("http://www.youtube.com/");
			}
		});
		
		itunes.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				if(!Global.isPaused )
				{
					pause.setChecked(true);
					pause.fire(event);
				}
				Gdx.net.openURI("http://itunes.apple.com/");
			}
		});
		
		google.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				if(!Global.isPaused )
				{
					pause.setChecked(true);
					pause.fire(event);
				}
				Gdx.net.openURI("https://play.google.com/");
			}
		});
		
		amazon.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				if(!Global.isPaused )
				{
					pause.setChecked(true);
					pause.fire(event);
				}
				Gdx.net.openURI("http://www.amazon.com/");
			}
		});
		
		InputProcessor menuHandler = new InputAdapter()
		{
			@Override
            public boolean keyDown(int keycode) 
            {
				if(keycode == Input.Keys.MENU)
				{
					Global.isPaused = !Global.isPaused;	
				}
				if(Global.isPaused)
				{
					pause.toggle();	
					music.pause();
					stage.addActor(paused);
					sequence.setBellsNotesVisiblity(false);
				}
				else
				{
					pause.toggle();
					SoundManager.play(music);
					sequence.setBellsNotesVisiblity(true);
					paused.remove();
				}
                return false;
            }
		};
		inputProcessorList.addProcessor(menuHandler);
		
		
		// Timer Setup
		time = duration;

		int temp	= (int) (time/60);
		int temp2	= (int)(time - (temp * 60));
		
		timer = new Label(temp + ":" + temp2, skin, "calibri50", Color.WHITE);
		timer.setPosition(1020, 640);
		if(temp2 < 10)	timer.setText(temp + ":0" + temp2);

		// Play Music
		if(!Global.isPaused )	SoundManager.play(music);

		// Add Effect
		stage.addAction(fadeIn( 0.75f ));

		// Add leftover Images
		stage.addActor(score);
		stage.addActor(pause);
		stage.addActor(youtube);
		stage.addActor(itunes);
		stage.addActor(google);
		stage.addActor(amazon);
		stage.addActor(restart);
		stage.addActor(nowPlaying);
		stage.addActor(timer);		
	}

	@Override
	public void render(float delta)
	{
		super.render(delta);

		if(!Global.isPaused )
		{
			sequence.update(delta);							// Update Sequence
			score.setText( String.valueOf(Global.score) );	// Update Score

			// Update Timer
			int temp	= (int)(time/60);
			int temp2	= (int)(time - (temp * 60));
			if(temp2 < 10)	timer.setText(temp + ":0" + temp2);
			else			timer.setText(temp + ":" + temp2);

			// Completed Sequence
			if(isSequenceDone() && gameTime < duration)
			{
				Global.score += 100;
				SoundManager.play(SoundManager.clear, 1);
				sequence.destroy();
				sequence = new Sequence(5, textures, stage);
			}
			else if(gameTime > duration)
			{
				Global.isPaused  = true;
				sequence.setVisibility(false);
				music.stop();
				// Adding Sequence of Actions
				stage.addAction( sequence(	fadeOut( 0.75f ), 		// Fade Action
											delay(1.0f), 			// Delay Action
											new Action() 			// Load Screen Action
											{
												public boolean act( float delta ) 
												{
													game.setScreen(game.getFinalScoreScreen());
													return true; // returning true consumes the event
												}
											} 
										)
								);
			}

			if(sequence.blinker > 5)
			{
				for(int i = 0; i < sequence.sequence.size; i++)
				{
					if(sequence.sequence.get(i) != Sequence.Color.CHECKED)
					{
						sequence.sequenceImage.get(i).addAction(sequence(
																		fadeOut(0.5f),	// Fade Out Action
																		fadeIn(0.5f),	// Fade In Action
																		fadeOut(0.5f),	// Fade Out Action
																		fadeIn(0.5f)	
																		)
																);
						break;
					}
				}
				sequence.blinker = 0;
			}
			
			bg2Image.toFront();
			
			gameTime += delta;
			time -= delta;
			sequence.blinker += delta;
		}
	}
	@Override
	public void dispose()
	{
		super.dispose();
		
		Gdx.app.debug("Game Screen: ", "Disposing..");
		
		background1.dispose();
		background2.dispose();
		textures.dispose();
		pauseUp.dispose();
		pauseDown.dispose();
		pauseChecked.dispose();
		youtubeUp.dispose();
		youtubeDown.dispose();
		itunesUp.dispose();
		itunesDown.dispose();
		googleUp.dispose();
		googleDown.dispose();
		amazonUp.dispose();
		amazonDown.dispose();
		restartUp.dispose();
		restartDown.dispose();
		
		music.stop();
		
		Global.isPaused = false;
	}
	

	
	// Wei Kiat Code;
	// Ensure that the game is paused when phone calls, 
	// power button, or home button are fired
	@Override
	public void pause()
	{
		super.pause();
		Gdx.app.debug("Game Screen: ", "Pausing..");
		Global.isPaused  = true;		// Pausing
		
		// setting the Music Settings for SoundManager;
		// this is to prevent the music from being null if
		// the users have an incoming call, or press their
		// power button during the transition from 
		// selections level to game screen.
		switch(Global.song)
		{
		case 1:	music = SoundManager.music1;	break;
		case 2:	music = SoundManager.music2;	break;
		case 3: music = SoundManager.music3;	break;
		case 4: music = SoundManager.music4;	break;
		}
		duration = 60;
		nowPlaying.setText("Song " + Global.song);
		music.pause();
		
		// Setting up the Pause
		if(paused==null) 
		{
			paused = new Label("-Paused-", skin, "calibri70", Color.WHITE);
			paused.setPosition(550, 400);
		}
		stage.addActor(paused);
		
		// setting the sequence bell visibility.
		sequence.setBellsNotesVisiblity(false);
	}


	private boolean isSequenceDone()
	{
		for(int i = 0; i < sequence.sequence.size; i++)
		{
			if(sequence.sequence.get(i) != Sequence.Color.CHECKED)
				return false;
		}
		Gdx.app.debug("Game Screen: ", "Completed a Sqeuence.");
		return true;
	}
}
