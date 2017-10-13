package entity;

import jplay.Sprite;
import observer.GameEntityObserver;

public class GameEntity extends Sprite {

	public String name;
	protected int life = 1;
	private boolean isDead = false;
	public Double velx = 0.0;
	public Double vely = 0.0;
	
	public GameEntity(String fileName) {
		super(fileName);
		name = fileName;
		// TODO Auto-generated constructor stub
	}
	
	//Life getter and setter
	public int getLife() {
		return this.life;
	}
	
	public void setLife(int newLife){
		this.life = newLife;
		
		if (life <= 0) {
			die();
		}
	}
	
	public boolean isDead(){
		return isDead;
	}
	
	//Trigger an event when contact happens
	public void didContact(GameEntity entity) {
		System.out.println(this.name + " contact: " + entity.name);
	}
	
	public void receiveDamage(int damage){
		setLife(life - damage);
	}
	
	protected void die(){
		
		isDead = true;
		
		System.out.println("Entity is dead!");
		destroy();
	}
	
	public void destroy(){
		
	}

}