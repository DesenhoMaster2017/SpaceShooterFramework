package entity;



public class Player extends GameEntity {

	public Player(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
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

	public Bullet fireBullet(){		
		Bullet bullet = new Bullet("src/assets/img/bullet_player.png", this.x, this.y, this);
		bullet.vely = -10.0;
		
		gameWorld.add(bullet);
		
		return bullet;
	}
	
}
