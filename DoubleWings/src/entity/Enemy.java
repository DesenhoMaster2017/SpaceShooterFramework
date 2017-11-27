package entity;

import java.util.ArrayList;
import Score.ScoreType;
import behavior.BehaviorExecutor;
import commands.Command;
import entity.player.PlayerSpaceship;

public class Enemy extends GameEntity implements BehaviorExecutor {

	static private String spriteImagePath = "src/assets/img/temp_player.png";
	private ArrayList<Command> behavior;
	private int commandCount = 0;
	private boolean mustBehave = false;
	
	public Enemy(String fileName) {
		super(fileName);
	}

	public Enemy(int x, int y) {
		super(Enemy.spriteImagePath);
		this.x = x;
		this.y = y;
	}

	public Enemy(int x, int y, ArrayList<Command> behavior) {
		super(Enemy.spriteImagePath);
		this.x = x;
		this.y = y;
		this.behavior = behavior;
	}
	
	@Override
	public void update() {
		super.update();
		if (mustBehave) {
			executeBehavior();
		} else { /*do nothing*/ }
	}

	@Override
	public void didContact(GameEntity entity){
		if (entity.getClass() == Bullet.class) {
			entity.receiveDamage(100); // test purposes
			this.receiveDamage(20); // test purposes
			Bullet bullet = (Bullet) entity;
			PlayerSpaceship spaceship = (PlayerSpaceship) bullet.owner;
			spaceship.getPlayer().increaseScore(ScoreType.LOW);
		}
	}
	
	public void executeBehavior() {
		if (commandCount < this.behavior.size()) {
			if (this.behavior.get(commandCount).execute(this)) {
				commandCount += 1;
			} else {/*donot*/}
		} else {/*donot*/}
	}
	
	public void addBehavior(ArrayList<Command> behavior) {
		this.behavior = behavior;
	}
	
	public void resetBehavior() {
		this.commandCount = 0;
	}
	
	public void startBehaving() {
		this.mustBehave = true;
	}
	
	public void stopBehaving() {
		this.mustBehave = false;
	}
	
}
