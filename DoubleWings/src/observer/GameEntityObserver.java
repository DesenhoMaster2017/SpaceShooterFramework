package observer;

import entity.GameEntity;

public interface GameEntityObserver {
	public void setEntity(GameEntity entity);
	public void notifyObserver(GameEntity entity);
}
