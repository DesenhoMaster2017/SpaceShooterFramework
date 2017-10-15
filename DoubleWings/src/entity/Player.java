package entity;

import constants.WindowConstants;
import game.GameController;
import scenes.ClassicContinue;
import scenes.GameOver;
import observer.GameEntityObserver;

public class Player {

	private static final int initialChances = 3; // Initially the player will have three lifes

	private boolean canContinue = true;
	private int chances = initialChances;
	private PlayerSpaceship spaceship;
	private GameController game;
	private int score = 0; // Score default value
	private GameEntityObserver observer = null; //Temp solution to the observer
  
  
	public Player(GameController game) {
		this.game = game;
		this.spaceship = new PlayerSpaceship(this, WindowConstants.WIDTH/2, WindowConstants.HEIGHT/2, true);
	}
	
	// HUD observer getter and setter 
	public GameEntityObserver getObserver() {
		return this.observer;
	}
	
	public void setObserver(GameEntityObserver observer) {
		this.observer = observer;
	}
	
	//Chances setters and getters
	public void setChances(int chances){
		this.chances = chances;
		//Notifying HUD to update chances shower
		if (observer != null) {
			observer.notifyObserver(this);	
		} else {
			System.out.println("Player log: HUD is null :(");
		}
	}
	
	public int getChances() {
		return this.chances;
	}

	//Score setters and getters
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		//Notifying HUD to update score shower
		if (observer != null) {
			observer.notifyObserver(this);	
		} else {
			System.out.println("Player log: HUD is null :(");
		}
	}
  
	private void resetSpaceship() {
		this.spaceship = new PlayerSpaceship(this, this.spaceship.x, this.spaceship.y, false);
		game.revivePlayerSpaceship();
	}
  
  /**
	 * Lose one life. Handle losing life and game over scenarios. 
	 * 
	 * */
	public void loseLife() {
		System.out.println("entered here in loseLife ");
//		if (!isLosingLife) {
//			isLosingLife = true;
			setChances(this.chances - 1);
			System.out.println("LOST A LIIIIIIIIIFE");
			System.out.println("lifes on player: " + this.chances);
			if (this.chances < 0) {
				System.out.println("LOSE GAAAAAAAAAAAAME");
				loseGame();
			} else {
				System.out.println("RESEEET");
				resetSpaceship();
			}
	}
  
  public void resetLife() {
		setChances(initialChances);
		resetSpaceship();
		System.out.println("Player log: life reset to: " + this.chances);
	}
	
	public void loseGame() {
		if (this.canContinue) {
			useContinue();
		} else {
			this.game.transitTo(new GameOver());
		}
	}
	
	public void useContinue() {
		this.canContinue = false;
		resetLife();
		this.game.transitTo(new ClassicContinue());
	}
	
	public PlayerSpaceship getSpaceship() {
		return spaceship;
	}
}