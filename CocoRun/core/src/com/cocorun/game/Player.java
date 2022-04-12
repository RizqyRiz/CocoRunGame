package com.cocorun.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Player {
	
	public Sprite sprite;
	public Rectangle bounds;
	
	/*
	 *  instantiating player object with a sprite and bounds stored as a Rectangle object
	 */
	public Player(int initialPosX, int initialPosY) {
		/*
		 *  stores the player character sprite
		 */
		sprite = Assets.coconutSprite;
		
		/*
		 *  generates and store the player character's hit box
		 */
		bounds = new Rectangle(initialPosX, initialPosY, 42, 42);
	}
	
}
	
	


