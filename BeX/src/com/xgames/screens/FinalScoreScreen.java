package com.xgames.screens;

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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.xgames.bex.BeX;
import com.xgames.bex.Global;
/* <Summary>
 *  The FinalScoreScreen will be shown when the 
 *  the player completes a level.
 * </Summary>
 */

public class FinalScoreScreen extends AbstractScreen 
{

	private Texture background, alpha, table;
	private Texture contUp, contDown;
	
	private Label score;
	private int number, speed;

	public FinalScoreScreen(BeX game) 	
	{
		super(game);	
		Gdx.app.debug("Final Score Screen: ", "Initalizing..");
	}

	@Override
	public void show()
	{
		Gdx.app.debug("Final SCore Screen: ", "Showing Final Screen..");
		screenType = Global.ScreenType.finalScore;
		super.show();

		// Loading Textures
		background 	= new Texture("playMenu/background_2.png");
		alpha 		= new Texture("creditscreen/alphascreen.png");
		table 		= new Texture("highscore/yourscore.png");
		contUp 		= new Texture("highscore/continue_norm.png");
		contDown	= new Texture("highscore/continue_over.png");

		// Creating Images
		Image bgImage 	 = new Image(background);
		Image alphaImage = new Image(alpha);
		Image tableImage = new Image(table);
		tableImage.setPosition(200, 211);
		
		// Creating Continue Button
		Button cont = new Button(new TextureRegionDrawable(new TextureRegion(contUp)), 
								 new TextureRegionDrawable(new TextureRegion(contDown)));
		cont.setPosition(474, 264);
		cont.addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				stage.addAction( sequence(fadeOut( 0.75f ),
						new Action() {
					public boolean act( float delta ) {
						game.setScreen(game.getMenuScreen());
						return true; // returning true consumes the event
					}
				} )
						);
			}
		});
		
		// Creating Score Label
		number = 0;
		speed = (int)(Global.score);
		
		score = new Label("" + number, skin, "calibri70", Color.WHITE);
		score.setPosition(579, 365);
		score.setAlignment(Align.center);
		
		stage.addActor(bgImage);
		stage.addActor(alphaImage);
		stage.addActor(tableImage);
		stage.addActor(score);
		stage.addActor(cont);
		
		if(Global.score > Global.highscore)		Global.highscore = Global.score;
		game.saveFile();
	}

	@Override
	public void render(float delta)
	{
		super.render(delta);
		
		number += (speed * delta);
		if(number > Global.score)
			number = Global.score;
		
		score.setText("" + number);
	}
	
	@Override
	public void dispose()
	{
		Gdx.app.debug("Final Score Screen: ", "Disposing..");
		super.dispose();
		
		background.dispose();
		alpha.dispose();
		table.dispose();
		contUp.dispose();
		contDown.dispose();
	}
}
