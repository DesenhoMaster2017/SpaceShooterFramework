package entity;

import java.util.ArrayList;

public class Player extends GameEntity {
	
	private ArrayList<Bullet> firedBullets = new ArrayList<Bullet>();
	
	public ArrayList<Bullet> getFiredBullets() {
		return firedBullets;
	}

	public void setFiredBullets(ArrayList<Bullet> firedBullets) {
		this.firedBullets = firedBullets;
	}

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
		firedBullets.add(bullet);
		System.out.println("Bullet fired count: " + firedBullets.size());
		
		return bullet;
	}

	public void removeBullet(Bullet bullet) {
		firedBullets.remove(bullet);
		bullet.setLife(0);
	}
	
}
