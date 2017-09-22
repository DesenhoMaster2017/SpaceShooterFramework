package commands;

import jplay.Sprite;

public abstract class MoveCommand {
	/**
	 * The distance yet to be traveled by the actor of this command.
	 * */
	protected double distanceToGo = 50;
	
	/**
	 * Move an actor by `distanceToGo` pixels in the command direction. Must be called inside the update() method to work properly.
	 * @param actor the actor to be moved by the command.
	 * @return true if movement is completed, false otherwise. 
	 * */
	public boolean execute(Sprite actor) {
		if (this.distanceToGo > 0) {
			moveActor(actor);
			this.distanceToGo -= 1;
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Define how the actor will be moved in each call to this command. Must override.
	 * @param actor the actor to be moved by the command. 
	 * */
	protected abstract void moveActor(Sprite actor);
}
