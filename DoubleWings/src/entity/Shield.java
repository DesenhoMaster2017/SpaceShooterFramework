package entity;

import entity.GameEntity;
import observer.GameEntityObserver;

public class Shield extends GameEntity {

	private static String spriteFilePath = "src/assets/img/temp_shield.png";
	
	private int regeneration;
	private Player player;
	private GameEntityObserver observer = null;
	static private String spriteImagePath = "src/assets/img/temp_shield.png";
    
	//Creation constructor to Shield
	public Shield(Player player) {
		
    //Initialization with shield image
		super(spriteFilePath, 10);
        
		//Getting the player from the StageTest class
		this.player = player;

		//Putting shield on the screen with reference the player position
		this.x = player.x;
		this.y = player.y;
	}
	
	public Shield(GameEntity player, int life) {
		// Initializing shield's image and life
		super(spriteImagePath, life);
		
		//Getting the player from the StageTest class
		this.player = player;

				//Putting shield on the screen with reference the player position
		this.x = player.x;
		this.y = player.y;
	}
    
	//Method to update the shield according the player
	public void update() {
		
		super.update();
		
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
			
			entity.receiveDamage(100);
			this.receiveDamage(10);
			System.out.println("hit enemy");
		}
	}
	
	@Override
	public void setLife(int newLife){
		super.setLife(newLife);
		//Notifing HUD to update shield life bar
		if (observer != null) {
			observer.notifyObserver(this);
		} else {
			System.out.println("Shield log: HUD is null :(");
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
