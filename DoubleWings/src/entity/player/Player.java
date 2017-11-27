package entity.player;

import entity.Bullet;
import util.*;
import entity.Enemy;
import entity.GameEntity;
import util.DelayTimer;

public class Player extends GameEntity implements DelayDelegate {
	
	// default sprite file path
	private static final int defaultMovimentVel = 7;
	
	private Shield shield;
	private PlayerController controller;
	
	public String spriteImagePath = "src/assets/img/player_lvl1.png";
	
	public int shootCooldown = 180;
	private boolean canShoot = true;
	private DelayTimer shootCDTimer = new DelayTimer(this, 1);
	
	public double movimentVel = defaultMovimentVel; // default value

	public Player(PlayerController controller, double x, double y, boolean adjust) {
		super("");
		this.loadImage(spriteImagePath);
		this.life = maxLife;
		
		this.shield = new Shield(this);
		this.shield.maxLife = 30;
		this.shield.setLife(30);
		
		this.controller = controller;
		if (adjust) {
			// x position fixed for sprite width
			this.x = x - this.width / 2;	
		} else {
			this.x = x;
		}
		this.y = y;
		
		controller.entity = this;
	}

	@Override
	public void didContact(GameEntity entity){
		if (entity.getClass() == Shield.class){
			
		} else if (entity.getClass() == Enemy.class) {
			
			entity.receiveDamage(100); 
			
			if (shield.getLife() <= 0) { // security check to avoid double dying bug
				this.receiveDamage(5);	
			}
			
		}else {
			
		}
	}
	
	@Override
	public void update(){
		super.update();
		controller.update();
	}
	

	public Shield getShield() {
		return this.shield;
	}
	
	public PlayerController getPlayerController() {
		return this.controller;
	}
	
	@Override
	public void reborn(){
		super.reborn();
		this.shield.reborn();
	}
	
	@Override
	public void setLife(int newlife){
		this.life = newlife;
		
		if (this.life < 0){
			this.life = 0;
		}
	}
	
	@Override
	public boolean isDead(){
		return false;
	}
	
	public void fireBullet(){
		
		if (canShoot){
			canShoot = false;
			this.shootCDTimer.schedule(this.shootCooldown);
			
			//System.out.println("Fire Bullet!");
			Bullet bullet = new Bullet();
			bullet.fireBy(this, -10);
			this.addToGameWorld(bullet);
		}
	}

	@Override
	public void delayEnded(DelayTimer timer) {
		if (timer.getType() == 1){
			this.canShoot = true;
		}
	}

}
