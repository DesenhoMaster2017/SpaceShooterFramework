package entity;

import entity.GameEntity;

public class Shield extends GameEntity {

	private int regeneration;
	private GameEntity player;
	static private String spriteImagePath = "src/assets/img/temp_shield.png";
    
	//Creation constructor to Shield
	public Shield(GameEntity player) {
		
        //Initialization with shield image
		super(spriteImagePath);
        
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
			this.receiveDamage(20); // test purposes
			System.out.println("hit enemy");
		}
	}
}
