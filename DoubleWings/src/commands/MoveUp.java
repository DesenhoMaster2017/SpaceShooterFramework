package commands;

import jplay.Sprite;

public class MoveUp extends MoveCommand {
	@Override
	protected void moveActor(Sprite actor) {
		actor.y -= 1;
	}
}
