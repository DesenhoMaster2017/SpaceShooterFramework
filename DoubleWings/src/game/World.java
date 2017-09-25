package game;

import java.util.ArrayList;

import Entity.GameEntity;

public class World {
	
	private ArrayList<GameEntity> objs;
	
	public World() {
		objs = new ArrayList<GameEntity>();
	}
	
	public void add(GameEntity entity) {
		objs.add(entity);			
	}
	public void remove(GameEntity entity) {
		objs.remove(entity);
	}
	public void update() {
		
		for(int i = 0; i < objs.size(); i++) {
			GameEntity obj1 = objs.get(i);
			
			for(int k = i+1; k <objs.size(); k++) {
				GameEntity obj2 = objs.get(k);
				
				if(obj1.collided(obj2)) {
					obj1.didContact(obj2);
					obj2.didContact(obj1);
				}
			}
		}
	}
}
