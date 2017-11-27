
import game.Game;
import scenes.stages.stage1.StageTest;

public class Main {

	public static void main(String[] args) {
		
		Game g = new Game();
		
		//First stage at game run
		g.setFirstStage(new StageTest());
		
		//Menu background for customization
		g.setMenuBackground("src/assets/img/menu/background.png");
		
		//Start game loop
		g.start();
	}
}
