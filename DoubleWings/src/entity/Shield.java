package entity;

import entity.GameEntity;
import hud.HUD;
import observer.GameEntityObserver;

public class Shield extends GameEntity {

	private int regeneration;
	private GameEntity player;
	private GameEntityObserver observer = null; //Temp solution to the observer
    
	//Creation constructor to Shield
	public Shield(GameEntity player) {
		
        //Initialization with shield image
		super("src/assets/img/temp_shield.png");
        
		//Getting the player from the StageTest class
		this.player = player;

		//Putting shield on the screen with reference the player position
		this.x = player.x;
		this.y = player.y;
		
	}
    
	//Method to update the shield according the player
	public void update() {
		
		//Shield movement
		Integer horizontalCorrection = (this.width - player.width)/2;
		Integer verticalCorrection = (this.height - player.height)/2;
		
		//Adjusting position player with force shield
		this.x = player.x - horizontalCorrection;
		this.y = player.y - verticalCorrection;
	}
	
	// Handle when contact happen
	@Override
	public void didContact(GameEntity entity){
		if (entity.getClass() == Enemy.class){
			
			entity.receiveDamage(100); // test purposes
			this.receiveDamage(10); // test purposes
			System.out.println("hit enemy");
		}
	}
	
	@Override
	public void setLife(int newLife){
		super.setLife(newLife);
		
		if (observer != null) {
			//Notifing HUD to update shield life bar
			observer.notifyObserver(this);
//			this.observer.updateShieldLifeBar(this.life);
		} else {
			System.out.println("HUD is null :(");
		}
	}
	
	// HUD observer getter and setter 
	public GameEntityObserver getObserver() {
		return this.observer;
	}
		
	public void setObserver(GameEntityObserver observer) {
		this.observer = observer;
	}
}
