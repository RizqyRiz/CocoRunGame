package com.cocorun.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


/*
 *  This class is used to load anything 
 *  media related such as fonts, icons,
 *  textures, sprite, audio, animation
 */

public class Assets {
	//game title
	protected static Texture title;
	
	//save handling
	protected static Preferences saveFile;
	protected static int highscore;
	
	//font setup
	protected static BitmapFont font;
	
	//sounds
	protected static Music BGM;
	protected static Music btnPressSFX;
	protected static Music collisionSFX;
	protected static Music pausePopupSFX;
	
	//inactive buttons
	protected static Texture playBtnImg;
	protected static Texture guideBtnImg;
	protected static Texture resumeBtnImg;
	protected static Texture exitBtnImg;
	protected static Texture pauseBtnImg;
	
	protected static Sprite playBtnSprite;
	protected static Sprite guideBtnSprite;
	protected static Sprite resumeBtnSprite;
	protected static Sprite exitBtnSprite;
	protected static Sprite pauseBtnSprite;
	
	//active buttons
	protected static Texture playActiveBtnImg;
	protected static Texture guideActiveBtnImg;
	protected static Texture resumeActiveBtnImg;
	protected static Texture exitActiveBtnImg;
	protected static Texture pauseActiveBtnImg;
	
	protected static Sprite playActiveBtnSprite;
	protected static Sprite guideActiveBtnSprite;
	protected static Sprite resumeActiveBtnSprite;
	protected static Sprite exitActiveBtnSprite;
	protected static Sprite pauseActiveBtnSprite;
	
	//background
	protected static Texture bgImage;
	protected static Sprite bgSprite;
	
	//how-to-play guide
	protected static Texture guideImg;
	protected static Sprite guideSprite;	
	
	//dark layer on pause
	protected static Texture darkAlphaImg;
	protected static Sprite darkAlphaSprite;
	
	//player character
	protected static Texture coconutImage;
	protected static Sprite coconutSprite;
	
	//obstacles
	protected static Texture carImage;
	protected static Sprite carSprite;
	protected static Texture boulderImage;
	protected static Sprite boulderSprite;
	protected static Texture potholeImage;
	protected static Sprite potholeSprite;
	
	public static void load() {
		
		title = new Texture(Gdx.files.internal("Title.png"));
		
		saveFile = Gdx.app.getPreferences("CocorunSave");
		highscore = saveFile.getInteger("highscore", 0);
		
		/*
		 * Configures the font we'll be using
		 */
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Impacted.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 32;
		parameter.color = Color.valueOf("ffffff");
		parameter.borderWidth = 1;
		font = gen.generateFont(parameter);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		/*
		 * Configure background music and sfx
		 */
		BGM = Gdx.audio.newMusic(Gdx.files.internal("Sound/bgm.mp3"));
		BGM.setLooping(true);
		btnPressSFX = Gdx.audio.newMusic(Gdx.files.internal("Sound/btn_click_1.wav"));
		collisionSFX = Gdx.audio.newMusic(Gdx.files.internal("Sound/collision_4.mp3"));
		pausePopupSFX = Gdx.audio.newMusic(Gdx.files.internal("Sound/pause_open_3.wav"));
		
		
		/*
		 * loads inactive buttons
		 */
		playBtnImg = new Texture(Gdx.files.internal("Buttons/play_inactive.png"));
		playBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		playBtnSprite = new Sprite(playBtnImg);
		
		guideBtnImg = new Texture(Gdx.files.internal("Buttons/guide_inactive.png"));
		guideBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		guideBtnSprite = new Sprite(guideBtnImg);
		
		exitBtnImg = new Texture(Gdx.files.internal("Buttons/exit_inactive.png"));
		exitBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		exitBtnSprite = new Sprite(exitBtnImg);
		
		resumeBtnImg = new Texture(Gdx.files.internal("Buttons/resume_inactive.png"));
		resumeBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		resumeBtnSprite = new Sprite(resumeBtnImg);
		
		pauseBtnImg = new Texture(Gdx.files.internal("Buttons/pause_inactive.png"));
		pauseBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		pauseBtnSprite = new Sprite(pauseBtnImg);
		
		
		/*
		 * loads active buttons
		 */
		playActiveBtnImg = new Texture(Gdx.files.internal("Buttons/play_active.png"));
		playActiveBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		playActiveBtnSprite = new Sprite(playActiveBtnImg);
		
		guideActiveBtnImg = new Texture(Gdx.files.internal("Buttons/guide_active.png"));
		guideActiveBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		guideActiveBtnSprite = new Sprite(guideActiveBtnImg);
		
		exitActiveBtnImg = new Texture(Gdx.files.internal("Buttons/exit_active.png"));
		exitActiveBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		exitActiveBtnSprite = new Sprite(exitActiveBtnImg);
		
		resumeActiveBtnImg = new Texture(Gdx.files.internal("Buttons/resume_active.png"));
		resumeActiveBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		resumeActiveBtnSprite = new Sprite(resumeActiveBtnImg);

		pauseActiveBtnImg = new Texture(Gdx.files.internal("Buttons/pause_active.png"));
		pauseActiveBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		pauseActiveBtnSprite = new Sprite(pauseActiveBtnImg);

		
		/*
		 * loads background image for in-game view
		 */
		bgImage = new Texture(Gdx.files.internal("Backgrounds/background_with_items.png"));
		//used so that the image isn't messed up when scaling
		bgImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		//converts the texture into a sprite which helps load faster
		bgSprite = new Sprite(bgImage);
		
		
		/*
		 * loads a darkened layer for pause screen
		 */
		darkAlphaImg = new Texture(Gdx.files.internal("Backgrounds/black-alpha.png"));
		darkAlphaImg.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		darkAlphaSprite = new Sprite(darkAlphaImg);
		
		/*
		 * loads how-to-play guide window image
		 */
		guideImg = new Texture(Gdx.files.internal("Backgrounds/guide.png"));
		guideImg.setFilter(TextureFilter.Linear, TextureFilter.Linear );
		guideSprite = new Sprite(guideImg);
		
		
		/*
		 * loads player textures and sprite
		 */
		coconutImage = new Texture(Gdx.files.internal("Player/cursed.png"));
		coconutImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		coconutSprite = new Sprite(coconutImage);
		
		
		/*
		 * loads obstacles textures and sprite
		 */
		carImage = new Texture(Gdx.files.internal("Obstacle/Car.png"));
		carImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		carSprite = new Sprite(carImage);
		
		boulderImage = new Texture(Gdx.files.internal("Obstacle/Boulder.png"));
		boulderImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		boulderSprite = new Sprite(boulderImage);
		
		potholeImage = new Texture(Gdx.files.internal("Obstacle/Pothole.png"));
		potholeImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		potholeSprite = new Sprite(potholeImage);
		
	}
	
}
