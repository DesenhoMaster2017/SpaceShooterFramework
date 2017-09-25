package scenes.stages.stage1;

import game.GameController;
import game.World;

import jplay.GameImage;
import jplay.Keyboard;
import Entity.GameEntity;
import constants.WindowConstants;
import scenes.GameScene;

public class StageTest extends GameScene {

	private GameEntity player;
	private GameImage background;
	private Shield shield;
	private World gameWorld;
    
	@Override
	public void initialSetup(GameController game){
		
		gameWorld = new World();
		//Set game controller elements
		this.game = game;
		this.keyboard = game.keyboard;
		
		//Configure up and down keys
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_EVERY_PRESS);
		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_EVERY_PRESS);
		
		//Loading background image
		background = new GameImage("src/assets/img/temp_background.png");

		//Creating player sprite
		player = new GameEntity("src/assets/img/temp_player.png");

		//Putting player on the center-bottom of the screen
		player.x = WindowConstants.WIDTH/2 - player.width/2;
		player.y = WindowConstants.HEIGHT - player.height;

		shield = new Shield(player);
		
		gameWorld.add(player);
		
	}
	
	@Override
	public void update(){

		//Draw the images for the game
		background.draw();
		player.draw();
		shield.draw();

		//Player movement
		player.moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, 4);//velocity = 1
		player.moveY(Keyboard.UP_KEY, Keyboard.DOWN_KEY, 4);//velocity = 1
		
		//Active o method update from Shield following the player 
		shield.update();

	}
}
