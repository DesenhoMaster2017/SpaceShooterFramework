package entity;

import commands.Command;

public class Enemy extends GameEntity {

	static private String spriteImagePath = "src/assets/img/temp_player.png";
	private int commandCount = 0;

	public Enemy(int x, int y) {
		super(Enemy.spriteImagePath);
		this.x = x;
		this.y = y;
	}
	
	public void executeBehavior(Command[] commands) {
		if (commandCount < commands.length) {
			if (commands[commandCount].execute(this)) {
				commandCount += 1;
			} else {/*donot*/}
		System.out.println("x: " + this.x + " y: " + this.y);
		} else {/*donot*/}
	}
	
	public Enemy(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void didContact(GameEntity entity){
		
	}
	
}
