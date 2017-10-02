package entity;

import hud.HUD;

public class Player extends GameEntity {

	private Shield shield;
	private int score = 0; // Score default value
	private int chances = 3; // Initially the player will have three lifes
	private HUD observer; //Temp solution to the observer

	public Player(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub

	}

	public Shield getShield(Shield shield){

		return shield;
	}

	public void setShield(Shield newShield) {

		this.shield = newShield;
	}

	@Override
	public void didContact(GameEntity entity){
		if (entity.getClass() == Shield.class){

		}else if (entity.getClass() == Enemy.class){

			entity.receiveDamage(100); // test purposes
			this.receiveDamage(20); // test purposes

		}else {

		}
	}
	
	//Chanses setters and getters
	public void setChances(int chances){
		observer.updateChances(this.chances);
	}
	
	public int getChances() {
		return this.chances;
	}
}
