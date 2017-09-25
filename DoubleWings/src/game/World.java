package game;

import java.util.ArrayList;

import entity.GameEntity;

public class World {
	
	private ArrayList<GameEntity> objs;
	private ArrayList<GameEntity> deadObjs; //Array that Handles dead entities
	
	public World() {
		objs = new ArrayList<GameEntity>();
		deadObjs = new ArrayList<GameEntity>();
	}
	
	public void add(GameEntity entity) {
		objs.add(entity);			
	}
	
	public void remove(GameEntity entity) {
		objs.remove(entity);
	}
	
	public void update() {
		
		// check all collisions
		for(int i = 0; i < objs.size(); i++) {
			GameEntity obj1 = objs.get(i);
			
			// i + 1 to not repeat obj collision check
			for(int k = i+1; k <objs.size(); k++) {
				GameEntity obj2 = objs.get(k);
				
				if(obj1.collided(obj2)) {
					obj1.didContact(obj2);
					obj2.didContact(obj1);
				}
			}
			
			// Draw and update all objects
			obj1.draw();
			obj1.update();
			
			// Update object position
			obj1.x += obj1.velx;
			obj1.y += obj1.vely;
			
			// Check if entity is dead
			if (obj1.isDead()) {
				deadObjs.add(obj1);
			}
		}
		
		for (GameEntity deadObj : deadObjs){
			boolean didRemove = objs.remove(deadObj);
			if (didRemove == true){
				System.out.println("Entity removed from the world");
			}else{
				System.out.println("Error removing entity");
			}
		}
		
		deadObjs.clear();
	}
}
