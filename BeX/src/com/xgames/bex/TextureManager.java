package com.xgames.bex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/* <Summary>
 * The TextureManager handles all the
 * notes and bells images for the levels.
 * </Summary>
 */
public class TextureManager 
{
	//Textures for the sequence
	public Texture ATex, BTex, CTex, DTex, ETex;
	
	public Array<Texture> notes;

	public TextureManager()
	{
		Gdx.app.debug("TextureManager: ","Initalizing..");
		
		ATex = new Texture("playMenu/bells/whitebell.png");
		BTex = new Texture("playMenu/bells/redbell.png");
		CTex = new Texture("playMenu/bells/silverbell.png");
		DTex = new Texture("playMenu/bells/goldbell.png");
		ETex = new Texture("playMenu/bells/bluebell.png");		
		
		notes = new Array<Texture>(10);
		notes.add(new Texture("playMenu/musicalnotes/doublenote.png"));
		notes.add(new Texture("playMenu/musicalnotes/doublerest.png"));
		notes.add(new Texture("playMenu/musicalnotes/doublesnote.png"));
		notes.add(new Texture("playMenu/musicalnotes/longwholenote.png"));
		notes.add(new Texture("playMenu/musicalnotes/restp.png"));
		notes.add(new Texture("playMenu/musicalnotes/rests.png"));
		notes.add(new Texture("playMenu/musicalnotes/singlenote1.png"));
		notes.add(new Texture("playMenu/musicalnotes/singlenote2.png"));
		notes.add(new Texture("playMenu/musicalnotes/singlerest.png"));
		notes.add(new Texture("playMenu/musicalnotes/wholenote.png"));
	}
	
	public void dispose()
	{
		ATex.dispose();
		BTex.dispose();
		CTex.dispose();
		DTex.dispose();
		ETex.dispose();
		
		while(notes.size > 0)	notes.pop();	// Wei Kiat Code; Refactor to shorten it;
	}
}
