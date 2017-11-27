package game;

import java.awt.Color;

import constants.WindowConstants;
import jplay.GameImage;
import jplay.Window;
import scenes.GameScene;
import scenes.menu.MenuScene;


public class Game {

	private GameScene firstScene;
	
	public void setFirstScene(GameScene firstScene){
		this.firstScene = firstScene;
	}
	
	public void start(){
		
		//It creates an windows with 800 pixels of width and 600 pixels of height
		Window window = new Window(WindowConstants.WIDTH,WindowConstants.HEIGHT);
		
		//Game controller handles game states, screen changes, stages...
		GameController game = GameController.singleton;
		game.keyboard = window.getKeyboard();
		game.addWindow(window);
		
		//Should transit first to menu... but for development purposes...
		
		game.transitTo(firstScene);
		
		boolean isRunning = true;
		System.out.println("Game is Running!");
		
		//Game main loop
		while(isRunning) {

			//Delay to keep 60 FPS
			window.delay(16);

			//Clear screen
			window.clear(Color.black);

			// update game
			isRunning = game.update();

			//Refresh the screen
			window.update();
		}
		
		//Leaving the game
		window.exit();
		
	}
	
	
	
}
