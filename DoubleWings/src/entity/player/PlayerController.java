package entity.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import Score.Score;
import Score.ScoreType;
import entity.GameEntity;
import input.Input;
import observer.GameEntityObserver;
import util.InputKey;
import util.RunEvent;



public class PlayerController implements KeyListener{

	private static final int initialChances = 3; // Initially the player will have three lifes

	private boolean canContinue = true;
	private int chances = initialChances;
	public GameEntity entity;
	public PlayerSceneDelegate delegate = null;

	private Score score;
	private GameEntityObserver observer = null; //Temp solution to the observer
	
	
	//Respawn
	public double initialPositionX = 0;
	public double initialPositionY = 0;
	
	public PlayerController() {
		super();
		this.score = new Score();
		
		this.setupInputListen();
		this.setupDefaultAction();
	}

	
	// entity dying control
	private boolean didDie = false;
	
	public void update(){
		

		if (entity.getLife() <= 0) {
			// security check to avoid double dying bug
			if (didDie == false) {
				didDie = true;
				
				// Enter here if the spaceship is destroyed
				this.loseLife();
			} else { /*do nothing*/ }
		}
		
		this.checkInput();
	}
	

	// HUD observer getter and setter 
	public GameEntityObserver getObserver() {
		return this.observer;
	}

	public void setObserver(GameEntityObserver observer) {
  		//Adding HUD observer to the shield
		if (entity instanceof Player){
			Player pl = (Player) entity;
			pl.getShield().setObserver(observer);
		}
		
		this.observer = observer;
	}

	//Chances setters and getters
	public void setChances(int chances){
		this.chances = chances;
		//Notifying HUD to update chances shower
		if (observer != null) {
			observer.notifyObserver(this);	
		} else {
			System.out.println("Player log: HUD is null");
		}
	}

	public int getChances() {
		return this.chances;
	}

	//Score setters and getters
	public Score getScore() {
		return score;
	}

	public void increaseScore(ScoreType score) {
		this.score.increaseScore(score);
		//Notifying HUD to update score shower
		if (observer != null) {
			observer.notifyObserver(this);	
		} else {
			System.out.println("Player log: HUD is null");
		}
	}

	private void resetentity() {
		this.entity.reborn();
		this.didDie = false;
		this.backToInitialPosition();
	}
	
	public void backToInitialPosition(){
		this.entity.x = initialPositionX;
		this.entity.y = initialPositionY;
	}

	/**
	 * Lose one life. Handle losing life and game over scenarios. 
	 * 
	 * */
	public void loseLife() {
		setChances(this.chances - 1);
		System.out.println("lifes on player: " + this.chances);
		
		if (this.chances < 0) {
			loseGame();
		} else {
			resetentity();
		}
	}

	public void resetLife() {
		setChances(initialChances);
		resetentity();
		System.out.println("Player log: life reset to: " + this.chances);
	}

	public void loseGame() {
		if (this.canContinue) {
			useContinue();
		} else {
			this.delegate.transitToGameOver();
		}
	}

	public void useContinue() {
		this.canContinue = false;
		resetLife();
		this.delegate.transitToContinue();
	}

	public GameEntity getEntity() {
		return entity;
	}
	
//MARK: Input Setup
	public void setupInputListen(){
		Input.addListener(this);
	}

	public void setupDefaultAction(){

		//shootKey
		this.addActionToKey(KeyEvent.VK_SPACE, 0, new RunEvent(){
			@Override
			public void run(Object source) {
				if (source instanceof Player){
					Player ship = (Player) source;
					ship.fireBullet();
				}
			}
		});

		//move left
		this.addActionToKey(KeyEvent.VK_LEFT, 1, new RunEvent(){
			@Override
			public void run(Object source) {
				if (source instanceof Player){
					Player ship = (Player) source;
					ship.x -= ship.movimentVel;
				}
			}
		});

		//move right
		this.addActionToKey(KeyEvent.VK_RIGHT, 1, new RunEvent(){
			@Override
			public void run(Object source) {
				if (source instanceof Player){
					Player ship = (Player) source;
					ship.x += ship.movimentVel;
				}
			}
		});
		
		//move right
		this.addActionToKey(KeyEvent.VK_UP, 1, new RunEvent(){
			@Override
			public void run(Object source) {
				if (source instanceof Player){
					Player ship = (Player) source;
					ship.y -= ship.movimentVel;
				}
			}
		});

		//move right
		this.addActionToKey(KeyEvent.VK_DOWN, 1, new RunEvent(){
			@Override
			public void run(Object source) {
				if (source instanceof Player){
					Player ship = (Player) source;
					ship.y += ship.movimentVel;
				}
			}
		});

	}
	
	
//MARK:  Input Handle	
	private void checkInput(){

		// Handle press manually
		for(int key : this.inputStates.keySet()){
			int state = this.inputStates.get(key);
			
			if (state == 0){
				inputStates.put(key, 1); // Change to holding
				InputKey input = new InputKey(key, 0); // Creation can over heat memory
				RunEvent l = this.listeners.get(input.hashCode());
				if(l != null){
					l.run(this.entity);
				}
			}
			
			if (state == 1){
				InputKey input = new InputKey(key, 1); // Creation can over heat memory
				RunEvent l = this.listeners.get(input.hashCode());
				if(l != null){
					l.run(this.entity);
				}
			}
			
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Just for keyboard typing...
		// no usefull by now
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		Integer st = this.inputStates.get(e.getKeyCode() );
		if(st == null){
			inputStates.put(e.getKeyCode(), 0);
		}
		
		
		//this.handleInputEvent(new InputKey(e.getKeyCode(), 1));

		// pressed State is handle manually
		// keyPressed only, produces too much delay
	}

	@Override
	public void keyReleased(KeyEvent e) {
		inputStates.remove(e.getKeyCode());
		this.handleInputEvent(new InputKey(e.getKeyCode(), 2));
	}


	private HashMap<Integer, Integer> inputStates = new HashMap<Integer, Integer>();
	private HashMap<Integer, RunEvent> listeners = new HashMap<Integer, RunEvent>();

	//Type:   0 = type,  1 = press, 2 = release
	//Key: equals KeyEvent keyCode
	public void addActionToKey(int key, int type, RunEvent e){

		InputKey input = new InputKey(key, type);
		listeners.put(input.hashCode(), e);
		//System.out.println("new " + input.hashCode());
	}

	private void handleInputEvent(InputKey e){

		//System.out.println("handle " + e.hashCode());

		RunEvent event = listeners.get(e.hashCode());
		if(event != null){
			event.run(this.entity);
		}else{
			//System.out.println("Event action not found");
		}
	}
	

}