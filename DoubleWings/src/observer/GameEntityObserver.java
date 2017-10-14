package observer;

import entity.GameEntity;

public interface GameEntityObserver {
	public void notifyObserver(GameEntity entity);
}
