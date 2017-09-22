package game_actors;

import commands.*;
import jplay.Sprite;

public class Enemy extends Sprite {
	static private String spriteImagePath = "src/assets/img/temp_player.png";
	private final MoveCommand[] behavior = {new MoveLeft(), new MoveDown(), new MoveUp(), new MoveRight()};
	private int commandCount = 0;

	public Enemy(int x, int y) {
		super(Enemy.spriteImagePath);
		this.x = x;
		this.y = y;
	}
	
	public void executeBehavior() {
		if (commandCount < behavior.length) {
			if (behavior[commandCount].execute(this)) {
				commandCount += 1;
			} else {/*donot*/}
		System.out.println("x: " + this.x + " y: " + this.y);
		} else {/*donot*/}
	}
}
