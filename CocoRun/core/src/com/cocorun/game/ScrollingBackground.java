package com.cocorun.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingBackground {
	
	protected static final int DEFAULT_SPEED = 1000;
	protected static final int ACCELERATION = 50;
	protected static final int MAX_SPEED_ACCELERATION = 100;
	
	private float x1, x2;
	private int speed;
	private int maxSpeed;
	private float imageScale;
	private boolean fixedSpeed;
	
	public ScrollingBackground() {
		x1 = 0;
		x2 = Assets.bgSprite.getWidth();
		imageScale = 0;
		
		speed = 0;
		maxSpeed = DEFAULT_SPEED;
		fixedSpeed = true;
		
	}
	
	public void updateAndRender(float delta, SpriteBatch batch) {
		if(speed < maxSpeed) {
			speed += MAX_SPEED_ACCELERATION * delta;
			if(speed > maxSpeed) {
				speed = maxSpeed;
			}
		} else if(speed > maxSpeed) {
			speed -= MAX_SPEED_ACCELERATION * delta;
			if(speed < maxSpeed) {
				speed = maxSpeed;
			}
		}
		
		if (!fixedSpeed) {
			speed += ACCELERATION * delta;
		}
		
		x1 -= speed * delta;
		x2 -= speed * delta;
		
		if (x1 + Assets.bgSprite.getWidth() * imageScale <= 0) {
			x1 = x2 + Assets.bgSprite.getWidth() * imageScale;
		}
		if (x2 + Assets.bgSprite.getWidth() * imageScale <= 0) {
			x2 = x1 + Assets.bgSprite.getWidth() * imageScale;
		}
		
		//Rendering
		//x2 = Assets.bgSprite.getWidth();
		float height = Gdx.graphics.getHeight();
		float width = (float)(Assets.bgSprite.getWidth() * imageScale);
		batch.draw(Assets.bgSprite, x1, 0, width, height);
		batch.draw(Assets.bgSprite, x2, 0, width, height);
	}
	
	public void resize (int width, int height) {
		imageScale = (float)(height / Assets.bgSprite.getHeight() * 1.45);
	}
	
	public void setSpeed (int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public void setFixedSpeed (boolean fixedSpeed) {
		this.fixedSpeed = fixedSpeed;
	}
}
