package entity;

<<<<<<< HEAD
import jplay.Keyboard;

public class PlayerSpaceship extends GameEntity {
	
	private Shield shield;
	private static String spriteImagePath = "src/assets/img/temp_player.png";

	public PlayerSpaceship() {
		super(spriteImagePath, 5);
		this.shield = new Shield(this, 10);
	}

	@Override
	public void didContact(GameEntity entity) {
		if (entity.getClass() == Shield.class) {
			
		}else if (entity.getClass() == Enemy.class) {
			
			entity.receiveDamage(100); // test purposes
			this.receiveDamage(20); // test purposes
=======
public class PlayerSpaceship extends GameEntity {
	
	// default sprite file path
	private static final String spriteImagePath = "src/assets/img/temp_player.png"; 
	private static final int defautlLife = 1;
	
	private Shield shield;
	
	public PlayerSpaceship() {
		super(spriteImagePath, defautlLife);
		this.shield = new Shield(this);
	}
	
	public PlayerSpaceship(double x, double y) {
		super(spriteImagePath, defautlLife);
		this.shield = new Shield(this);
		
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
			
>>>>>>> lol
		}else {
			
		}
	}
	
<<<<<<< HEAD
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
=======
	public Shield getShield() {
		return this.shield;
	}
	
>>>>>>> lol
}
