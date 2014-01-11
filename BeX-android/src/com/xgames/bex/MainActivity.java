package com.xgames.bex;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/* <Summary>
 * The Main Activity Class is a Android Activity Class.
 * It is used to ensure that when the user clicks on the icon,
 * the game will have the configuration settings when it is created.
 * </Summary>
 */
public class MainActivity extends AndroidApplication 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);   
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        
        cfg.useWakelock			= true;		// Wei Kiat code; Prevent sleep mode;
        cfg.useGL20 			= true;
        cfg.useAccelerometer	= false;
        cfg.useCompass 			= false;
        
        initialize(new BeX(), cfg);
    }
}