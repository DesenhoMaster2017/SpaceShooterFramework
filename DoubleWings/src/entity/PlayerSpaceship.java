package entity;

import jplay.Keyboard;

public class PlayerSpaceship extends GameEntity {
	
	private Shield shield;
	private static String spriteImagePath = "src/assets/img/temp_player.png";

	public PlayerSpaceship() {
		super(spriteImagePath);
		this.shield = new Shield(this, 10);
	}

	@Override
	public void didContact(GameEntity entity) {
		if (entity.getClass() == Shield.class) {
			
		}else if (entity.getClass() == Enemy.class) {
			
			entity.receiveDamage(100); // test purposes
			this.receiveDamage(20); // test purposes
		}else {
			
		}
	}
	
	@Override
	public void draw() {
		super.draw();
		if (this.shield != null) {
			this.shield.draw();
		}
	}
	
	public void move() {
		this.moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, 4);//velocity = 1
		this.moveY(Keyboard.UP_KEY, Keyboard.DOWN_KEY, 4);//velocity = 1
	}
}
