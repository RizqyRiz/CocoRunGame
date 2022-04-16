package com.cocorun.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Player {
	
	private Sprite sprite;
	private Rectangle bounds;
	
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
	
	public void setY(float y) {
		this.bounds.y = y;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}
	
	public float getX() {
		return this.bounds.x;
	}
	
	public float getY() {
		return this.bounds.y;
	}
	
	public float getWidth() {
		return this.bounds.getWidth();
	}
	
	public float getHeight() {
		return this.bounds.getHeight();
	}
	
}
	
	


