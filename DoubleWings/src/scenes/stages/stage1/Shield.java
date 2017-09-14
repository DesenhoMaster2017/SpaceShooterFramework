package scenes.stages.stage1;

import constants.WindowConstants;
import jplay.Sprite;
import scenes.GameScene;

public class Shield extends Sprite{

	private int life;
	private int regeneration;
	// private Sprite shield;

	public Shield(){

		super("src/assets/img/temp_shield.png");

		//Putting shield on the screen
		this.x = WindowConstants.WIDTH/2 - this.width/2;
		this.y = WindowConstants.HEIGHT - this.height;
		
	}

}
