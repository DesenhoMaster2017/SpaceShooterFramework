package entity;

import jplay.Keyboard;

public class PlayerSpaceship extends GameEntity {
	
	private Shield shield;

	public PlayerSpaceship(String fileName) {
		super(fileName);
		this.shield = new Shield(this);
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
	
	public void move() {
		this.moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, 4);//velocity = 1
		this.moveY(Keyboard.UP_KEY, Keyboard.DOWN_KEY, 4);//velocity = 1
	}
	
	public Shield getShield() {
		return this.shield;
	}
	
}
