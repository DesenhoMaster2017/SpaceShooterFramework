package scenes.stages;

import java.util.ArrayList;
import jplay.Parallax;
import game.World;
import hud.HUD;
import entity.Enemy;
import entity.player.*;
import entity.pool.*;
import commands.*;
import constants.WindowConstants;
import scenes.*;
import game.evolver.*;



public class StageTest extends GameScene implements GameEventCallback, PlayerSceneDelegate {

	private World gameWorld;
	private HUD hud;
	private PlayerController playerControl;
	private ArrayList<Command> commands;
	private Command currentCommand = null;
	private int commandCount = 0;
	private Enemy asteroid1;
	private Parallax parallax;
  	

  	@Override
  	public void initialSetup(){

  		gameWorld = new World();
  		
  		gameWorld.addPool(new EnemyPool());
  		gameWorld.addPool(new BulletPool());
  		
  		gameWorld.add(testEnemy());

  		playerControl = new PlayerController();
  		
  		configureEntities();

  		//Development purposes
  		creatingCommands();
  		this.configureEvents();
  	}

  	protected void viewSetup(){
  		
  		//Creation a object to class Parallax
  		parallax = new Parallax();

  		//The first one added will be the last one to be painted.
  		parallax.add("src/assets/img/background_layer_0.png");
  		parallax.add("src/assets/img/background_layer_1.png");
  		parallax.add("src/assets/img/background_layer_2.png");

		parallax.getLayer(0).setVelY(0.5);
  		parallax.getLayer(1).setVelY(4.5);
  		parallax.getLayer(2).setVelY(5);
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
  		//Create the HUD and adding it as player's observer
  		hud = new HUD();
  		
  		//Creating player sprite on the center-bottom of the screen
  		playerControl.initialPositionX = WindowConstants.WIDTH/2;
  		playerControl.initialPositionY = WindowConstants.HEIGHT/2;
  		
  		createSpaceShip();
  		
  		playerControl.setObserver(hud);
  		playerControl.delegate = this;

  		createAsteroid(2.0);
  		createAsteroid(4.0);

  		createTestAsteroid();
  	}

  	public void createSpaceShip() {
  		
  		//Creating player Entity
  		Player spaceship = new Player(this.playerControl, 
  				playerControl.initialPositionX, 
  				playerControl.initialPositionY, true);

  		gameWorld.add(spaceship);
  		gameWorld.add(spaceship.getShield());
  	}

  	public void createAsteroid(double velY) {
  		Enemy asteroid = (Enemy) this.gameWorld.createEntity(Enemy.class);
  		asteroid.loadImage("src/assets/img/asteroid.png");
  		asteroid.setLife(50);
  		asteroid.x = Math.random() * (WindowConstants.WIDTH - asteroid.width*2) + asteroid.width;
  		asteroid.y = -200;
  		asteroid.vely = velY;

  		gameWorld.add(asteroid);
  	}

  	public void createTestAsteroid() {
  		asteroid1 = new Enemy("src/assets/img/asteroid.png");
  		asteroid1.setLife(10);
  		asteroid1.x = WindowConstants.WIDTH/2 - asteroid1.width/2;
  		asteroid1.y = 0;
  		asteroid1.vely = 2.0;

  		gameWorld.add(asteroid1);
  	}

  	@Override
  	public void update(){
  		updateParalax();

  		gameWorld.update(); // Updates and draw all entities added in game world
  		hud.draw(); // Draw all HUD elements
  		playerControl.update(); // Update input checking
  		
  		executeAsteroidCommand();
  	}

  	
  	public void updateParalax(){
  		//Print all layers that have been added
  			parallax.drawLayers();
  			
  			//The method below is responsible for maintaining infinite repetition of the layers.
  			parallax.repeatLayers(800, 600, false);
  			
  			//Move the parallax orientation vertically
  			parallax.moveLayersStandardY(false);
  	}

  	public void executeAsteroidCommand(){

  		//Asteroid command execute
  		commandCount += 1;

  		if (commandCount >= 50 && commands.size() > 0){

  			//			System.out.println("Commands: " + commands.size());
  			currentCommand = commands.remove(commands.size() - 1); // return removed object
  			//			System.out.println("current: "+ String.valueOf(currentCommand));			

  			commandCount = 0;
  		}

  		if (currentCommand != null){
  			currentCommand.execute(asteroid1);
  		}
  	}

  	// Callback event handler
  	@Override
  	public void eventCallback(GameEvent event) {

  		//		System.out.println("Event callback received with type: " + event.type);

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
  		//		System.out.println("Launch Enemy Down! \\o/");
  		this.createAsteroid(2.0);
  	}

  	public void launchEnemyCrazy(){
  		//		System.out.println("Crazy enemy in sight! ò.Ó ");
  		this.createAsteroid(4.0);
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

  		this.gameWorld.addEventAfterCurrentTime(this, 400, 2, "Enemy2");

  		this.gameWorld.addEventAfterCurrentTime(this, 250, 1, "Enemy down");
  		this.gameWorld.addEventAfterCurrentTime(this, 300, 1, "Enemy down");
  		this.gameWorld.addEventAfterCurrentTime(this, 400, 1, "Enemy down");

  		this.gameWorld.addEventAfterCurrentTime(this, 50, 2, "Enemy2");

  		this.gameWorld.addEventAfterCurrentTime(this, 620, 2, "Enemy2");
  		this.gameWorld.addEventAfterCurrentTime(this, 660, 2, "Enemy2");
  		this.gameWorld.addEventAfterCurrentTime(this, 690, 2, "Enemy2");

  	}

  	// Player Scene Delegate
  	@Override
  	public void transitToGameOver() {
  		GameOver gameOver = new GameOver();
  		this.game.transitTo(gameOver);
  	}
  	
  	@Override
  	public void transitToContinue() {
  		GameScene scene = new ClassicContinue();
  		this.game.transitTo(scene);
  	}
  	
  	private Enemy testEnemy() {
		ArrayList<Command> behavior = new ArrayList<Command>();
		behavior.add(new MoveCommand(CommandType.DOWN));
		behavior.add(new MoveCommand(CommandType.DOWN));
		behavior.add(new MoveCommand(CommandType.LEFT));
		behavior.add(new MoveCommand(CommandType.LEFT));
		
		Enemy enemy = new Enemy("src/assets/img/asteroid.png");
		enemy.x = Math.random() * (WindowConstants.WIDTH - enemy.width*2) + enemy.width;
  		enemy.y = 0;
  		enemy.addBehavior(behavior);
  		enemy.startBehaving();
  		
  		return enemy;
	}
}

