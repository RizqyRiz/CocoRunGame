package com.cocorun.game;

import com.badlogic.gdx.Game;

public class CocoRunGame extends Game{

	public GameScreen gameScreen;
	
	@Override
	public void create() {
		//loads all assets that will be used in the game
		Assets.load();
		
		//creates the game screen
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
	
}
