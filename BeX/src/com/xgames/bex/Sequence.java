package com.xgames.bex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;

/*<Summary>
 * The Sequence class is used to store a
 * sequence of bells and notes which are 
 * used in a level.
 * </Summary>
 */

public class Sequence 
{
	public enum Color	{	A, B, C, D, E, CHECKED	}
	
	public		float 			blinker;		//Time for blinker
	public 		Array<Color>	sequence;		//Holder for the sequence
	public 		Array<Image>	sequenceImage;	//Holder for the sequence Image
	public 		Array<Image>	musicalNotes;	//Holder Musical notes Image
	public 		Array<Button>	bells;			//Holder for the Bells
	protected 	Array<Color>	bellsColor;		//Holder for the Bells Colors

	protected static final int BELL_BAR_1	= 258;	//Y positions of the bells on top bar	
	protected static final int BELL_BAR_2	= 86;	//Y positions of the bells on bottom bar
	protected static final int SEQ_WIDTH	= 105;	//Positions of the sequence Width
	protected static final int SEQ_HEIGHT	= 109;	//Positions of the sequence Height

	protected 	Stage	stage;	// Stage
	protected 	int 	speed;	//Speed of bells
	
	private TextureManager textures;	//Texture Manager
	
	public Sequence(int number, TextureManager textureManager, Stage screenStage)
	{
		Gdx.app.debug("Sequence: ", "Initalizing..");
		stage 		= screenStage;
		textures 	= textureManager;

		sequence 		= new Array<Color>(number);
		sequenceImage	= new Array<Image>(number);
		
		for(int i = 0; i < number; i++)
		{
			Color temp = randomColor(number);
			sequence.add(temp);
			switch(temp)
			{
			case A:	sequenceImage.add(new Image(textures.ATex));	break;
			case B:	sequenceImage.add(new Image(textures.BTex));	break;
			case C:	sequenceImage.add(new Image(textures.CTex));	break;
			case D:	sequenceImage.add(new Image(textures.DTex));	break;
			case E:	sequenceImage.add(new Image(textures.ETex));	break;
			default:
				break;
			}
			sequenceImage.get(i).setBounds(354 + (158 * i), 519, SEQ_WIDTH, SEQ_HEIGHT);
		}

		bells 		 = new Array<Button>(number);
		bellsColor	 = new Array<Color>(number);
		musicalNotes = new Array<Image>(number);
		
		for(int i = 0; i < number; i++)
		{
			bellsColor.add(sequence.get(i));
			switch(bellsColor.get(i))
			{
			case A:		bells.add(new Button(new TextureRegionDrawable(new TextureRegion(textures.ATex))));		break;
			case B:		bells.add(new Button(new TextureRegionDrawable(new TextureRegion(textures.BTex))));		break;
			case C:		bells.add(new Button(new TextureRegionDrawable(new TextureRegion(textures.CTex))));		break;
			case D:		bells.add(new Button(new TextureRegionDrawable(new TextureRegion(textures.DTex))));		break;
			case E:		bells.add(new Button(new TextureRegionDrawable(new TextureRegion(textures.ETex))));		break;
			default:	
				//error
				break;
			}
			
			setButtonEvent(i);
			
			musicalNotes.add(new Image(textures.notes.random()));
			
			// Wei Kiat Code;
			// Optimising the Random Function;
			if(Global.random.nextInt(2) == 0)
			{
				bells.get(i).setY(BELL_BAR_1);
				musicalNotes.get(i).setY(124);
			}
			else
			{
				bells.get(i).setY(BELL_BAR_2);
				musicalNotes.get(i).setY(297);
			}
		}
		
		IntArray temp = new IntArray(number);
		for(int i = 0; i < number; i++)	{	temp.add(i);	}
		temp.shuffle();
		
		for(int i = 0; i < number; i++)
		{
			bells.get(i).setX((temp.get(i) * 208) + 1250);
			musicalNotes.get(i).setX((temp.get(i) * 208) + 1250);
		}
		
		speed = 300;
		blinker = 0;
		
		//Add into stage
		for(int i = 0; i < number; i++)
		{
			stage.addActor(sequenceImage.get(i));
			stage.addActor(bells.get(i));
			stage.addActor(musicalNotes.get(i));
		}
	}

	public void update(float delta)
	{
		for(int i = 0; i < bells.size; i++)
		{
			bells.get(i).setX(bells.get(i).getX() - (speed*delta));
			musicalNotes.get(i).setX(bells.get(i).getX() - (speed*delta));
			
			if(bells.get(i).getX() < 220)
			{
				bells.get(i).setX(1250);
				if(!bells.get(i).hasParent())
				{
					Color temp = randomColor(5);
					bellsColor.set(i, temp);
					float x = bells.get(i).getX();
					float y = bells.get(i).getY();
					switch(temp)
					{
					case A:		bells.set(i, (new Button(new TextureRegionDrawable(new TextureRegion(textures.ATex)))));	break;
					case B:		bells.set(i, (new Button(new TextureRegionDrawable(new TextureRegion(textures.BTex)))));	break;
					case C:		bells.set(i, (new Button(new TextureRegionDrawable(new TextureRegion(textures.CTex)))));	break;
					case D:		bells.set(i, (new Button(new TextureRegionDrawable(new TextureRegion(textures.DTex)))));	break;
					case E:		bells.set(i, (new Button(new TextureRegionDrawable(new TextureRegion(textures.ETex)))));	break;
					default:
						//error
						break;
					}
					bells.get(i).setPosition(x, y);
					setButtonEvent(i);
					stage.addActor(bells.get(i));
				}
			}
			
			if(musicalNotes.get(i).getX() < 220)	
				musicalNotes.get(i).setX(1250);				
		}
	}

	public void destroy()
	{
		Gdx.app.debug("Sequence: ", "Destorying Images");
		for(int i = 0; i < sequenceImage.size; i++)
		{
			sequenceImage.get(i).remove();
			bells.get(i).remove();
			musicalNotes.get(i).remove();
		}
	}
	
	// Wei Kiat Code; 
	// Added for ease of displaying;
	public void setBellsNotesVisiblity(boolean flag)
	{
		Gdx.app.debug("Sequence: ", "Setting Visibility for bells and notes");
		for(int i = 0; i < bells.size; i++)
		{
			bells.get(i).setVisible(flag);
			musicalNotes.get(i).setVisible(flag);
		}
	}
	public void setVisibility(boolean flag)
	{
		Gdx.app.debug("Sequence: ", "Setting Visibility for Images");
		for(int i = 0; i < bells.size; i++)
		{
			bells.get(i).setVisible(flag);
			sequenceImage.get(i).setVisible(flag);
			musicalNotes.get(i).setVisible(flag);
		}
	}
	
	private Color randomColor(int value)
	{
		int temp = Global.random.nextInt(value);
		return Color.values()[temp];
	}
	
	private void setButtonEvent(final int number)
	{
		bells.get(number).addListener(new InputListener() 
		{
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
			{
				for(int i = 0; i < sequence.size; i++)
				{
					if(sequence.get(i) != Color.CHECKED)
					{
						if(sequence.get(i) == bellsColor.get(number))
						{
							sequence.set(i, Color.CHECKED);
							sequenceImage.get(i).setColor(1, 1, 1, 0.2f);
							sequenceImage.get(i).clearActions();
							//add points
							Global.score += 10;
							SoundManager.play(SoundManager.right);
							//remove bell
							bells.get(number).remove();
							//reset blinker
							blinker = 0;
						}
						else
						{
							//wrong bell pressed
							// Wei Kiat Code; Optimising Code;
							if( (Global.score -= 10) < 0 )	Global.score = 0;	
							Gdx.input.vibrate(5);
							SoundManager.play(SoundManager.wrong);
						}
						break;
					}
				}
				return true;
			}
		});
	}
}
