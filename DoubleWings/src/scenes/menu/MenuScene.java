package scenes.menu;
import scenes.GameScene;
import jplay.Keyboard;
import game.GameController;
import jplay.Sprite;

enum OptionMenu {  
	Start_Game(1), Ranking(2), Settings(3), Quit(4);
	
	private final int value; //Current option on menu
	
	OptionMenu(int option){
		value = option;
	}
	
	OptionMenu next(){
		
		int option = value;
		if (value < 4){
			option += 1;
		}
		
		return OptionMenu(option);
	}

	OptionMenu back(){
		
		int option = value;
		if (value > 1){
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
	private Keyboard keyboard = null; //Attribute to save keyboard
	
	//Sprites on scene
	private Sprite title;
	private Sprite startButton;
	private Sprite rankingButton;
	private Sprite settingsButton;
	private Sprite arrow;
	
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
			selectedMenuOption = selectedMenuOption.next();
			System.out.println(selectedMenuOption);
		}
		
		// Up selection		
		if (keyboard.keyDown(Keyboard.UP_KEY)){
			System.out.println("up");
			selectedMenuOption = selectedMenuOption.back();
			System.out.println(selectedMenuOption);
		}
		
		
		
	}
	
}
