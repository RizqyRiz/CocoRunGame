package com.cocorun.game;

import com.badlogic.gdx.Game;

public class CocoRunGame extends Game{
	
	protected static final int BTN_WIDTH = 192;
	protected static final int BTN_HEIGHT = 108;
	protected static final int WIDTH = 1920;
	protected static final int HEIGHT = 1080;
	
	protected ScrollingBackground scrollingBackground;

	@Override
	public void create() {
		//loads all assets that will be used in the game
		Assets.load();
		
		//initialize a scrolling background
		scrollingBackground = new ScrollingBackground();

		//instantiates the main menu screen
		setScreen(new MainMenuScreen(this, Assets.highscore));
	}
	
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void resize (int width, int height) {
		this.scrollingBackground.resize(width, height);
		super.resize(width, height);
	}
	
}
