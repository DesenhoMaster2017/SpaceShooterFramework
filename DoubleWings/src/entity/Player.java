package entity;

public class Player extends GameEntity {

	private Shield shield;
	private int score = 0; // Score default value
	private int chances = 3; // Initially the player will have three lifes

	public Player(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub

	}

	public Shield getShield(Shield shield){

		return shield;
	}

	public void setShield(Shield newShield) {

		this.shield = newShield;
	}

	@Override
	public void didContact(GameEntity entity){
		if (entity.getClass() == Shield.class){

		}else if (entity.getClass() == Enemy.class){

			entity.receiveDamage(100); // test purposes
			this.receiveDamage(20); // test purposes

		}else {

		}
	}

}
