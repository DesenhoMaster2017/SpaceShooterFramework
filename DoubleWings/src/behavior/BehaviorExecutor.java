package behavior;

import java.util.ArrayList;

import commands.Command;

public interface BehaviorExecutor {
	void executeBehavior();
	void addBehavior(ArrayList<Command> behavior);
	void resetBehavior();
	void startBehaving();
	void stopBehaving();
}
