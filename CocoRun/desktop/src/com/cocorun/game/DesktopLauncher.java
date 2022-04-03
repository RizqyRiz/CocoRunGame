package com.cocorun.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.cocorun.game.CocoRunGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//initialize and configure game window
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("CocoRun");
		config.setForegroundFPS(60);
		config.useOpenGL3(false,4, 2);
		config.setWindowedMode(800, 480);
		new Lwjgl3Application(new CocoRunGame(), config);
	}
}
