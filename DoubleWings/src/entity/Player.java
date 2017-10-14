package entity;

import constants.WindowConstants;
import game.GameController;
import scenes.ClassicContinue;
import scenes.GameOver;

public class Player {
	// 
	private boolean canContinue = true;
	private int lifes = 3;
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
	
	public void loseLife() {
		this.lifes -= 1;
		System.out.println(lifes);
		if (this.lifes <= 0) {
			loseGame();
		} else {
			resetSpaceship();
		}
	}
	
	public void resetLife() {
		this.lifes = 3;
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
		this.game.transitTo(new ClassicContinue());
	}
	
	public PlayerSpaceship getSpaceship() {
		return spaceship;
	}
	
}