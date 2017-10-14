package entity;


public class Player extends GameEntity {
	
	public int upKey = 0;
	public int downKey = 0;
	public int leftKey = 0;
	public int rightKey = 0;
	public int shootKey = 0;

	public Player(String fileName) {
		super(fileName);
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
	
	@Override
	public void update(){
		super.update();
		checkInput();
	}
	
	public void checkInput(){
		
		//Player movement
		moveX(leftKey, rightKey, this.velx);
		moveY(upKey, downKey, this.vely);
		
		//shootKey
	}
	
}
