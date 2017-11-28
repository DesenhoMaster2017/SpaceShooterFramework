
import game.Game;
import scenes.menu.MenuScene;
import scenes.stages.StageTest;

public class Main {

	public static void main(String[] args) {
		
		Game g = new Game();
		
		//Creating first scene as menu
		MenuScene menu = new MenuScene();
		menu.firstLevel = new StageTest();
		
		//First stage at game run
		g.setFirstScene(menu);
		
		//Start game loop
		g.start();
	}
}
