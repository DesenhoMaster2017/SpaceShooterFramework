
import scenes.GameScene;


public class GameController {
	
	public GameScene currentScene = null;
	
	// Transit to another scene
	public void transitTo(GameScene scene){
		
		//leave transition if scene is null
		if(scene == null) return;
		
		//Update current scene variable
		currentScene = scene;
		
		//run initial setup
		scene.initialSetup();
	}
	
	public void update(){
		
		//Updates current scene
		if (currentScene != null){
			currentScene.update();
		}
		
	}

}
