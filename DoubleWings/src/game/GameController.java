package game;

import scenes.GameScene;
import scenes.stages.stage1.StageTest;
import entity.Player;
import jplay.Keyboard;

public class GameController {
	
	public GameScene currentScene = null;
	public Keyboard keyboard = null;
	private boolean isRunning = true; 
	private Player player;
	
	public GameController() {
		super();
		this.player = new Player(this);
	}
	
	// Transit to another scene
	public void transitTo(GameScene scene) {	
		
		//leave transition if scene is null
		if(scene == null || keyboard == null) 
		  return;
		
		else if(currentScene != null){
			currentScene.destroy();
		}

		//run initial setup
		scene.configure(this);
		
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
	
	public Player getPlayer() {
		return player;
	}
	
	public void resetPlayer() {
		this.player = new Player(this);
	}
	
	public void revivePlayerSpaceship() {
		if (currentScene.getClass() == StageTest.class) {
			StageTest stage = (StageTest) currentScene;
			stage.createSpaceShip();
		} else {
			System.out.println("wtffffff");
		}
	}
	
}
