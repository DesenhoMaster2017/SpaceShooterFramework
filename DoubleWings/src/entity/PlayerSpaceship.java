package entity;

import jplay.Keyboard;

public class PlayerSpaceship extends GameEntity {
	
	// default sprite file path
	private static final String spriteImagePath = "src/assets/img/temp_player.png"; 
	private static final int defautlLife = 1;
	private static final int defaultMovimentVel = 4;
	
	private Shield shield;
	private Player player;
	
	// Default values for keys. Can be reset using setKeySet 
	private int upKey = Keyboard.UP_KEY;
	private int downKey = Keyboard.DOWN_KEY;
	private int leftKey = Keyboard.LEFT_KEY;
	private int rightKey = Keyboard.RIGHT_KEY;
	private int shootKey = 0;
	
	public double movimentVel = defaultMovimentVel; // default value

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
	
	public void checkInput(){
		
		//Player movement
		moveX(leftKey, rightKey, this.movimentVel);
		moveY(upKey, downKey, this.movimentVel);
		
		//shootKey
	}
	
	public void setKeySet(int upKey, int downKey, int rightKey, int leftKey, int shootKey) {
		this.upKey = upKey;
		this.downKey = downKey;
		this.rightKey = rightKey;
		this.leftKey = leftKey;
		this.shootKey = shootKey;
	}

	@Override
	public void update() {
		
		super.update();
		
		
		if (this.isDead()) {
			// Enter here if the spaceship is destroyed
			this.player.loseLife();
		} else {
			checkInput();
		}
	}
	

}
