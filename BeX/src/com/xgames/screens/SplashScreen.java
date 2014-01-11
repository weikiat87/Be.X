package com.xgames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.xgames.bex.BeX;
import com.xgames.bex.Global;
import com.xgames.bex.SoundManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/* <Summary>
 * The Splash Screen is the first screen
 * that the program will load.
 * </Summary>
 */
public class SplashScreen extends AbstractScreen 
{

	private Texture bg;
	private Array<Texture> splash;
	private Animation animation;
	private Image aniImage;
	private float stateTime;
	
	public SplashScreen(BeX game) 
	{
		super(game);	
		Gdx.app.debug("Splash Screen: ", "Initalizing..");
	}
	
	@Override
	public void show()
	{
		screenType = Global.ScreenType.splash;
		super.show();
		Gdx.app.debug("Splash Screen: ", "Showing Splash Screen..");	
		
		SoundManager.isMusicOn = true;
		SoundManager.isSoundOn = true;
		
		bg = new Texture("splashscreen/background.png");
		
		splash = new Array<Texture>(12);
		splash.add(new Texture("splashscreen/frame1.png"));
		splash.add(new Texture("splashscreen/frame2.png"));
		splash.add(new Texture("splashscreen/frame3.png"));
		splash.add(new Texture("splashscreen/frame4.png"));
		splash.add(new Texture("splashscreen/frame5.png"));
		splash.add(new Texture("splashscreen/frame6.png"));
		splash.add(new Texture("splashscreen/frame7.png"));
		splash.add(new Texture("splashscreen/frame8.png"));
		splash.add(new Texture("splashscreen/frame9.png"));
		splash.add(new Texture("splashscreen/frame10.png"));
		splash.add(new Texture("splashscreen/frame11.png"));
		splash.add(new Texture("splashscreen/frame12.png"));
		
		Image bgImage = new Image(bg);
		
		Array<TextureRegion> splashRegion = new Array<TextureRegion>(12);
		for(int i = 0; i < splash.size; i++)
			splashRegion.add(new TextureRegion(splash.get(i)));
		
		animation = new Animation(0.05f, splashRegion, Animation.NORMAL);
		stateTime = -1;
		
		aniImage = new Image(splash.get(0));
		aniImage.setPosition(246, 192);
		
		stage.addActor(bgImage);
		stage.addActor(aniImage);
		
		stage.addAction( sequence(delay( 3f ), fadeOut( 0.75f ),
				new Action() {
			public boolean act( float delta ) {
				game.setScreen(game.getMenuScreen());
				return true; // returning true consumes the event
			}
		} )
				);
	}
	
	@Override
	public void render(float delta)
	{
		super.render(delta);
		
		stateTime += delta;
		if(stateTime > 0)
			aniImage.setDrawable(new TextureRegionDrawable(animation.getKeyFrame(stateTime)));
	}
	
	@Override
	public void dispose()
	{
		Gdx.app.debug("Splash Screen: ", "Disposing..");
		bg.dispose();
		
		// Wei Kiat Code;
		// Added to ensure that all images are cleared.
		splash.clear();	
		aniImage.clearActions();
		
		super.dispose();
	}
}