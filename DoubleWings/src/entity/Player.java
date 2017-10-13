package entity;

import hud.HUD;
import observer.GameEntityObserver;

public class Player extends GameEntity {

	private Shield shield;
	private int score = 0; // Score default value
	private int chances = 3; // Initially the player will have three lifes
	private GameEntityObserver observer = null; //Temp solution to the observer

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
	
	// HUD observer getter and setter 
	public GameEntityObserver getObserver() {
		return this.observer;
	}
	
	public void setObserver(HUD observer) {
		this.observer = observer;
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
	
	//Chances setters and getters
	public void setChances(int chances){
		//Notifying HUD to update chances shower
//		observer.updateChances(this.chances);
	}
	
	public int getChances() {
		return this.chances;
	}
}
