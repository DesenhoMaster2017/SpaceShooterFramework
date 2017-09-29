package scenes.stages.stage1;

import game.GameController;
import game.World;
import entity.*;

import jplay.GameImage;
import jplay.Keyboard;
import entity.GameEntity;
import constants.WindowConstants;
import scenes.GameScene;

public class StageTest extends GameScene {

	private GameEntity player;
	private GameImage background;
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

		configureEntities();
	}

	private void configureEntities(){
		//Creating player sprite
		player = new Player("src/assets/img/temp_player.png");
		player.setLife(5);

		//Putting player on the center-bottom of the screen
		player.x = WindowConstants.WIDTH/2 - player.width/2;
		player.y = WindowConstants.HEIGHT - player.height;

		// In this way we can collect all the HUD values directly from the player
		Shield shield = new Shield(player);
		shield.setLife(10);
		player.getShield(shield);

		Enemy asteroid1 = new Enemy("src/assets/img/asteroid.png");
		asteroid1.setLife(10);
		asteroid1.x = WindowConstants.WIDTH/2 - asteroid1.width/2;
		asteroid1.y = 0;
		asteroid1.vely = 2.0;

		Enemy asteroid2 = new Enemy("src/assets/img/asteroid.png");
		asteroid2.setLife(10);
		asteroid2.x = WindowConstants.WIDTH/2 - asteroid2.width/2;
		asteroid2.y = -200;
		asteroid2.vely = 2.0;

		gameWorld.add(asteroid1);
		gameWorld.add(asteroid2);

		gameWorld.add(shield);
		gameWorld.add(player);
	}

	@Override
	public void update(){

		background.draw();
		gameWorld.update(); // Updates and draw all entities added in game world

		//Player movement
		player.moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, 4);//velocity = 1
		player.moveY(Keyboard.UP_KEY, Keyboard.DOWN_KEY, 4);//velocity = 1

	}
}
