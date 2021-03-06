package com.cocorun.game;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
	
	private CocoRunGame game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Viewport viewport;
	
	private Player coconut;
	private int playerX;
	
	private Obstacle obstacle;
	private List<Obstacle> obstacles;
	private long lastObsTime;
	
	private int topLane; 
	private int midLane; 
	private int botLane; 
	
	//used to check for game state
	private boolean paused;
	
	//will be used to keep track of time and points
	private float timeElapsed;
	private int points;
	private float spawnTime = 1000000000;
	
	//randomizer setup
	private Random rand = new Random();
	
	public GameScreen(CocoRunGame game) {
		this.game = game;
		
		game.scrollingBackground.setFixedSpeed(false);
		
		/*
		 * setToOrtho(false) = mathematical axis (y-axis: bottom -> top)
		 * setToOrtho(true) = most graphics processor axis (y-axis: top -> bottom)
		 * if set to false, make sure to flip your Textures and/or Sprite as well
		 */
		camera = new OrthographicCamera();
		camera.setToOrtho(false,1386, 756);
		
		//sets the background to fit the window size
		viewport = new StretchViewport(camera.viewportWidth, camera.viewportHeight);
		viewport.setCamera(camera);
		
		//configure lane coordinates based on window size
		topLane = (int)(camera.viewportHeight*0.37);
		midLane = (int)(camera.viewportHeight*0.2) + 10;
		botLane = 15; 
		
		//create the SpriteBatch 
		batch = new SpriteBatch();
		
		//spawns the player on the middle lane
		playerX = (int)(camera.viewportWidth*0.05);
		coconut = new Player(playerX,midLane);
		
		//initialize time elapsed and game timer since the game session starts
		timeElapsed = 0f;
		
		//initialize the obstacles array list and starts spawning them
		obstacles = new ArrayList<Obstacle>();
		spawnObstacles();
	}
	
	//handles obstacle instantiations
	private void spawnObstacles() {
		//randomizes the position the obstacles will spawn on and instantiating the obstacle object
		int randomLane = rand.nextInt(3);
		int obsX = (int)camera.viewportWidth;
		if(randomLane == 2)
			obstacle = new Obstacle(obsX, topLane, rand.nextInt(10));
		else if (randomLane == 1) 
			obstacle = new Obstacle(obsX, midLane, rand.nextInt(10));
		else
			obstacle = new Obstacle(obsX, botLane, rand.nextInt(10));

		//store the objects instantiated into the array list
		obstacles.add(obstacle);
		lastObsTime = TimeUtils.nanoTime();		
	}

	@Override
	public void render(float delta) {
		/*
		 * clearing the canvas and set it to white using RGBA
		 * need to both clear the canvas and to set a background
		 * color in case the background image fails to load
		 */
		ScreenUtils.clear(1f, 1f, 1f, 1f);
		
		camera.update();
		
		// handles what happens when the game is or isn't paused
		if(paused) {
			if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				Assets.pausePopupSFX.play();
				System.out.println("RESUME");
				paused = false;
			}
		} else {
			generalUpdate();
			System.out.println(spawnTime);
		}
		
		//resizes the SpriteBatch to the screen size we set it
		batch.setProjectionMatrix(camera.combined);
		
		//starts drawing the SpriteBatch into the canvas
		batch.begin();
				//batch.draw(Assets.bgSprite, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
				
				game.scrollingBackground.updateAndRender(delta, batch);
		
				for (Obstacle obstacle : obstacles) {
					batch.draw(obstacle.getSprite(), obstacle.getX(), obstacle.getY());
				}
				batch.draw(coconut.getSprite(), coconut.getX(), coconut.getY());
				Assets.font.draw(batch, "Points earned: " + points, 10, camera.viewportHeight - 30);
				
				float pauseBtnX = (camera.viewportWidth - 130);
				float pauseBtnY = (camera.viewportHeight - 130);
				if (Gdx.input.getX() < pauseBtnX + CocoRunGame.BTN_WIDTH && Gdx.input.getX() > pauseBtnX && camera.viewportHeight - Gdx.input.getY() < pauseBtnY + CocoRunGame.BTN_HEIGHT &&  camera.viewportHeight - Gdx.input.getY() > pauseBtnY) {
					batch.draw(Assets.pauseActiveBtnSprite, pauseBtnX, pauseBtnY, 128, 128);
					if(Gdx.input.justTouched()) {
						Assets.btnPressSFX.play();
						paused = true;
					}
				} else {
					batch.draw(Assets.pauseBtnSprite, pauseBtnX, pauseBtnY, 128, 128);
				}
				
				
				if(paused) {
					batch.draw(Assets.darkAlphaSprite, 0, 0);
					Assets.font.draw(batch, "Points earned: " + points, 10, camera.viewportHeight - 30);
					GlyphLayout pauseTextLayout = new GlyphLayout(Assets.font, "PAUSED", Color.WHITE, 0, Align.center, false);
					Assets.font.draw(batch, pauseTextLayout, camera.viewportWidth / 2, camera.viewportHeight / 2 + CocoRunGame.BTN_HEIGHT / 2 + 74);
					
					float btnX = (camera.viewportWidth / 2 - CocoRunGame.BTN_WIDTH / 2);
					float playBtnY = (float)(camera.viewportHeight * 0.4);
					float exitBtnY = (float)(camera.viewportHeight * 0.2);
					
					if (Gdx.input.getX() < btnX + CocoRunGame.BTN_WIDTH && Gdx.input.getX() > btnX && camera.viewportHeight - Gdx.input.getY() < playBtnY + CocoRunGame.BTN_HEIGHT &&  camera.viewportHeight - Gdx.input.getY() > playBtnY) {
						batch.draw(Assets.resumeActiveBtnSprite, btnX, playBtnY, CocoRunGame.BTN_WIDTH, CocoRunGame.BTN_HEIGHT);
						if(Gdx.input.justTouched()) {
							Assets.btnPressSFX.play();
							paused = false;
						}
					} else {
						batch.draw(Assets.resumeBtnSprite, btnX, playBtnY, CocoRunGame.BTN_WIDTH, CocoRunGame.BTN_HEIGHT);
					}
					
					if (Gdx.input.getX() < btnX + CocoRunGame.BTN_WIDTH && Gdx.input.getX() > btnX && camera.viewportHeight - Gdx.input.getY() < exitBtnY + CocoRunGame.BTN_HEIGHT &&  camera.viewportHeight - Gdx.input.getY() > exitBtnY) {
						batch.draw(Assets.exitActiveBtnSprite, btnX, exitBtnY, CocoRunGame.BTN_WIDTH, CocoRunGame.BTN_HEIGHT);
						if(Gdx.input.justTouched()) {
							Assets.btnPressSFX.play();
							game.setScreen(new MainMenuScreen(game, 0));
							dispose();
						}
					} else {
						batch.draw(Assets.exitBtnSprite, btnX, exitBtnY, CocoRunGame.BTN_WIDTH, CocoRunGame.BTN_HEIGHT);
					}
					
				}
		batch.end();
	}
	
	//updates the SpriteBatch objects in the canvas 
	public void generalUpdate() {
		timeElapsed += Gdx.graphics.getDeltaTime();
		points = (int)(timeElapsed * 50);

		// configure pause button
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Assets.pausePopupSFX.play();
			System.out.println("PAUSED");
			paused = true;
		}
		
		//configure user input and controls
		if (Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (coconut.getY() == botLane) {
				coconut.setY(midLane);
			} else if (coconut.getY() == midLane) {
				coconut.setY(topLane);
			} else {
				coconut.setY(topLane);
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			
			if (coconut.getY() == topLane) {
				coconut.setY(midLane);
			} else if (coconut.getY() == midLane) {
				coconut.setY(botLane);
			} else {
				coconut.setY(botLane);
			}
		}
		
		/*
		 *  check if we need to create a new obstacles
		 *   below invokes spawnObstacle method
		 *   every 0.5seconds (500000000 nanoseconds)
		 *   of in-game time and delay between each
		 *   spawn is decreased over time
		 */
		float spawnMod = TimeUtils.nanoTime() / 150000000;
		spawnTime -= spawnMod;
		if (spawnTime < 200000000) {
			spawnTime = 200000000;
		}
		if (TimeUtils.nanoTime() - lastObsTime > spawnTime) {
			spawnObstacles();
		}
					
		// looping through the obstacles array list and moving them
		ListIterator<Obstacle> iter = obstacles.listIterator();
		while (iter.hasNext()) {
			Obstacle obstacle = iter.next();
			
			//speed modifiers
			//float modExponential = (float) Math.pow(1.1, timeElapsed);
			float modLinear = timeElapsed;
			
			//calculate speed increase over time
			float baseSpeed = 5;		//set initial speed to 5 meters (pixel) per second
			float maxSpeed = 50;	//set speed limit to 200 meters (pixel) per second
			float displacement = baseSpeed + modLinear;
			if(displacement > maxSpeed) {
				displacement = maxSpeed;
			}
			
			//moves the obstacles towards the player (from right to left of screen)
			obstacle.setX(displacement); 
			if (obstacle.getX() + 64 < 0) {
				/*
				 * removes the obstacle once it misses the player and  hits the left side of the screen
				 */
				iter.remove();
			}
			if (obstacle.isOverlap(coconut.getBounds())) {
				System.out.println("Collision detected");
				Assets.collisionSFX.play();
				game.setScreen(new GameOverScreen(game,points));
				dispose();
			}
		}
		
	}

	@Override
	public void dispose() {}
	
	@Override
	public void show() {}
	
	@Override
	public void resize(int width, int height) {}
	
	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}
}
