package scenes;
import java.util.ArrayList;
import java.util.Timer;

import constants.WindowConstants;
import game.GameController;
import jplay.GameImage;
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
	private ArrayList<Sprite> countDown = new ArrayList<Sprite>();
	private Sprite cd;
	
	//Thread counter
	//static Thread thread = new Thread(); 
	
	
	public void initialSetup(GameController game) {
		
		//Set game controller elements
		this.game = game;
		keyboard = game.keyboard;
		
		//Configure enter key and escape
		keyboard.setBehavior(keyboard.ENTER_KEY, keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(keyboard.ESCAPE_KEY, keyboard.DETECT_INITIAL_PRESS_ONLY);
		
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
		cd = new Sprite("src/assets/img/continue/number_9.png");
		cd.x = WindowConstants.WIDTH/2 - cd.width/2;
		cd.y = WindowConstants.HEIGHT/2 - cd.height/2;
		
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
		cd.draw();
	}
	



	@Override
	public void terminate() {
		System.out.println("Timer Ended");
		GameScene menu = new MenuScene();
		game.transitTo(menu);
	}
	



	@Override
	public void updateImageForIndex(int index) {
		cd.loadImage("src/assets/img/continue/number_" + String.valueOf(index) + ".png");
	}

}
