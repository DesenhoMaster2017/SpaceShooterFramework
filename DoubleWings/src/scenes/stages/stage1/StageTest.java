package scenes.stages.stage1;

import game.GameController;
import game.World;
import entity.*;

import java.util.ArrayList;

import commands.*;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.Sprite;
import entity.GameEntity;

import java.util.Timer;

import constants.WindowConstants;
import scenes.ClassicContinue;
import scenes.GameOver;
import scenes.GameScene;
import util.CountDownTimer;

public class StageTest extends GameScene {

	private GameEntity player;
	private GameImage background;
	private World gameWorld;
	private ArrayList<Command> commands;
	private Command currentCommand = null;
	private int commandCount = 0;
	private Enemy asteroid1;
	private int lifePlayer = 3;
    
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
		//Creating player sprite
		player = new Player("src/assets/img/temp_player.png");
		player.setLife(3);
				
		//Putting player on the center-bottom of the screen
		player.x = WindowConstants.WIDTH/2 - player.width/2;
		player.y = WindowConstants.HEIGHT - player.height;

		Shield shield = new Shield(player);
		shield.setLife(10);
				
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
		
		gameWorld.add(shield);
		gameWorld.add(player);
	}
	
	
	@Override
	public void update(){
		
		background.draw();
		gameWorld.update(); // Updates and draw all entities added in game world

		//Player movement
		player.moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, 4);//velocity = 1
		player.moveY(Keyboard.UP_KEY, Keyboard.DOWN_KEY, 4);//velocity = 1
		
		executeAsteroidCommand();

		if (player.isDead()){
			
			if(lifePlayer > 0){
				lifePlayer = lifePlayer - 1;
				launchGameContinue();
			}
					
			else if(lifePlayer == 0){
				launchGameOver();
			}		
				
		}
				
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
	
	public void launchGameContinue(){
		GameScene countdown = new ClassicContinue();
		game.transitTo(countdown);
	}
	
	public void launchGameOver(){
		GameScene gameOver = new GameOver();
		game.transitTo(gameOver);
	}
	
}
