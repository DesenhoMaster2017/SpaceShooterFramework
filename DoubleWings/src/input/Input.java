package input;

import java.awt.event.KeyListener;
import game.GameController;

public class Input {
	
	static public void addListener(KeyListener l){
		GameController.singleton.addInputListener(l);
	}
	
	static public void removeListener(KeyListener l){
		GameController.singleton.removeInputListener(l);
	}

}
