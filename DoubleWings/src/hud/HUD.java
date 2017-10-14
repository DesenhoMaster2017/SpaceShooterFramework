package hud;

import jplay.Sprite;
import observer.GameEntityObserver;
import constants.WindowConstants;
import entity.GameEntity;
import entity.Player;
import entity.Shield;

public class HUD implements GameEntityObserver{

	private Sprite shieldLifeBar;
	private Sprite shieldLifeBarOrnament;
	private Sprite chancesSimbol;
  
	//Copy of the attributes observed by the HUD
	private int score;

	public HUD(){
		//Setting HUD elements initial setups
		shieldLifeBar = new Sprite("src/assets/img/hud/energy.png");
		this.shieldLifeBar.x = WindowConstants.WIDTH/2 - this.shieldLifeBar.width/2;
		this.shieldLifeBar.y = WindowConstants.HEIGHT - this.shieldLifeBar.height;
		
		shieldLifeBarOrnament = new Sprite("src/assets/img/hud/shield_bar_ornament.png");
		this.shieldLifeBarOrnament.x = 0;
		this.shieldLifeBarOrnament.y = WindowConstants.HEIGHT - this.shieldLifeBarOrnament.height;
    
		chancesSimbol = new Sprite("src/assets/img/hud/chances.png", 4);
		this.chancesSimbol.setCurrFrame(3);
		this.chancesSimbol.x = 0;
		this.chancesSimbol.y = 0;
	}

	// TIP: Perhaps use a pattern to specialize all the updates
	public void update(){
	  
	}

	public void draw(){
		shieldLifeBar.draw();
		shieldLifeBarOrnament.draw();
		chancesSimbol.draw();
		this.drawScore();
	}
  
	public void drawScore() {
	  
	}
  
	public void updateShieldLifeBar(int shieldLife){
		//Make life bar width proportional to the shield current life
//		System.out.println("Shield changed in the hu");
//		System.out.println(shieldLife);
		float proportion = (float) shieldLife / 20;
//		System.out.println(proportion);
		float newLifeBarWidth = proportion * this.shieldLifeBar.width;
		this.shieldLifeBar.width = (int) newLifeBarWidth;
//		System.out.println(shieldLifeBar.width);
		this.shieldLifeBar.x = WindowConstants.WIDTH/2 - this.shieldLifeBar.width/2;
	}

	//Update player chances on HUD
	public void updateChances(int playerChances){
		if (playerChances <= 3 && playerChances >= 0) {
			this.chancesSimbol.setCurrFrame(playerChances);
			
		} else {
			System.out.println("HUD log: Player chances number is out of range.");
			this.chancesSimbol.setCurrFrame(0);
		}
	}

	public void updateScore(){
   
	}

	//Take action depending of the game entity 
	@Override
	public void notifyObserver(GameEntity entity) {
		if (entity instanceof Shield) {
			//System.out.println("HUD log: Shield class indentified.");
			updateShieldLifeBar(entity.getLife());
			
		} else if (entity instanceof Player){
			System.out.println("HUD log: Player class indentified.");
			Player player = (Player) entity;
			updateChances(player.getChances());
			
		} else {
			System.out.println("HUD log: No class identified.");
		}	
	}
}
