package scenes;
import java.util.Timer;

import constants.WindowConstants;
import game.GameController;
import jplay.GameImage;
import jplay.InputBase;
import jplay.Keyboard;
import jplay.Sprite;
import scenes.menu.MenuScene;
import util.CountDownTimer;
import util.CountDownTimerEnds;

public class ClassicContinue extends GameScene implements CountDownTimerEnds {
		
	// Sprites on scene
	private GameImage background;
	private Sprite gameOver;
	private Sprite wantToContinue;
	private Sprite counter;
	
	//Thread counter
	//static Thread thread = new Thread(); 
	
	
	public void initialSetup(GameController game) {
		
		//Set game controller elements
		this.game = game;
		keyboard = game.keyboard;
		
		//Configure enter key and escape
		keyboard.setBehavior(Keyboard.ENTER_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(Keyboard.ESCAPE_KEY, InputBase.DETECT_INITIAL_PRESS_ONLY);
		
		background = new GameImage("src/assets/img/temp_background.png");
		
		//Define scenes elements position
		//Continue sprite upper-center position
		wantToContinue = new Sprite("src/assets/img/continue/continue.png");
		wantToContinue.x = WindowConstants.WIDTH/2 - wantToContinue.width/2;
		wantToContinue.y = WindowConstants.HEIGHT/2.5 - wantToContinue.height;
		//Game over sprite center position
		gameOver = new Sprite("src/assets/img/continue/game_over.png");
		gameOver.x = WindowConstants.WIDTH/2 - gameOver.width/2;
		gameOver.y = WindowConstants.HEIGHT/2 - gameOver.height/2;
		
		//Number sprite positions
		counter = new Sprite("src/assets/img/continue/number_9.png");
		counter.x = WindowConstants.WIDTH/2 - counter.width/2;
		counter.y = WindowConstants.HEIGHT/2 - counter.height/2;
		
		Timer timer = new Timer();
		CountDownTimer countDownn = new CountDownTimer();
		countDownn.delegate = this;
		long delay = 1000;
		timer.scheduleAtFixedRate(countDownn, delay, delay);
	}
	
	public void update() {
		
		background.draw();
		//gameOver.draw();
		wantToContinue.draw();
		counter.draw();
		
		checkButtonSelection();
		
	}
	
	@Override
	public void terminate() {
		System.out.println("Timer Ended");
		GameScene menu = new MenuScene();
		game.transitTo(menu);
	}
	
	@Override
	public void updateImageForIndex(int index) {
		counter.loadImage("src/assets/img/continue/number_" + String.valueOf(index) + ".png");
	}
	
	private void checkButtonSelection ( ) {
		if (keyboard.keyDown(Keyboard.ENTER_KEY)) {
			//Transit to a continue state of the game
		} else if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
			GameScene menu = new MenuScene();
			game.transitTo(menu);
		}
	}

}
