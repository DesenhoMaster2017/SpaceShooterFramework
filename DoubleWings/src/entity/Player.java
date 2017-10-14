package entity;

import constants.WindowConstants;

public class Player {
	// 
	private boolean canContinue = true;
	private int lifes = 3;
	private PlayerSpaceship spaceship;
	
	public Player() {
		createNewSpaceship();
	}
	
	private void createNewSpaceship() {
		this.spaceship = new PlayerSpaceship(WindowConstants.WIDTH/2, WindowConstants.HEIGHT/2);
	}
	
	public void loseLife() {
		this.lifes -= 1;
		if (this.lifes <= 0) {
			loseGame();
		} else { /*do nothing*/ }
	}
	
	public void resetLife() {
		this.lifes = 3;
	}
	
	public void loseGame() {
		if (this.canContinue) {
			useContinue();
		} else {
			// handle gameover screen
		}
	}
	
	public void useContinue() {
		this.canContinue = false;
		// handle continue screen
	}
	
}