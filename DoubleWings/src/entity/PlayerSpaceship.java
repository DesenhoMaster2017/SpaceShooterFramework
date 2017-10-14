package entity;

import jplay.Keyboard;

public class PlayerSpaceship extends GameEntity {
	
	// default sprite file path
	private static final String spriteImagePath = "src/assets/img/temp_player.png"; 
	private static final int defautlLife = 1;
	
	private Shield shield;
	private Player player;

	public PlayerSpaceship(Player player, double x, double y) {
		super(spriteImagePath, defautlLife);
		this.shield = new Shield(this);
		this.player = player;
		
		// x position fixed for sprite width
		this.x = x - this.width / 2;
		this.y = y;
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

	public Shield getShield() {
		return this.shield;
	}
	
	@Override
	public void revive() {
		super.revive();
		this.shield = new Shield(this);
	}

	@Override
	public void update() {
		if (this.isDead()) {
			// Enter here if the spaceship is destroyed
			this.player.loseLife();
		} else {
			// Track keyboard inputs for movement
			this.moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, 4);//velocity = 1
			this.moveY(Keyboard.UP_KEY, Keyboard.DOWN_KEY, 4);//velocity = 1
		}
	}
	

}
