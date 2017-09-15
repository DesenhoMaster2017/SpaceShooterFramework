package scenes.menu;
import scenes.GameScene;
import jplay.GameImage;
import jplay.Keyboard;
import constants.WindowConstants;
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
	//GameScene constants
	public static final int DISTANCE_TITLE_BUTTON = WindowConstants.HEIGHT/24;
	public static final int DISTANCE_BETWEEN_BUTTONS = WindowConstants.HEIGHT/48;
	
	private OptionMenu selectedMenuOption = OptionMenu.Start_Game;//Define initial menu option
	private Keyboard keyboard = null; //Attribute to save keyboard
	
	// Sprites on scene
	private GameImage background;
	private Sprite title;
	private Sprite startButton;
	private Sprite rankingButton;
	private Sprite settingsButton;
	private Sprite quitButton;
	private Sprite arrow;
	
	public void initialSetup(GameController game){
		
		//Reset option menu
		selectedMenuOption = OptionMenu.Start_Game;
		
		//save keyboard
		keyboard = game.keyboard;
		
		//Configure up and down keys
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		
		//Define Scene elements
		background = new GameImage("src/assets/img/menu/background.png");
		
		title = new Sprite("src/assets/img/menu/title.png");
		title.x = WindowConstants.WIDTH/2 - title.width/2;
		title.y = WindowConstants.HEIGHT/3 - title.height/2;
		
		startButton = new Sprite("src/assets/img/menu/start_button.png");
		startButton.x = WindowConstants.WIDTH/2 - startButton.width/2;
		startButton.y = title.y + title.height + DISTANCE_TITLE_BUTTON;
		
		rankingButton = new Sprite("src/assets/img/menu/ranking.png");
		rankingButton.x = startButton.x;
		rankingButton.y = startButton.y + startButton.height + DISTANCE_BETWEEN_BUTTONS;
		
		settingsButton = new Sprite("src/assets/img/menu/settings.png");
		settingsButton.x = startButton.x;
		settingsButton.y = rankingButton.y + rankingButton.height + DISTANCE_BETWEEN_BUTTONS;
		
		quitButton = new Sprite("src/assets/img/menu/quit.png"); 
		quitButton.x = startButton.x;
		quitButton.y = settingsButton.y + settingsButton.height + DISTANCE_BETWEEN_BUTTONS;
		
		arrow = new Sprite("src/assets/img/menu/arrow.png");
	}
	
	public void update(){
		
		// Control menu option selection
		
		// Down selection
		if (keyboard.keyDown(Keyboard.DOWN_KEY)){
			System.out.println("down");
			//Change current menu option
			selectedMenuOption = selectedMenuOption.next();
			System.out.println(selectedMenuOption);
		}
		
		// Up selection		
		if (keyboard.keyDown(Keyboard.UP_KEY)){
			System.out.println("up");
			//Change current menu option
			selectedMenuOption = selectedMenuOption.back();
			System.out.println(selectedMenuOption);
		}
		
		// Draw scene elements
		background.draw();
		title.draw();
		startButton.draw();
		rankingButton.draw();
		settingsButton.draw();
		quitButton.draw();
	}
}
