package com.xgames.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.xgames.bex.BeX;
import com.xgames.bex.Global;


/*<Summary>
 * The AbstractScreen class contains the 
 * Game, Stage, and the Skin of a screen
 * 
 * The AbstractScreen implements the basic
 * function required for a screen which are:
 * render,resize,show,hide,pause,resume,and dispose
 * </Summary>
 */
public class AbstractScreen implements Screen 
{

	protected final BeX game;
	protected Stage stage;
	protected InputProcessor inputHandler;
	protected InputMultiplexer inputProcessorList;
	protected Global.ScreenType screenType;
	
	public Skin skin;
	
	public AbstractScreen(BeX game)	
	{
		Gdx.app.debug("Abstract Screen: " , "Initializing..");
		this.game = game;		
	}

	@Override
	public void render(float delta)
	{
		// the following code clears the screen with the given RGB color (black)
		Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

		// update and draw the stage actors
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{
		Gdx.app.debug("Abstract Screen :", "Resizing..");
		stage.setViewport(Global.SCREEN_WIDTH, Global.SCREEN_HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}

	// init
	@Override
	public void show() 
	{
		Gdx.app.debug("Abstract Screen: ", "Showing Screen..");
		this.stage = new Stage(Global.SCREEN_WIDTH, Global.SCREEN_HEIGHT, true);
		inputHandler = new InputAdapter() 
		{
            @Override
            public boolean keyDown(int keycode) 
            {
                if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK) )
                {
	                switch(screenType)
	                {
	                case game:	
	                	if(!Global.isPaused) Global.isPaused = true;
	                	stage.addAction( sequence(fadeOut( 0.75f ),
	                			new Action() 
	                	{
						public boolean act( float delta ) {	game.setScreen(game.getMusicScreen()); return true;}
	                	}));
	                	break;
	                case finalScore:	
	                case levelSelection:	
	                	stage.addAction( sequence(fadeOut( 0.75f ),
	                			new Action() 
	                	{
						public boolean act( float delta ) {	game.setScreen(game.getMenuScreen()); return true;}
	                	}));
	                	break;
	                case menu:				Gdx.app.exit();							break;
					default:				/*Not Used*/							break;
	                }
                }
                else if( (keycode == Input.Keys.VOLUME_DOWN) || (keycode == Input.Keys.VOLUME_UP) )
                {
                	return true;
                }
                return false;
            }
        };

        inputProcessorList = new InputMultiplexer(stage,inputHandler);
        Gdx.input.setInputProcessor(inputProcessorList);
        
		skin = new Skin();
		skin.add("calibri70", new BitmapFont(Gdx.files.internal("skin/calibri70.fnt"), Gdx.files.internal("skin/calibri70_0.png"), false));		//mainmenu - highscore
		skin.add("calibri50", new BitmapFont(Gdx.files.internal("skin/calibri50.fnt"), Gdx.files.internal("skin/calibri50_0.png"), false));		//gamescreen - score
		skin.add("calibri30", new BitmapFont(Gdx.files.internal("skin/calibri30.fnt"), Gdx.files.internal("skin/calibri30_0.png"), false));		//gamescreen - now playing
	
	}

	@Override	public void resume() 	{}
	@Override	public void hide() 		
	{
		Gdx.app.debug("Abstract Screen: ", "Hiding..");
		dispose();
	}
	
	// Wei Kiat Code;
	// Save the game settings;
	@Override
	public void pause() 
	{
		Gdx.app.debug("Abstract Screen: ", "Saving..");
		Global.isPaused = true;
		game.saveFile();
	}

	@Override
	public void dispose()
	{	
		Gdx.app.debug("Abstract Screen: ", "Disposing..");
		// dispose the collaborators
        stage.dispose();
        stage.clear();				//Wei Kiat Code; Added to clear Stage properly;
        inputProcessorList.clear(); //Wei Kiat Code; Added to clear InputList;
        skin.dispose();
	}

} 