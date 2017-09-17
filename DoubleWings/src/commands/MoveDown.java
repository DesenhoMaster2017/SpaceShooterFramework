package commands;

import jplay.Sprite;

public class MoveDown implements Command {

	@Override
	public void execute(Sprite actor) {
		actor.moveTo(actor.x, actor.y + 4, 4);
	}

}
