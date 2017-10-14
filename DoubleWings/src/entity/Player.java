package entity;

import java.util.Timer;
import java.util.TimerTask;
import util.DelayDelegate;
import util.*;
import jplay.Keyboard;

public class Player extends GameEntity implements DelayDelegate{
	
	public int upKey = 0;
	public int downKey = 0;
	public int leftKey = 0;
	public int rightKey = 0;
	public int shootKey = 0;
	
	public double movimentVel = 0;
	
	public int shootCooldown = 100;
	private boolean canShoot = true;
	private DelayTimer shootCDTimer = new DelayTimer(this, 1);

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

	public void fireBullet(){
		
		if (canShoot){
			canShoot = false;
			this.shootCDTimer.schedule(this.shootCooldown);
			
			//System.out.println("Fire Bullet!");
			Bullet bullet = new Bullet();
			bullet.fireBy(this, -10);
			gameWorld.add(bullet);
		}
		
	}
	
	@Override
	public void update(){
		super.update();
		checkInput();
	}
	
	public void checkInput(){
		
		//Player movement
		moveX(leftKey, rightKey, this.movimentVel);
		moveY(upKey, downKey, this.movimentVel);
		
		//shootKey
		if(gameWorld != null){
			if (gameWorld.keyboard != null){
				if(gameWorld.keyboard.keyDown(shootKey)){
					this.fireBullet();
				}
			}
		}
	}
	
	@Override
	public void moveX(int leftKey, int rightKey, double vel){
		if(gameWorld != null){
			
			if (gameWorld.keyboard != null){
				
				if(gameWorld.keyboard.keyDown(leftKey)){
					this.x -= vel;
				}
				
				if(gameWorld.keyboard.keyDown(rightKey)){
					this.x += vel;
				}
			}
		}
	}
	
	@Override
	public void moveY(int upKey, int downKey, double vel){
		if(gameWorld != null){
			
			if (gameWorld.keyboard != null){
				
				if(gameWorld.keyboard.keyDown(upKey)){
					this.y -= vel;
				}
				
				if(gameWorld.keyboard.keyDown(downKey)){
					this.y += vel;
				}
			}
		}
	}

	@Override
	public void delayEnded(DelayTimer timer) {
		if (timer.getType() == 1){
			this.canShoot = true;
		}
	}
	
}
