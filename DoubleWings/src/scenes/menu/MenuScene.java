package scenes.menu;
import scenes.GameScene;
import jplay.Keyboard;
import game.GameController;


enum OptionMenu {  
	Start_Game(1), Ranking(2), Settings(3), Quit(4);
	
	private final int valor;
	
	OptionMenu(int option){
		valor = option;
	}
	
	OptionMenu next(){
		
		int option = valor;
		if (valor < 4){
			option += 1;
		}
		
		return OptionMenu(option);
	}

	OptionMenu back(){
		
		int option = valor;
		if (valor > 1){
			option -= 1;
		}
		
		return OptionMenu(option);
	}

	private OptionMenu OptionMenu(int option) {

		switch(option){

		case 1:
			return OptionMenu.Start_Game;
			
		case 2:
			return OptionMenu.Ranking;
			
		case 3:
			return OptionMenu.Settings;
			
		case 4:
			return OptionMenu.Quit;
		}

		return OptionMenu.Start_Game;
	}
	
}


// MenuScene 
public class MenuScene extends GameScene {

	private OptionMenu selectedMenuOption = OptionMenu.Start_Game;
	private Keyboard keyboard = null; 
	
	
	public void initialSetup(GameController game){
		
		//Reset option menu
		selectedMenuOption = OptionMenu.Start_Game;
		
		//save keyboard
		keyboard = game.keyboard;
		
		//Configure up and down keys
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
	}
	
	public void update(){
		
		// Controls menu option selection
		
		// Down selection
		if (keyboard.keyDown(Keyboard.DOWN_KEY)){
			System.out.println("down");
			selectedMenuOption.next();
		}
		
		// Up selection		
		if (keyboard.keyDown(Keyboard.UP_KEY)){
			System.out.println("up");
			selectedMenuOption.back();
		}
		
	}
	
}
