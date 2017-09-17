package commands;

import jplay.Sprite;

public class MoveUp implements Command {

	@Override
	public void execute(Sprite actor) {
		actor.moveTo(actor.x, actor.y - 4, 4);
	}

}
