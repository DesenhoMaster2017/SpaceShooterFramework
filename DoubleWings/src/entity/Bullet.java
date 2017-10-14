package entity;

public class Bullet extends GameEntity {
	final private int BULLETSPEED = 10;

	public Bullet(String fileName, double x, double y, Player player){
		super(fileName);
		
		Integer horizontalCorrection = (this.width - player.width)/2;
		
		this.x = player.x - horizontalCorrection;
		this.y = player.y;
    }
    
  		

    public void moveBullet() {
        // moveY(BULLETSPEED);
    		y -= BULLETSPEED;
    }
}
