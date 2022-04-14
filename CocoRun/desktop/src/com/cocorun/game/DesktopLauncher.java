package com.cocorun.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//initialize and configure game window
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("CocoRun");
		config.setForegroundFPS(60);
		config.useOpenGL3(true,4, 2);
		config.setWindowedMode(1344, 756);
		config.setResizable(false);
		new Lwjgl3Application(new CocoRunGame(), config);
	}
}
