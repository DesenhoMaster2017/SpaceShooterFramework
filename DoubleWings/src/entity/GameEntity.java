package entity;

import game.GameController;
import jplay.Sprite;
import scenes.ClassicContinue;
import scenes.GameScene;

public class GameEntity extends Sprite {

	public String name;
	private int life = 1;
	private boolean isDead = false;
	public Double velx = 0.0;
	public Double vely = 0.0;
	
	
	public GameEntity(String fileName) {
		super(fileName);
		name = fileName;
		// TODO Auto-generated constructor stub
	}
	
	public void setLife(int newLife){
		this.life = newLife;
		
		if (life <= 0) {
			die();
			GameScene countdown = new ClassicContinue();
			GameController game = new GameController();
			game.transitTo(countdown);
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
	
	private void die(){
		
		isDead = true;
		
		System.out.println("Entity is dead!");
		destroy();
	}
	
	public void destroy(){
		
	}
}