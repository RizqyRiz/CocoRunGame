package com.cocorun.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameOverScreen implements Screen {

	private CocoRunGame game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Viewport viewport;
	
	private int score, highscore;
	private String newHigh = "";
	
	private Preferences saveFile;
	
	public GameOverScreen(CocoRunGame game, int score) {
		this.game = game;
		this.score = score;
		
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
		
		saveFile = Gdx.app.getPreferences("CocorunSave");
		this.highscore = Assets.highscore;
		
		//checks if current score beats highscore
		if (score > highscore) {
			highscore = score;
			newHigh = " (NEW!)";
			saveFile.putInteger("highscore", score);
			saveFile.flush();
		}

	}
	
	@Override
	public void render(float delta) {
		/*
		 * clearing the canvas and set it to white using RGBA
		 * need to both clear the canvas and to set a background
		 * color in case the background image fails to load
		 */
		ScreenUtils.clear(0, 0, 0, 0);
		
		camera.update();
		
		//resizes the SpriteBatch to the screen size we set it, in this case 1920 x 1080
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
				float btnX = (camera.viewportWidth / 2 - CocoRunGame.BTN_WIDTH / 2);
				float playBtnY = (float)(camera.viewportHeight * 0.4);
				float exitBtnY = (float)(camera.viewportHeight * 0.2);
				
				GlyphLayout gameOverLayout = new GlyphLayout(Assets.font, "GAME OVER!", Color.WHITE, 0, Align.center, false);
				GlyphLayout highScoreLayout = new GlyphLayout(Assets.font, "Highscore: " + highscore + newHigh, Color.WHITE, 0, Align.center, false);
				GlyphLayout scoreLayout = new GlyphLayout(Assets.font, "Score: " + score, Color.WHITE, 0, Align.center, false);
				Assets.font.draw(batch, gameOverLayout, camera.viewportWidth / 2, camera.viewportHeight / 2 + CocoRunGame.BTN_HEIGHT / 2 + 74);
				Assets.font.draw(batch, highScoreLayout, camera.viewportWidth / 2 , camera.viewportHeight / 2 + CocoRunGame.BTN_HEIGHT / 2 + 42);
				Assets.font.draw(batch, scoreLayout, camera.viewportWidth / 2 , camera.viewportHeight / 2 + CocoRunGame.BTN_HEIGHT / 2 + 10);
				
				if (Gdx.input.getX() < btnX + CocoRunGame.BTN_WIDTH && Gdx.input.getX() > btnX && camera.viewportHeight - Gdx.input.getY() < playBtnY + CocoRunGame.BTN_HEIGHT &&  camera.viewportHeight - Gdx.input.getY() > playBtnY) {
					batch.draw(Assets.playActiveBtnSprite, btnX, playBtnY, CocoRunGame.BTN_WIDTH, CocoRunGame.BTN_HEIGHT);
					if(Gdx.input.justTouched()) {
						Assets.btnPressSFX.play();
						game.setScreen(new GameScreen(game));
						dispose();
					}
				} else {
					batch.draw(Assets.playBtnSprite, btnX, playBtnY, CocoRunGame.BTN_WIDTH, CocoRunGame.BTN_HEIGHT);
				}
				
				if (Gdx.input.getX() < btnX + CocoRunGame.BTN_WIDTH && Gdx.input.getX() > btnX && camera.viewportHeight - Gdx.input.getY() < exitBtnY + CocoRunGame.BTN_HEIGHT &&  camera.viewportHeight - Gdx.input.getY() > exitBtnY) {
					batch.draw(Assets.exitActiveBtnSprite, btnX, exitBtnY, CocoRunGame.BTN_WIDTH, CocoRunGame.BTN_HEIGHT);
					if(Gdx.input.justTouched()) {
						Assets.btnPressSFX.play();
						game.setScreen(new MainMenuScreen(game, highscore));
						dispose();
					}
				} else {
					batch.draw(Assets.exitBtnSprite, btnX, exitBtnY, CocoRunGame.BTN_WIDTH, CocoRunGame.BTN_HEIGHT);
				}
				
		batch.end();
		
	}
	
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

	@Override
	public void dispose() {}

}
