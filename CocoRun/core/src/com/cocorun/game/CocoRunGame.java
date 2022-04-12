package com.cocorun.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class CocoRunGame extends Game{

	public GameScreen gameScreen;
	public int highscore;
	
	@Override
	public void create() {
		//loads all assets that will be used in the game
		Assets.load();
		
		//save file handling
		Preferences saveFile = Gdx.app.getPreferences("CocorunSave");
		highscore = saveFile.getInteger("highscore", 0);
		
		//creates the game screen
		//gameScreen = new GameScreen(this);
		setScreen(new MainMenuScreen(this, highscore));
	}
	
}
