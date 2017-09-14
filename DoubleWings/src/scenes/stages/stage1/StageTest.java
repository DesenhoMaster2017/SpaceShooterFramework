package scenes.stages.stage1;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.Sprite;
import constants.WindowConstants;
import scenes.GameScene;

public class StageTest extends GameScene {

	private Sprite player;
	private GameImage background;

	public void initialSetup(){

		//Loading background image
		background = new GameImage("src/assets/img/temp_background.png");

		//Creating player sprite
		player = new Sprite("src/assets/img/temp_player.png");

		//Putting player on the center-bottom of the screen
		player.x = WindowConstants.WIDTH/2 - player.width/2;
		player.y = WindowConstants.HEIGHT - player.height;

	}

	Shield shield = new Shield();

	public void update(){

		//Draw the images for the game
		// background.draw();
		player.draw();
		shield.draw();

		//Player movement
		player.moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, 4);//velocity = 1
		player.moveY(Keyboard.UP_KEY, Keyboard.DOWN_KEY, 4);//velocity = 1
	}
}
