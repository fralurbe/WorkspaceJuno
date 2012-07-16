package com.fracarlu.runjumprun.Asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fracarlu.runjumprun.Tools.LevelParser;

// *@Clase para cargar los assets
public class Assets 
{   
	private static final int FRAME_COLS = 6;         
    private static final int FRAME_ROWS = 5;         

    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT = 32;
    
    private static final String PATH_NIVELES = "levels.txt";       
    
    public static BitmapFont fuente;
    
	public static Texture spritesheet;
	public static TextureRegion fondogeneral;
		       
	public static TextureRegion play;
	public static TextureRegion pause;
	public static TextureRegion sound;
	public static TextureRegion help;
	public static TextureRegion highscores;
	public static TextureRegion gameover;
	public static TextureRegion ready;
	public static TextureRegion resume;
	public static TextureRegion quit;
	
	public static TextureRegion floor;
	
	public static TextureRegion quad_1;
	public static TextureRegion quad_2;
	public static TextureRegion quad_3;
	public static TextureRegion quad_4;
	public static TextureRegion quad_5;
	public static TextureRegion quad_6;
	public static TextureRegion quad_7;
	public static TextureRegion quad_8;
	
	public static TextureRegion platform;
	
	public static Texture spritesheet_run;
	public static TextureRegion[] runFrames;
	public static TextureRegion[] jumpFrames;
	public static Animacion corredorCorrer;	
	public static Animacion corredorSaltar;
	public static TextureRegion corredorChocar;
	
	public static Music music;
	public static Sound jumpSound;
	public static Sound highJumpSound;
	public static Sound hitSound;
	public static Sound clickSound;
	
	public static String levelsfilecontent;
	
	public static Texture loadTexture (String file) 
	{
		return new Texture(Gdx.files.internal(file));
	}
	
	public static void load () 
	{		
		spritesheet = loadTexture("spritesheet512x512.png");
		fondogeneral = new TextureRegion(spritesheet, 0, 64, 320, 480);
		play = new TextureRegion(spritesheet, 0, 0, 128, 64 );
		sound = new TextureRegion(spritesheet, 128, 0, 128, 64 );
		help = new TextureRegion(spritesheet, 128 * 2, 0, 128, 64 );
		pause = new TextureRegion(spritesheet, 128 * 3, 0,128, 64 );		
		ready = new TextureRegion(spritesheet, 320, 64, 128, 64 );
		quit = new TextureRegion(spritesheet, 320, 64 * 2, 128, 64 );
		resume = new TextureRegion(spritesheet, 320,  64 * 3, 128, 64 );
		gameover = new TextureRegion(spritesheet, 320,  64*4, 128, 64 );
		highscores = new TextureRegion(spritesheet, 320, 64*5,  128, 64 );
		
		quad_1 = new TextureRegion(spritesheet, 448, 				TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT );
		quad_2 = new TextureRegion(spritesheet, 448 + TILE_WIDTH,	TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT );
		quad_3 = new TextureRegion(spritesheet, 448, 				TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT );
		quad_4 = new TextureRegion(spritesheet, 448 + TILE_WIDTH, 	TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT );
		quad_5 = new TextureRegion(spritesheet, 448, 				TILE_HEIGHT * 4, TILE_WIDTH, TILE_HEIGHT );
		quad_6 = new TextureRegion(spritesheet, 448 + TILE_WIDTH, 	TILE_HEIGHT * 4, TILE_WIDTH, TILE_HEIGHT );
		quad_7 = new TextureRegion(spritesheet, 448, 				TILE_HEIGHT * 5, TILE_WIDTH, TILE_HEIGHT );
		quad_8 = new TextureRegion(spritesheet, 448 + TILE_WIDTH, 	TILE_HEIGHT * 5, TILE_WIDTH, TILE_HEIGHT );
		
		platform = new TextureRegion(spritesheet, 448, 64+(TILE_HEIGHT * 4), TILE_WIDTH, TILE_HEIGHT);
		
		
		// spritesheet del tio corriendo
		spritesheet_run = loadTexture("sprite_animation.png");
        TextureRegion[][] tmp = TextureRegion.split(
        		spritesheet_run, 
        		spritesheet_run.getWidth() / FRAME_COLS,
        		spritesheet_run.getHeight() / FRAME_ROWS);                                
        runFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) 
        {
                for (int j = 0; j < FRAME_COLS; j++) {
                	runFrames [index++] = tmp[i][j];
                }
        }
        corredorCorrer = new Animacion(0.025f, runFrames);
        
        jumpFrames = new TextureRegion[FRAME_COLS];
        for (int j = 0; j < FRAME_COLS; j++) {
        	jumpFrames [j] = runFrames[runFrames.length - FRAME_COLS - 1 + j];
        }
        corredorSaltar= new Animacion(0.025f, jumpFrames);
        
        corredorChocar = new TextureRegion(tmp[0][0]);
        
        fuente = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);

		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		//if (Settings.soundEnabled) music.play();
		jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.ogg"));
		highJumpSound = Gdx.audio.newSound(Gdx.files.internal("highjump.ogg"));
		hitSound = Gdx.audio.newSound(Gdx.files.internal("hit.ogg"));		
		clickSound = Gdx.audio.newSound(Gdx.files.internal("click.ogg"));
		levelsfilecontent = LeerFicheroDefinicionNiveles();
		LevelParser levelparser = new LevelParser(levelsfilecontent);
		levelparser.ParseLevel(0);
		
	}

	public static String LeerFicheroDefinicionNiveles() 
	{		
		String fileContent;
		String path = PATH_NIVELES;
		try
		{
	        FileHandle  handle = Gdx.files.internal(path);	       	       
	        fileContent = handle.readString();
		}
		catch (Exception ex)
		{
			return "";
		}
        return fileContent;	   
	}	
	
	public static void playSound (Sound sound) {
		//if (Settings.soundEnabled) sound.play(1);
	}

}
