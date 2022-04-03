package com.cocorun.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Obstacle {
	
	public Sprite sprite;
	public Rectangle bounds;
	
	/*
	 *  instantiating obstacles object with a sprite and bounds stored as a Rectangle object
	 */
	public Obstacle(int initialPosY, int type) {
		/*
		 * random generated integer between 0-2 is
		 * passed through the constructor and used
		 * as a determinant for obstacle's sprite
		 */
		switch(type) {
		case 0: sprite = Assets.carSprite;break;
		case 1: sprite = Assets.boulderSprite;break;
		case 2: sprite = Assets.potholeSprite;break;
		}
		
		/*
		 *  generates and store the obstacle's hit box
		 */
		bounds = new Rectangle(800, initialPosY, 64, 64);
	}

}
