package com.cocorun.game;

import com.badlogic.gdx.Gdx;
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
	
	//font setup
	public static BitmapFont font;
	
	//sounds
	public static Music BGM;
	
	//inactive buttons
	public static Texture playBtnImg;
	public static Texture resumeBtnImg;
	public static Texture exitBtnImg;
	public static Sprite playBtnSprite;
	public static Sprite resumeBtnSprite;
	public static Sprite exitBtnSprite;
	
	//active buttons
	public static Texture playActiveBtnImg;
	public static Texture resumeActiveBtnImg;
	public static Texture exitActiveBtnImg;
	public static Sprite playActiveBtnSprite;
	public static Sprite resumeActiveBtnSprite;
	public static Sprite exitActiveBtnSprite;
	
	//background
	public static Texture bgImage;
	public static Sprite bgSprite;
	
	//dark layer on pause
	public static Texture darkAlphaImg;
	public static Sprite darkAlphaSprite;
	
	//background objects
	public static Texture treeImage;
	public static Sprite treeSprite;
	public static Texture cloudImage;
	public static Sprite cloudSprite;
	
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
		
		/*
		 * Configures the font we'll be using
		 */
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Montserrat-Regular.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 32;
		parameter.color = Color.valueOf("ffffff");
		parameter.borderWidth = 1;
		font = gen.generateFont(parameter);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		/*
		 * Configure background music
		 */
		BGM = Gdx.audio.newMusic(Gdx.files.internal("Sound/bgm.mp3"));
		BGM.setLooping(true);
		
		
		/*
		 * loads inactive buttons
		 */
		playBtnImg = new Texture(Gdx.files.internal("Buttons/play.png"));
		playBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		playBtnSprite = new Sprite(playBtnImg);
		
		exitBtnImg = new Texture(Gdx.files.internal("Buttons/exit.png"));
		exitBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		exitBtnSprite = new Sprite(exitBtnImg);
		
		resumeBtnImg = new Texture(Gdx.files.internal("Buttons/resume.png"));
		resumeBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		resumeBtnSprite = new Sprite(resumeBtnImg);
		
		
		/*
		 * loads active buttons
		 */
		playActiveBtnImg = new Texture(Gdx.files.internal("Buttons/playActive.png"));
		playActiveBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		playActiveBtnSprite = new Sprite(playActiveBtnImg);
		
		exitActiveBtnImg = new Texture(Gdx.files.internal("Buttons/exitActive.png"));
		exitActiveBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		exitActiveBtnSprite = new Sprite(exitActiveBtnImg);
		
		resumeActiveBtnImg = new Texture(Gdx.files.internal("Buttons/resumeActive.png"));
		resumeActiveBtnImg.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		resumeActiveBtnSprite = new Sprite(resumeActiveBtnImg);

		
		/*
		 * loads background image for in-game view
		 */
		bgImage = new Texture(Gdx.files.internal("background-cocorun.png"));
		//used so that the image isn't messed up when scaling
		bgImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		//converts the texture into a sprite which helps load faster
		bgSprite = new Sprite(bgImage);
		
		
		/*
		 * loads a darkened layer for pause screen
		 */
		darkAlphaImg = new Texture(Gdx.files.internal("black-alpha.png"));
		darkAlphaImg.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		darkAlphaSprite = new Sprite(darkAlphaImg);
		
		
		/*
		 * loads player textures and sprite
		 */
		coconutImage = new Texture(Gdx.files.internal("Player/cursed_angy.png"));
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
		
		
		/*
		 * loads background textures and sprite
		 */
		treeImage = new Texture(Gdx.files.internal("Tree.png"));
		treeImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		treeSprite = new Sprite(treeImage);
		
		cloudImage = new Texture(Gdx.files.internal("Cloud.png"));
		cloudImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		cloudSprite = new Sprite(cloudImage);
	}
	
}
