package game;
import scenes.GameScene;
import jplay.Keyboard;


public class GameController {
	
	public GameScene currentScene = null;
	public Keyboard keyboard = null;
	
	// Transit to another scene
	public void transitTo(GameScene scene){
		
		//leave transition if scene is null
		if(scene == null) return;
		
		//Update current scene variable
		currentScene = scene;
		
		//run initial setup
		scene.initialSetup(this);
	}
	
	public void update(){
		
		//Updates current scene
		if (currentScene != null){
			currentScene.update();
		}
		
	}

}
