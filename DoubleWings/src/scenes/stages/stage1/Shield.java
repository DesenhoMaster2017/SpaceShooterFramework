package scenes.stages.stage1;

import constants.WindowConstants;
import jplay.Sprite;
import scenes.GameScene;

public class Shield extends Sprite{

	private int life;
	private int regeneration;
	// private Sprite shield;

	private Sprite player;

	public Shield(Sprite player){

		super("src/assets/img/temp_shield.png");

		this.player = player;

		//Putting shield on the screen
		this.x = player.x;
		this.y = player.y;

	}

	public void update(){

		//Shield movement
		Integer horizontalCorrection = (this.width - player.width)/2;
		Integer verticalCorrection = (this.height - player.height)/2;

		this.x = player.x - horizontalCorrection;
		this.y = player.y - verticalCorrection;

	}

}
