package scenes.stages.stage1;

import game.World;
import entity.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import commands.*;

import jplay.GameImage;
import jplay.Keyboard;
import entity.GameEntity;
import constants.WindowConstants;
import scenes.GameScene;
import game.evolver.GameEvent;
import game.evolver.GameEventCallback;

public class StageTest extends GameScene implements GameEventCallback{

	private GameImage background;
	private World gameWorld;
	private ArrayList<Command> commands;
	private Command currentCommand = null;
	private int commandCount = 0;
	private Enemy asteroid1;
	
    
	@Override
	protected void initialSetup(){
		
		gameWorld = new World();
		
		//Configure up and down keys
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_EVERY_PRESS);
		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_EVERY_PRESS);
		
		//Second Player configuration
		keyboard.addKey(KeyEvent.VK_A, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_S, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_D, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_W, Keyboard.DETECT_EVERY_PRESS);

		configureEntities();
		
		//Development purposes
		creatingCommands();
		
		this.configureEvents();
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
	
	public Player createPlayer(){
		//Creating player sprite
		Player player = new Player("src/assets/img/temp_player.png");
		player.setLife(5);
				
		//Putting player on the center-bottom of the screen
		player.x = WindowConstants.WIDTH/2 - player.width/2;
		player.y = WindowConstants.HEIGHT - player.height;
		
		//Default velocity
		player.movimentVel = 4;
		
		Shield shield = new Shield(player);
		shield.setLife(10);
		
		gameWorld.add(shield);
		gameWorld.add(player);
		
		return player;
	}

	
	private void configureEntities(){
		
		Player pl1 = createPlayer();
		pl1.downKey = Keyboard.DOWN_KEY;
		pl1.upKey = Keyboard.UP_KEY;
		pl1.leftKey = Keyboard.LEFT_KEY;
		pl1.rightKey = Keyboard.RIGHT_KEY;
		
		Player pl2 = createPlayer();
		pl2.downKey = KeyEvent.VK_S;
		pl2.upKey = KeyEvent.VK_W;
		pl2.leftKey = KeyEvent.VK_A;
		pl2.rightKey = KeyEvent.VK_D;
		
		
		//Testing commands
		asteroid1 = new Enemy("src/assets/img/asteroid.png");
		asteroid1.setLife(10);
		asteroid1.x = WindowConstants.WIDTH/2 - asteroid1.width/2;
		asteroid1.y = 0;
		asteroid1.vely = 2.0;
		
		gameWorld.add(asteroid1);
	}
	
	public void createFirstAsteroid(){
		Enemy first = this.gameWorld.createEnemy();
		first.loadImage("src/assets/img/asteroid.png");
		first.setLife(10);
		first.x = Math.random() * (WindowConstants.WIDTH - first.width*2) + first.width;
		first.y = -200;
		first.vely = 2.0;
		
		gameWorld.add(first);
	}
	
	public void createSecondAsteroid(){
		
		Enemy second = this.gameWorld.createEnemy();
		second.loadImage("src/assets/img/asteroid.png");
		second.setLife(10);
		second.x = Math.random() * (WindowConstants.WIDTH - second.width*2) + second.width;
		second.y = -200;
		second.vely = 4.0;
		
		gameWorld.add(second);
		
	}
	
	
	@Override
	public void update(){
		
		for (int i = 0; i < 100; i++){
			if(this.keyboard.keyDown(i)){
				System.out.println("key down: " + i);
			}
			
		}
		
		background.draw();
		gameWorld.update(); // Updates and draw all entities added in game world

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

	
	// Callback event handler
	@Override
	public void eventCallback(GameEvent event) {
		
		System.out.println("Event callback received with type: " + event.type);
		
		switch (event.type){
		case 1:
			launchEnemyDown();
			break;
		case 2:
			launchEnemyCrazy();
			break;
		default:
			this.script1();
			break;
		}
	}
	
	public void launchEnemyDown(){
		System.out.println("Launch Enemy Down! \\o/");
		this.createFirstAsteroid();
	}
	
	public void launchEnemyCrazy(){
		System.out.println("Crazy enemy in sight! ò.Ó ");
		this.createSecondAsteroid();
	}
	
	public void configureEvents(){
		this.script1();
	}
	
	public void script1(){
		
		// 200 = default
		// 1 = first enemy
		// 2 = second type enemy
		this.gameWorld.addEventAfterCurrentTime(this, 700, 200, "Recursive script");
		
		this.gameWorld.addEventAfterCurrentTime(this, 200, 1, "Enemy down");
		
		this.gameWorld.addEventAfterCurrentTime(this, 400, 2, "Enemy Crazy bastard");
		
		this.gameWorld.addEventAfterCurrentTime(this, 250, 1, "Enemy down");
		this.gameWorld.addEventAfterCurrentTime(this, 300, 1, "Enemy down");
		this.gameWorld.addEventAfterCurrentTime(this, 400, 1, "Enemy down");
		
		this.gameWorld.addEventAfterCurrentTime(this, 50, 2, "Enemy Crazy bastard");
		
		this.gameWorld.addEventAfterCurrentTime(this, 620, 2, "Enemy Crazy bastard");
		this.gameWorld.addEventAfterCurrentTime(this, 660, 2, "Enemy Crazy bastard");
		this.gameWorld.addEventAfterCurrentTime(this, 690, 2, "Enemy Crazy bastard");
	}
}