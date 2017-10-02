package hud;

import jplay.Sprite;
import observer.GameEntityObserver;

public class HUD implements GameEntityObserver{

	private Sprite shieldLifeBar;
	private Sprite chancesSimbol;
  
	//Copy of the attributes observed by the HUD
	private int score;

	public HUD(){
		
		shieldLifeBar = new Sprite(".png");
    
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
		chancesSimbol.draw();
		this.drawScore();
	}
  
	public void drawScore() {
	  
	}
  
	public void updateShieldLifeBar(){

	}

	public void updateChances(int playerChances){
		if (playerChances <= 3 && playerChances >= 0) {
			this.chancesSimbol.setCurrFrame(playerChances);
			
		} else {
			this.chancesSimbol.setCurrFrame(0);
		}
	}

	public void updateScore(){
   
	}

}
