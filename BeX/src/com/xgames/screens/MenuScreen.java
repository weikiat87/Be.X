package com.xgames.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
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
import com.xgames.bex.SoundManager;

/* <Summary>
 * The Menu Screen will be where the player
 * can view their highscore and toggling their
 * music and sfx controllers
 * </Summary>
 */
public class MenuScreen extends AbstractScreen 
{
	private Texture background, title, credits, alpha;
	private Texture fxUp, fxDown, fxChecked, musicUp, musicDown, musicChecked, infoUp, infoDown;
	
	private Label highscore;
	
	public MenuScreen(BeX game) 
	{	
		super(game);
		Gdx.app.debug("Menu Screen: ", "Initalizing..");
	}
	
	@Override
	public void show()
	{
		screenType = Global.ScreenType.menu;
		super.show();

		Gdx.app.debug("Menu Screen: ", "Showing Menu Screen..");
		// Init Variables
		Global.song = 0;
		
		// Setup Textures
		background	 = new Texture("mainMenu/background.png");
		credits 	 = new Texture("creditscreen/creditscreen.png");
		alpha 		 = new Texture("creditscreen/alphascreen.png");
		title 		 = new Texture("mainMenu/Title.png");
		fxUp		 = new Texture("mainMenu/buttons/sfxbutton_on.png");
		fxDown		 = new Texture("mainMenu/buttons/sfxbutton_over.png");
		fxChecked	 = new Texture("mainMenu/buttons/sfxbutton_off.png");
		musicUp		 = new Texture("mainMenu/buttons/musicbutton_on.png");
		musicDown	 = new Texture("mainMenu/buttons/musicbutton_over.png");
		musicChecked = new Texture("mainMenu/buttons/musicbutton_off.png");	
		infoUp	 	 = new Texture("mainMenu/buttons/creditbutton_norm.png");
		infoDown 	 = new Texture("mainMenu/buttons/creditbutton_over.png");

		// Setup BG
		Image bgImage = new Image(background);
		bgImage.setPosition(0, 0);
		
		// Setup Labels
		final Label text = new Label("(Tap Anywhere To Go Back)", skin, "calibri30", Color.WHITE);
		text.setPosition(450, 690);
		highscore = new Label("" + Global.highscore, skin, "calibri50", Color.WHITE);
		highscore.setPosition(300, 650);
		
		// Setup Buttons
		Button soundButton = null;
		Button musicButton = null;
		final Button creditsButton	= new Button(new TextureRegionDrawable(new TextureRegion(credits)));
		final Button alphaButton 	= new Button(new TextureRegionDrawable(new TextureRegion(alpha)));	
		final Button titleButton 	= new Button(new TextureRegionDrawable(new TextureRegion(title)));
		Button infoButton 			= new Button(new TextureRegionDrawable(new TextureRegion(infoUp)),
			 									 new TextureRegionDrawable(new TextureRegion(infoDown)));

		if(SoundManager.isMusicOn)
		{
			musicButton = new Button(new TextureRegionDrawable(new TextureRegion(fxUp)),
									 new TextureRegionDrawable(new TextureRegion(fxDown)),
									 new TextureRegionDrawable(new TextureRegion(fxChecked)));
		}
		else
		{
			musicButton = new Button(new TextureRegionDrawable(new TextureRegion(fxChecked)),
									 new TextureRegionDrawable(new TextureRegion(fxDown)),
									 new TextureRegionDrawable(new TextureRegion(fxUp)));
		}
		if(SoundManager.isSoundOn)
		{
			soundButton = new Button(new TextureRegionDrawable(new TextureRegion(musicUp)),
									 new TextureRegionDrawable(new TextureRegion(musicDown)),
									 new TextureRegionDrawable(new TextureRegion(musicChecked)));
		}
		else
		{
			soundButton = new Button(new TextureRegionDrawable(new TextureRegion(musicChecked)),
									 new TextureRegionDrawable(new TextureRegion(musicDown)),
									 new TextureRegionDrawable(new TextureRegion(musicUp)));
		}
		
		// Setup Button Pos
		soundButton.setPosition(947, 14);
		musicButton.setPosition(1076, 14);
		creditsButton.setPosition(255, 50);
		titleButton.setPosition(20, 158);
		infoButton.setPosition(814, 14);
	
		// Setup Button Listeners
		titleButton.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				stage.addAction( sequence(fadeOut( 0.5f ),
						new Action() {
					public boolean act( float delta ) {
						game.setScreen(game.getMusicScreen());
						return true; // returning true consumes the event
					}
				} )
						);
				return true;
			}
		});

		soundButton.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Global.sfxOn			= !Global.sfxOn;
				SoundManager.isSoundOn	= Global.sfxOn;
			}
		});

		musicButton.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Global.musicOn			= !Global.musicOn;
				SoundManager.isMusicOn	= Global.musicOn;
			}
		});
		
		infoButton.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				stage.addActor(text);
				stage.addActor(alphaButton);
				stage.addActor(creditsButton);
				titleButton.remove();
				highscore.remove();
			}
		});
		
		alphaButton.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				alphaButton.remove();
				creditsButton.remove();
				text.remove();
				stage.addActor(titleButton);
				stage.addActor(highscore);
			}
		});
		
		creditsButton.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				alphaButton.remove();
				creditsButton.remove();
				text.remove();
				stage.addActor(titleButton);
				stage.addActor(highscore);
			}
		});

		// Add Actors
		stage.addActor(bgImage);
		stage.addActor(titleButton);
		stage.addActor(infoButton);
		stage.addActor(soundButton);
		stage.addActor(musicButton);
		stage.addActor(highscore);
	}

	@Override
	public void dispose()
	{
		Gdx.app.debug("Menu Screen: ", "Disposing..");
		super.dispose();
		
		background.dispose();
		title.dispose();
		alpha.dispose();
		credits.dispose();
		
		fxUp.dispose();
		fxDown.dispose();
		fxChecked.dispose();
		musicUp.dispose();
		musicDown.dispose();
		musicChecked.dispose();
		infoUp.dispose();
		infoDown.dispose();
	}
}
