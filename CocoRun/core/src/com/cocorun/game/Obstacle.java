package com.cocorun.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Obstacle {
	
	private Sprite sprite;
	private Rectangle bounds;
	private int typeID;
	
	/*
	 *  instantiating obstacles object with a sprite and bounds stored as a Rectangle object
	 */
	public Obstacle(int initialPosX, int initialPosY, int typeID) {
		/*
		 * random generated integer between 0-2 is
		 * passed through the constructor and used
		 * as a determinant for obstacle's sprite
		 */
		switch(typeID) {
		//case 0: sprite = Assets.carSprite;break;
		case 0: sprite = Assets.boulderSprite;break;
		case 1: sprite = Assets.potholeSprite;break;
		default: sprite = Assets.carSprite; 
		}
		
		/*
		 *  generates and store the obstacle's hit box
		 */
		bounds = new Rectangle(initialPosX, initialPosY, 64, 64);
	}
	
	public void setX(float x) {
		this.bounds.x -= x;
	}
	
	public Sprite getSprite() {
		return this.sprite;
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
	
	public int getTypeID() {
		return this.typeID;
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}
	
	public boolean isOverlap(Rectangle bounds) {
		if(this.bounds.overlaps(bounds)) {
			return true;
		}
		return false;
	};

}
