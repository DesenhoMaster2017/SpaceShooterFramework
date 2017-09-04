import jplay.Window; 

import java.awt.Color;

import constants.WindowConstants;

import scenes.stages.stage1.*;


public class Main {

	public static void main(String[] args) {
		System.out.println("It's running!");

		//It creates an windows with 800 pixels of width and 600 pixels of height   
		Window window = new Window(WindowConstants.WIDTH,WindowConstants.HEIGHT);
		
		//Game controller handles game states, screen changes, stages...
		GameController game = new GameController();
		
		//Should transit first to menu... but for development purposes...
		StageTest stage = new StageTest();
		game.transitTo(stage);

		//Game main loop
		while(true) {
			
			//Delay to keep 60 FPS
			window.delay(16);
			
			//Clear screen
			window.clear(Color.black);
			
			// update game
			game.update();
			
			//Refresh the screen
			window.update();
		}
	}

}
