package entity;

import constants.WindowConstants;
import game.GameController;
import scenes.ClassicContinue;
import scenes.GameOver;

public class Player {
	
	private static final int initialLife = 3;
	
	private boolean canContinue = true;
	private int lifes = initialLife;
	private PlayerSpaceship spaceship;
	private GameController game;
	
	public Player(GameController game) {
		this.game = game;
		this.spaceship = new PlayerSpaceship(this, WindowConstants.WIDTH/2, WindowConstants.HEIGHT/2);
	}
	
	private void resetSpaceship() {
		this.spaceship = new PlayerSpaceship(this, this.spaceship.x, this.spaceship.y);
		game.revivePlayer();
	}
	
	/**
	 * Lose one life. Handle losing life and game over scenarios. 
	 * 
	 * */
	public void loseLife() {
		this.lifes -= 1;
		System.out.println("lifes on player: " + lifes);
		if (this.lifes <= 0) {
			loseGame();
		} else {
			resetSpaceship();
		}
	}
	
	public void resetLife() {
		this.lifes = initialLife;
		resetSpaceship();
		System.out.println("life reset to: " + lifes);
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