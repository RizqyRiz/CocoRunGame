package com.cocorun.game;

import com.badlogic.gdx.Gdx;
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
	//fonts
	public static BitmapFont font;
	
	//background
	public static Texture bgImage;
	public static Sprite bgSprite;
	
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
		FreeTypeFontGenerator font = new FreeTypeFontGenerator(Gdx.files.internal("Montserrat-Regular.ttf"));
		
		/*
		 * loads background image for in-game view
		 */
		bgImage = new Texture(Gdx.files.internal("background-cocorun.png"));
		//used so that the image isn't messed up when scaling
		bgImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		//converts the texture into a sprite which helps load faster
		bgSprite = new Sprite(bgImage);
		//flips the y-axis of the sprite
		bgSprite.flip(false, true);
		
		/*
		 * loads player textures and sprite
		 */
		coconutImage = new Texture(Gdx.files.internal("coconut64.png"));
		coconutImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		coconutSprite = new Sprite(coconutImage);
		coconutSprite.flip(false, true);
		
		/*
		 * loads obstacles textures and sprite
		 */
		carImage = new Texture(Gdx.files.internal("Car.png"));
		carImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		carSprite = new Sprite(carImage);
		carSprite.flip(false, true);
		
		boulderImage = new Texture(Gdx.files.internal("Boulder.png"));
		boulderImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		boulderSprite = new Sprite(boulderImage);
		boulderSprite.flip(false, true);
		
		potholeImage = new Texture(Gdx.files.internal("Pothole.png"));
		potholeImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		potholeSprite = new Sprite(potholeImage);
		potholeSprite.flip(false, true);
		
		/*
		 * loads background textures and sprite
		 */
		treeImage = new Texture(Gdx.files.internal("Tree.png"));
		treeImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		treeSprite = new Sprite(treeImage);
		treeSprite.flip(false, true);
		
		cloudImage = new Texture(Gdx.files.internal("Cloud.png"));
		cloudImage.setFilter(TextureFilter.Linear, TextureFilter.Linear );	
		cloudSprite = new Sprite(cloudImage);
		cloudSprite.flip(false, true);
	}
	
}
