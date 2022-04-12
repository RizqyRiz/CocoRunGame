package com.cocorun.game;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	private Obstacle obstacle;
	private List<Obstacle> obstacles;
	private long lastObsTime;
	
	private int topLane; //= 240;	//480*0.5
	private int midLane; //= 336;	//480*0.7
	private int botLane; //= 432;	//480*0.9
	
	//used to check for game state
	private boolean paused;
	
	//will be used to keep track of time and points
	private float timeElapsed;
	private int points;
	//private int seconds;
	//private int minutes;
	//private int tempTimeCounter = 0;
	
	//randomizer setup
	private Random rand = new Random();
	
	
	
	public GameScreen(CocoRunGame game) {
		this.game = game;
		
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
		topLane = (int)(camera.viewportHeight*0.4);
		midLane = (int)(camera.viewportHeight*0.2) + 10;
		botLane = 15; 
		
		//create the SpriteBatch 
		batch = new SpriteBatch();
		
		//spawns the player on the middle lane
		int playerX = (int)(camera.viewportWidth*0.05);
		coconut = new Player(playerX,midLane);
		
		//initialize time elapsed and game timer since the game session starts
		timeElapsed = 0f;
		//seconds = 0;
		//minutes = 0;
		
		//initialize the obstacles array list and starts spawning them
		obstacles = new ArrayList<Obstacle>();
		spawnObstacles();
	}
	
	private void spawnObstacles() {
		//randomizes the position the obstacles will spawn on and instantiating the obstacle object
		int randomLane = rand.nextInt(3);
		int obsX = (int)camera.viewportWidth;
		if(randomLane == 2)
			obstacle = new Obstacle(obsX, topLane, 0);//rand.nextInt(3));
		else if (randomLane == 1) 
			obstacle = new Obstacle(obsX, midLane, 0);//rand.nextInt(3));
		else
			obstacle = new Obstacle(obsX, botLane, 0);//rand.nextInt(3));

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
		
		// handles what happens in each game state
		if(paused) {
			if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
				System.out.println("RESUME");
				paused = false;
			}
		} else {
			generalUpdate();
			//System.out.println(seconds);
		}
		
		//resizes the SpriteBatch to the screen size we set it, in this case 1920 x 1080
		batch.setProjectionMatrix(camera.combined);
		
		//starts drawing the SpriteBatch into the canvas
		batch.begin();
				batch.draw(Assets.bgSprite, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
				for (Obstacle obstacle : obstacles) {
					batch.draw(obstacle.sprite, obstacle.bounds.x, obstacle.bounds.y);
				}
				batch.draw(coconut.sprite, coconut.bounds.x, coconut.bounds.y);
				//Assets.font.draw(batch, "Time: " + String.format("%02d",minutes) + ":" + String.format("%02d",seconds), (float)(camera.viewportWidth/2.35), 30);
				//Assets.font.draw(batch, "Time: " + Float.toString(timeElapsed), (float)(camera.viewportWidth/2.35), 30);
				Assets.font.draw(batch, "Points earned: " + points, 10, camera.viewportHeight - 30);
				
				if(paused) {
					
				}
		batch.end();
	}
	
	//updates the SpriteBatch objects in the canvas 
	public void generalUpdate() {
		timeElapsed += Gdx.graphics.getDeltaTime();
		points = (int)(timeElapsed * 50);
		int tempSeconds = (int)timeElapsed;
		
		/*
		Assets.settings.putString("name", "Danial");
		Assets.settings.flush();
		Assets.name = Assets.settings.getString("name", "No name found");
		*/
		
		/*if(tempSeconds != 0 && tempSeconds%59 == 0) {
			seconds = tempSeconds;
			tempTimeCounter += 1;
			minutes = tempTimeCounter/60;
		}*/
		
		// configure pause button
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			System.out.println("PAUSED");
			paused = true;
		}
		
		//configure user input and controls
		if (Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (coconut.bounds.y == botLane) {
				coconut.bounds.y = midLane;
			} else if (coconut.bounds.y == midLane) {
				coconut.bounds.y = topLane;
			} else {
				coconut.bounds.y = topLane;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			
			if (coconut.bounds.y == topLane) {
				coconut.bounds.y = midLane;
			} else if (coconut.bounds.y == midLane) {
				coconut.bounds.y = botLane;
			} else {
				coconut.bounds.y = botLane;
			}
		}
		
		/*
		 *  check if we need to create a new obstacles
		 *   below invokes spawnObstacle method
		 *   every 0.5seconds (500000000 nanoseconds)
		 *   of in-game time
		 */
		if (TimeUtils.nanoTime() - lastObsTime > 500000000)
			spawnObstacles();
					
		// looping through the obstacles array list and moving them
		ListIterator<Obstacle> iter = obstacles.listIterator();
		while (iter.hasNext()) {
			Obstacle obstacle = iter.next();
			
			//speed modifiers
			float modExponential = (float) Math.pow(1.1, timeElapsed);
			float modLinear = timeElapsed;
			
			//calculate speed increase over time
			float baseSpeed = 5;		//set initial speed to 5 meters (pixel) per second
			float maxSpeed = 80;	//set speed limit to 30 meters (pixel) per second
			float displacement = baseSpeed + modLinear;
			if(displacement > maxSpeed) {
				displacement = maxSpeed;
			}
			
			//moves the obstacles towards the player (from right to left of screen)
			obstacle.bounds.x -=  displacement;//displacement; 
			if (obstacle.bounds.x + 64 < 0) {
				/*
				 * removes the obstacle once it misses the player and  hits the left side of the screen
				 */
				iter.remove();
			}
			if (obstacle.bounds.overlaps(coconut.bounds)) {
				System.out.println("Collision detected");
				game.setScreen(new GameOverScreen(game,points));
				dispose();
				//dropsGathered++;
				//dropSound.play();
				//paused = true;
				//iter.remove();
				//game.setScreen(new MainMenuScreen(game));
				//dispose();
			}
		}
		
	}

	@Override
	public void dispose() {
		
	}
	
	@Override
	//Executes once only (useful for game opening screen etc
	public void show() {
	}
	
	@Override
	//Not going to be used as we're using OrthographicCamera to rescale
	public void resize(int width, int height) {	
	}
	
	@Override
	//Only for android
	public void pause() {
	}

	@Override
	//only for android
	public void resume() {
	}

	@Override
	public void hide() {
	}
}
