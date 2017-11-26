package game;

import scenes.GameScene;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import jplay.Keyboard;

public class GameController {
	
	public static GameController singleton = new GameController();
	public GameScene currentScene = null;
	public Keyboard keyboard = null;
	private boolean isRunning = true; 
	
	
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
	
	
	//Window to hear keyboard input
	private JFrame window;
	
	public void addWindow(JFrame w){
		this.window = w;
	}
	
	public void addInputListener(KeyListener l){
		this.window.addKeyListener(l);
	}
	
	public void removeInputListener(KeyListener l){
		this.window.removeKeyListener(l);
	}
	
}
