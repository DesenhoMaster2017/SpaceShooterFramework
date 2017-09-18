package scenes;
import java.util.ArrayList;

import constants.WindowConstants;
import game.GameController;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Sprite;

public class ClassicContinue extends GameScene {
	
	// Sprites on scene
	private GameImage background;
	private Sprite gameOver;
	private Sprite wantToContinue;
	private ArrayList<Sprite> countDown = new ArrayList<Sprite>();
	
	
	public void initialSetup(GameController game) {
		
		//Set game controller elements
		this.game = game;
		keyboard = game.keyboard;
		
		//Configure enter key and escape
		keyboard.setBehavior(keyboard.ENTER_KEY, keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(keyboard.ESCAPE_KEY, keyboard.DETECT_INITIAL_PRESS_ONLY);
		
		//background = new GameImage("src/assets/img/temp_background.png");
		
		//Define scenes elements position
		//Continue sprite upper-center position
		wantToContinue = new Sprite("src/assets/img/continue/continue.png");
		wantToContinue.x = WindowConstants.WIDTH/2 - wantToContinue.width/2;
		wantToContinue.y = WindowConstants.HEIGHT/2 - wantToContinue.height;
		
		//Game over sprite center position
		gameOver = new Sprite("src/assets/img/continue/game_over.png");
		gameOver.x = WindowConstants.WIDTH/2 - gameOver.width/2;
		gameOver.y = WindowConstants.HEIGHT/2 - gameOver.height/2;
		
		//Number sprites positions
		numberPositions();
		
	}
	
	private void countDown() {
		
		
	}
	
	public void update() {
		
		//background.draw();
		//gameOver.draw();
		wantToContinue.draw();
		
		//for (Sprite spr: this.countDown) {
		//	spr.draw();
		//}
		
		countDown.get(8).draw();
		
		countDown();
		
		
	}
	
	private void numberPositions() {
		
		//Creating number sprites
		Sprite number0 = new Sprite("src/assets/img/continue/number_0.png");
		Sprite number1 = new Sprite("src/assets/img/continue/number_1.png");
		Sprite number2 = new Sprite("src/assets/img/continue/number_2.png");
		Sprite number3 = new Sprite("src/assets/img/continue/number_3.png");
		Sprite number4 = new Sprite("src/assets/img/continue/number_4.png");
		Sprite number5 = new Sprite("src/assets/img/continue/number_5.png");
		Sprite number6 = new Sprite("src/assets/img/continue/number_6.png");
		Sprite number7 = new Sprite("src/assets/img/continue/number_7.png");
		Sprite number8 = new Sprite("src/assets/img/continue/number_8.png");
		Sprite number9 = new Sprite("src/assets/img/continue/number_9.png");
		
		//Adding sprites elements to array
		countDown.add(number0);
		countDown.add(number1);
		countDown.add(number2);
		countDown.add(number3);
		countDown.add(number4);
		countDown.add(number5);
		countDown.add(number6);
		countDown.add(number7);
		countDown.add(number8);
		countDown.add(number9);
		
		//Setting center position for each element
		for (Sprite numbers: countDown) {
			
			numbers.x = WindowConstants.WIDTH/2 - numbers.width/2;
			numbers.y = WindowConstants.HEIGHT/2 - numbers.height/2;
			
		}
		
	}

}
