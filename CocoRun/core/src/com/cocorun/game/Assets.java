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
	public static Texture title;
	
	//saved high score
	public static int highscore;
	
	//font setup
	public static BitmapFont font;
	
	//sounds
	public static Music BGM;
	public static Music btnPressSFX;
	public static Music collisionSFX;
	public static Music pausePopupSFX;
	
	//inactive buttons
	public static Texture playBtnImg;
	public static Texture guideBtnImg;
	public static Texture resumeBtnImg;
	public static Texture exitBtnImg;
	public static Texture pauseBtnImg;
	
	public static Sprite playBtnSprite;
	public static Sprite guideBtnSprite;
	public static Sprite resumeBtnSprite;
	public static Sprite exitBtnSprite;
	public static Sprite pauseBtnSprite;
	
	//active buttons
	public static Texture playActiveBtnImg;
	public static Texture guideActiveBtnImg;
	public static Texture resumeActiveBtnImg;
	public static Texture exitActiveBtnImg;
	public static Texture pauseActiveBtnImg;
	
	public static Sprite playActiveBtnSprite;
	public static Sprite guideActiveBtnSprite;
	public static Sprite resumeActiveBtnSprite;
	public static Sprite exitActiveBtnSprite;
	public static Sprite pauseActiveBtnSprite;
	
	//background
	public static Texture bgImage;
	public static Sprite bgSprite;
	
	//how-to-play guide
	public static Texture guideImg;
	public static Sprite guideSprite;	
	
	//dark layer on pause
	public static Texture darkAlphaImg;
	public static Sprite darkAlphaSprite;
	
	//player character
	public static Texture coconutImage;
	public static Sprite coconutSprite;
	
	//obstacles
	public static Texture carImage;
	public static Sprite carSprite;
	public static Texture boulderImage;
	public static Sprite boulderSprite;
	public static Texture potholeImage;
	public static Sprite potholeSprite;
	
	public static void load() {
		
		title = new Texture(Gdx.files.internal("Title.png"));
		
		Preferences saveFile = Gdx.app.getPreferences("CocorunSave");
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
