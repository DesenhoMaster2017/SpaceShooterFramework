package scenes;
import java.util.Timer;

import constants.WindowConstants;
import game.GameController;
import jplay.GameImage;
import jplay.InputBase;
import jplay.Keyboard;
import jplay.Sprite;
import scenes.menu.MenuScene;
import scenes.stages.stage1.StageTest;
import util.CountDownTimer;
import util.CountDownTimerEnds;

public class Lose extends GameScene implements CountDownTimerEnds {
		
	// Sprites on scene
	private GameImage background;
	private Sprite lose;
	private Sprite lifeRemaining;
	private int lifePlayer;
	
	//Thread counter
	//static Thread thread = new Thread(); 
	
	
	public void initialSetup() {
		
		background = new GameImage("src/assets/img/temp_background.png");
		
		//Define scenes elements position
		//Continue sprite upper-center position
		
		lose = new Sprite("src/assets/img/continue/YOU-LOSE.png");
		lose.x = WindowConstants.WIDTH/2 - lose.width/2;
		lose.y = WindowConstants.HEIGHT/500 - lose.height/20;
		
		//Number sprite positions
		
		if(getLifePlayer() == 1){
			lifeRemaining = new Sprite("src/assets/img/continue/1life.png");	
		}
		
		else if(getLifePlayer() == 2){
			lifeRemaining = new Sprite("src/assets/img/continue/2life.png");
		}
		
		else if(getLifePlayer() == 3){
			lifeRemaining = new Sprite("src/assets/img/continue/3life.png");
		}
		
		lifeRemaining.x = WindowConstants.WIDTH/2 - lifeRemaining.width/2;
		lifeRemaining.y = WindowConstants.HEIGHT/2 - lifeRemaining.height/2;
		
		timeWait();
	}
	
	@Override
	protected void viewSetup() {
		// TODO Auto-generated method stub
		
	}
	
	public void setLifePlayer(int lifePlayer){
		 this.lifePlayer = lifePlayer;
	}
	
	public int getLifePlayer(){
		 return lifePlayer;
	}
	
	public void timeWait(){
		
		Timer timer = new Timer();
		CountDownTimer countDownn = new CountDownTimer();
		countDownn.delegate = this;
		long delay = 1000;
		timer.scheduleAtFixedRate(countDownn, delay, delay);
	
	}
	
	public void update() {
		
		background.draw();
		lose.draw();
		lifeRemaining.draw();
		
		//checkButtonSelection();
		
	}
	
	@Override
	public void terminate() {
		if (game != null){
			System.out.println("Timer Ended");
			ClassicContinue classicContinue = new ClassicContinue();
			game.transitTo(classicContinue);
		}
	}
	
	@Override
	public void updateImageForIndex(int index) {
		//counter.loadImage("src/assets/img/continue/number_" + String.valueOf(index) + ".png");
	}
	
	/*private void checkButtonSelection () {
		
		if(game != null && keyboard != null){
			if (keyboard.keyDown(Keyboard.ENTER_KEY)) {
				//Transit to a continue state of the game
				MenuScene transitScene = new MenuScene();
				game.transitTo(transitScene.firstStage());
				
			} else if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
				GameScene menu = new MenuScene();
				game.transitTo(menu);
			}
		}
	}*/

}
