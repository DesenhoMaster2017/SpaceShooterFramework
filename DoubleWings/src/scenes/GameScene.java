package scenes;
import game.GameController;
import jplay.Keyboard;;

public abstract class GameScene {
	
	protected GameController game;
	protected Keyboard keyboard; 

    public abstract void update();
		
	public abstract void initialSetup(GameController game);
	
}
