package com.xgames.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

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

/* <Summary>
 * The Music Screen is where the player
 * will select a level that they will be playing.
 * </Summary>
 */

public class MusicScreen extends AbstractScreen
{
	private Texture background, alpha;
	private Texture oneUp, oneDown, twoUp, twoDown, threeUp, threeDown, fourUp, fourDown;
	
	public MusicScreen(BeX game) 
	{
		super(game);	
		Gdx.app.debug("Music Screen: ", "Initalizing..");
	}

	
	@Override
	public void show()
	{
		screenType = Global.ScreenType.levelSelection;
		super.show();

		Gdx.app.debug("Music Screen: ", "Showing Music Screen..");
		// Setup Textures
		background	= new Texture("mainMenu/background.png");
		alpha 		= new Texture("chooseyourmusicscreen/background.png");
		oneUp 		= new Texture("chooseyourmusicscreen/1_norm.png");
		oneDown 	= new Texture("chooseyourmusicscreen/1_over.png");
		twoUp 		= new Texture("chooseyourmusicscreen/2_norm.png");
		twoDown 	= new Texture("chooseyourmusicscreen/2_over.png");
		threeUp 	= new Texture("chooseyourmusicscreen/3_norm.png");
		threeDown 	= new Texture("chooseyourmusicscreen/3_over.png");
		fourUp 		= new Texture("chooseyourmusicscreen/4_norm.png");
		fourDown 	= new Texture("chooseyourmusicscreen/4_over.png");

		// Setup BG
		Image bgImage = new Image(background);
		bgImage.setPosition(0, 0);
		
		// Setup Label
		Label text = new Label("(Tap Anywhere To Go Back)", skin, "calibri30", Color.WHITE);
		text.setPosition(450, 150);
		
		// Creating Buttons
		final Button alphaButton	= new Button(new TextureRegionDrawable(new TextureRegion(alpha)));
		final Button oneButton 		= new Button(new TextureRegionDrawable(new TextureRegion(oneUp)),
												 new TextureRegionDrawable(new TextureRegion(oneDown)));
		final Button twoButton 		= new Button(new TextureRegionDrawable(new TextureRegion(twoUp)),
												 new TextureRegionDrawable(new TextureRegion(twoDown)));
		final Button threeButton 	= new Button(new TextureRegionDrawable(new TextureRegion(threeUp)),
												 new TextureRegionDrawable(new TextureRegion(threeDown)));
		final Button fourButton 	= new Button(new TextureRegionDrawable(new TextureRegion(fourUp)),
												 new TextureRegionDrawable(new TextureRegion(fourDown)));

		// Setup Button Pos
		alphaButton.setPosition(0, 0);
		oneButton.setPosition(51, 288);
		twoButton.setPosition(362, 288);
		threeButton.setPosition(671, 288);
		fourButton.setPosition(986, 288);

		// Setup Button Listeners
		alphaButton.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				stage.addAction( sequence(fadeOut( 0.5f ),
						new Action() {
					public boolean act( float delta ) {
						game.setScreen(game.getMenuScreen());
						return true; // returning true consumes the event
					}
				} )
						);
				return true;
			}
		});

		oneButton.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Global.song = 1;
				stage.addAction( sequence(fadeOut( 0.5f ),
						new Action() {
					public boolean act( float delta ) {
						game.setScreen(game.getGameScreen());
						return true; // returning true consumes the event
					}
				} )
						);
			}
		});

		twoButton.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Global.song = 2;
				stage.addAction( sequence(fadeOut( 0.75f ), delay(0.5f),
						new Action() {
					public boolean act( float delta ) {
						game.setScreen(game.getGameScreen());
						return true; // returning true consumes the event
					}
				} )
						);
			}
		});

		threeButton.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Global.song = 3;
				stage.addAction( sequence(fadeOut( 0.75f ), delay(0.5f),
						new Action() {
					public boolean act( float delta ) {
						game.setScreen(game.getGameScreen());
						return true; // returning true consumes the event
					}
				} )
						);
			}
		});

		fourButton.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Global.song = 4;
				stage.addAction( sequence(fadeOut( 0.75f ), delay(0.5f),
						new Action() {
					public boolean act( float delta ) {
						game.setScreen(game.getGameScreen());
						return true; // returning true consumes the event
					}
				} )
						);
			}
		});
		
		// Add Actors into stage
		stage.addActor(bgImage);
		stage.addActor(text);
		stage.addActor(alphaButton);
		stage.addActor(oneButton);
		stage.addActor(twoButton);
		stage.addActor(threeButton);
		stage.addActor(fourButton);
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
		Gdx.app.debug("Music Screen: ", "Disposing..");
		
		background.dispose();
		alpha.dispose();
		
		oneUp.dispose();
		oneDown.dispose();
		twoUp.dispose();
		twoDown.dispose();
		threeUp.dispose();
		threeDown.dispose();
		fourUp.dispose();
		fourDown.dispose();
	}
}