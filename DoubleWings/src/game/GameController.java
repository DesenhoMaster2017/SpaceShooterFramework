package game;

import scenes.GameScene;
import jplay.Keyboard;

public class GameController {
	
	public GameScene currentScene = null;
	public Keyboard keyboard = null;
	private boolean isRunning = true; 
	
	// Transit to another scene
	public void transitTo(GameScene scene) {
		
		//leave transition if scene is null
		if(scene == null) 
		  return;

		//run initial setup
		scene.initialSetup(this);
		
		//Update current scene variable
		currentScene = scene;

	}
	
	// Updates current scene and control running status
	public boolean update(){
		
		//Updates current scene
		if (currentScene != null){
			currentScene.update();
		}
		
		return isRunning; 
	}
	
	// Quit game Ending process
	public void quit(){
		isRunning = false;
	}
	
}
