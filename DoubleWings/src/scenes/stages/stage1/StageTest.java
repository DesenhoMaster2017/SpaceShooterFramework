package scenes.stages.stage1;

import game.World;
import entity.*;
import java.util.ArrayList;
import commands.*;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Sprite;
import java.util.Timer;
import entity.GameEntity;
import constants.WindowConstants;
import scenes.ClassicContinue;
import scenes.GameOver;
import scenes.GameScene;
import scenes.Lose;

public class StageTest extends GameScene {
	private Player player;
	private GameImage background;
	private World gameWorld;
	private ArrayList<Command> commands;
	private Command currentCommand = null;
	private int commandCount = 0;
	private Enemy asteroid1;
	private int lifePlayer = 3;					// Variable used to count the number of lives of the player
	
	// Instantiating an object to do manipulation in the lose class	
	Lose lose = new Lose();
	
	@Override
	protected void initialSetup(){
		
		gameWorld = new World();
		
		//Configure up and down keys
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_EVERY_PRESS);
		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_EVERY_PRESS);

		configureEntities();
		
		//Development purposes
		creatingCommands();
			
	}
	
	protected void viewSetup(){
		// Loading background image
		background = new GameImage("src/assets/img/temp_background.png");
	}
	
	private void creatingCommands(){
		commands = new ArrayList<Command>();
		
		commands.add(CommandCreator.createCommand(CommandType.LEFT));
		commands.add(CommandCreator.createCommand(CommandType.DOWN));		
		commands.add(CommandCreator.createCommand(CommandType.RIGHT));
		commands.add(CommandCreator.createCommand(CommandType.RIGHT));
		commands.add(CommandCreator.createCommand(CommandType.RIGHT));
		
		currentCommand = commands.remove(commands.size() - 1); // return removed object
	}

	
	private void configureEntities(){
		//Creating player sprite on the center-bottom of the screen
		final PlayerSpaceship spaceShip = this.game.getPlayer().getSpaceship();
		asteroid1 = new Enemy("src/assets/img/asteroid.png");
		asteroid1.setLife(10);
		asteroid1.x = WindowConstants.WIDTH/2 - asteroid1.width/2;
		asteroid1.y = 0;
		asteroid1.vely = 2.0;
				
		Enemy asteroid2 = new Enemy("src/assets/img/asteroid.png");
		asteroid2.setLife(10);
		asteroid2.x = WindowConstants.WIDTH/2 - asteroid2.width/2;
		asteroid2.y = -200;
		asteroid2.vely = 2.0;

		gameWorld.add(asteroid1);
		gameWorld.add(asteroid2);
		gameWorld.add(spaceShip);
		gameWorld.add(spaceShip.getShield());
	}
	
	@Override
	public void update(){
		
		background.draw();
		gameWorld.update(); // Updates and draw all entities added in game world

		//Player movement
		
		
		executeAsteroidCommand();
	}
	
	public void executeAsteroidCommand(){
		//Asteroid command execute
		commandCount += 1;

		if (commandCount >= 50 && commands.size() > 0){

			System.out.println("Commands: " + commands.size());
			currentCommand = commands.remove(commands.size() - 1); // return removed object
			System.out.println("current: "+ String.valueOf(currentCommand));			

			commandCount = 0;
		}

		if (currentCommand != null){
			currentCommand.execute(asteroid1);
		}
	}
	
	//Method to transition to the continue scene
	public void launchGameContinue(){	
		GameScene countdown = new ClassicContinue();
		game.transitTo(countdown);
	}
	
	//Method to transition to the Game Over scene
	public void launchGameOver(){
		GameScene gameOver = new GameOver();
		game.transitTo(gameOver);
	}
	
	//Method to transition to the lose scene
	public void launchScreenLose(){
		game.transitTo(lose);
	}
	
}
