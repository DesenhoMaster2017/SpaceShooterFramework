package commands;

import jplay.Sprite;

public class MoveLeft implements Command {

	@Override
	public void execute(Sprite actor) {
		actor.moveTo(actor.x - 4, actor.y, 4);
	}
}
