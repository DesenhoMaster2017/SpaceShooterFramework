package hud;

import entity.Player;
import jplay.Sprite;
import observer.GameEntityObserver;

public class HUD implements GameEntityObserver{

  private Player player; // Variable to read player informations
  private Sprite shieldLifeBar;
  private Sprite chances;
  // private (int or Sprite) score;

  public HUD(){

    // Load HUD elements' sprite and sets the number of frames
    // FIX: Add actual images
    shieldLifeBar = new Sprite(".png", 3);
    chances = new Sprite(".png", 3);
  }

  // TIP: Perhaps use a pattern to specialize all the updates
  public void update(){
	  
  }

  public void draw(){

    shieldLifeBar.draw();
    chances.draw();
    this.drawScore();
  }
  
  public void drawScore() {
	  
  }
  
  public void updateShieldLifeBar(){

  }

  public void updateChances(){

  }

  // public void updateScore(){
  //
  // }

}
