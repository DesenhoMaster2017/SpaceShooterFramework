package scenes.stages.stage1;

import jplay.Sprite;

public class Shield extends Sprite {

	private int life;
	private int regeneration;
	private Sprite player;
    
	//Creation constructor to Shield
	public Shield(Sprite player) {
		
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
}
