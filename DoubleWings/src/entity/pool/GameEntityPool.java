package entity.pool;

import java.util.ArrayList;
import entity.GameEntity;

public class GameEntityPool {

	private ArrayList<GameEntity> entities = new ArrayList<GameEntity>();
	
	public GameEntity getEntity(){
		
		GameEntity entity;
		
		if (entities.isEmpty()){
			//placeholder
			entity = new GameEntity("src/assets/asteroid.png");
		}else{
			int last = entities.size() -1;
			entity = entities.remove(last);
		}
		
		return entity;
	}
	
	public void dumbEntity(GameEntity entity){
		if (entities.contains(entity) == false){
			entities.add(entity);
		}
	}
	
}
