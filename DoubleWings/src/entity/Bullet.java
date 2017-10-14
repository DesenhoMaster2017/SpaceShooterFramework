package entity;



public class Bullet extends GameEntity {

	public Bullet(String fileName, double x, double y, GameEntity owner){
		super(fileName);
		
		Integer horizontalCorrection = (this.width - owner.width)/2;
		
		this.x = owner.x - horizontalCorrection;
		
		if (owner.getClass() == Player.class){
			this.y = owner.y;
		}else {
			this.y = owner.y + owner.height;
		}
    }
    
}
