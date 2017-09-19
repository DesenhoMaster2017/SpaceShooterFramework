package behavior;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class Command {
	
	/**
	 * Constructor.
	 * @param commandText text of a command to be assigned to an enemy.
	 */
	public Command(String commandText){
		Command.commandText = commandText;
	}
	
	public static String getCommandText() {
		return commandText;
	}

	public static void setCommandText(String commandText) {
		Command.commandText = commandText;
	}
	
	// PRIVATE 
	private static String commandText;

}
