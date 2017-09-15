package scenes;
import game.GameController;
import jplay.Keyboard;;

public abstract class GameScene {
	
	protected GameController game;
	protected Keyboard keyboard; 

	public void update(){};
		
	public void initialSetup(GameController game){};
	
}
