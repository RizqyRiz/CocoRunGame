package com.cocorun.game;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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

public class MainMenuScreen implements Screen {

	private CocoRunGame game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Viewport viewport;
	private int highscore;
	
	private Obstacle obstacle;
	private List<Obstacle> obstacles;
	private long lastObsTime;
	
	private int topLane; 
	private int midLane; 
	private int botLane;
	
	//randomizer setup
	private Random rand = new Random();
	
	private static final int btnWidth = 192;
	private static final int btnHeight = 108;
	
	//private static final int playBtnY = 100;
	
	public MainMenuScreen(CocoRunGame game, int highscore) {
		this.game = game;
		
		//save file handling
		Preferences saveFile = Gdx.app.getPreferences("CocorunSave");
		this.highscore = saveFile.getInteger("highscore", 0);
		
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
		
		batch = new SpriteBatch();
		
		//configure lane coordinates based on window size
		topLane = (int)(camera.viewportHeight*0.4);
		midLane = (int)(camera.viewportHeight*0.2) + 10;
		botLane = 15; 
		
		obstacles = new ArrayList<Obstacle>();
		spawnObstacles();
	}
	
	private void spawnObstacles() {
		//randomizes the position the obstacles will spawn on and instantiating the obstacle object
		int randomLane = rand.nextInt(3);
		int obsX = (int)camera.viewportWidth;
		if(randomLane == 2)
			obstacle = new Obstacle(obsX, topLane, 3);
		else if (randomLane == 1) 
			obstacle = new Obstacle(obsX, midLane, 3);
		else
			obstacle = new Obstacle(obsX, botLane, 3);

		//store the objects instantiated into the array list
		obstacles.add(obstacle);
		lastObsTime = TimeUtils.nanoTime();		
	}
	
	@Override
	public void show() {
		Assets.BGM.play();
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
		
		backgroundUpdate();
		
		//resizes the SpriteBatch to the screen size we set it, in this case 1920 x 1080
		batch.setProjectionMatrix(camera.combined);
		
		//starts drawing the SpriteBatch into the canvas
		batch.begin();
		
				batch.draw(Assets.bgSprite, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
				batch.draw(Assets.treeSprite, 10, viewport.getWorldHeight() / 2 + 5);
				
				for (Obstacle obstacle : obstacles) {
					batch.draw(obstacle.sprite, obstacle.bounds.x, obstacle.bounds.y);
				}
				
				GlyphLayout gameTitleLayout = new GlyphLayout(Assets.font, "Run, Coco! Run!", Color.BLACK, 0, Align.center, false);
				GlyphLayout highScoreLayout = new GlyphLayout(Assets.font, "Highscore: " + highscore, Color.BLACK, 0, Align.center, false);
				Assets.font.draw(batch, gameTitleLayout, camera.viewportWidth / 2, (float)(camera.viewportHeight * 0.8));
				Assets.font.draw(batch, highScoreLayout, camera.viewportWidth / 2 , (float)(camera.viewportHeight * 0.8) - 32);
				
				/*
				 * configures x-coordinates of all button to center of screen
				 * configures y-coordinates of different buttons
				 */
				float btnX = (camera.viewportWidth / 2 - btnWidth / 2);
				float playBtnY = (float)(camera.viewportHeight * 0.4);
				float exitBtnY = (float)(camera.viewportHeight * 0.2);
				
				if (Gdx.input.getX() < btnX + btnWidth && Gdx.input.getX() > btnX && camera.viewportHeight - Gdx.input.getY() < playBtnY + btnHeight &&  camera.viewportHeight - Gdx.input.getY() > playBtnY) {
					batch.draw(Assets.playActiveBtnSprite, btnX, playBtnY, btnWidth, btnHeight);
					if(Gdx.input.justTouched()) {
						Assets.btnPressSFX.play();
						game.setScreen(new GameScreen(game));
						dispose();
					}
				} else {
					batch.draw(Assets.playBtnSprite, btnX, playBtnY, btnWidth, btnHeight);
				}
				
				if (Gdx.input.getX() < btnX + btnWidth && Gdx.input.getX() > btnX && camera.viewportHeight - Gdx.input.getY() < exitBtnY + btnHeight &&  camera.viewportHeight - Gdx.input.getY() > exitBtnY) {
					batch.draw(Assets.exitActiveBtnSprite, btnX, exitBtnY, btnWidth, btnHeight);
					if(Gdx.input.justTouched()) {
						Assets.btnPressSFX.play();
						try {
							Thread.sleep(500);
							dispose();
							System.exit(1);
						} catch (Exception e) {
							System.out.println(e);
						}
					}
				} else {
					batch.draw(Assets.exitBtnSprite, btnX, exitBtnY, btnWidth, btnHeight);
				}
			
		batch.end();
		
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
		}
		
	}
	
	public void backgroundUpdate() {
		if (TimeUtils.nanoTime() - lastObsTime > 500000000)
			spawnObstacles();
					
		// looping through the obstacles array list and moving them
		ListIterator<Obstacle> iter = obstacles.listIterator();
		while (iter.hasNext()) {
			Obstacle obstacle = iter.next();

			//moves the obstacles from right to left of screen
			obstacle.bounds.x -=  30;
			if (obstacle.bounds.x + 64 < 0) {
				/*
				 * removes the obstacle once it misses the player and  hits the left side of the screen
				 */
				iter.remove();
			}
		}
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}

}
