package commands;

import jplay.Sprite;

public class MoveRight extends MoveCommand {
	@Override
	protected void moveActor(Sprite actor) {
		actor.x += 1;
	}
}
