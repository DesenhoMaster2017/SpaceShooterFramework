package game;

import java.util.ArrayList;
import java.util.HashMap;

import entity.pool.*;
import game.evolver.*;
import entity.GameEntity;

public class World {
	
	private ArrayList<GameEntity> objs;
	private ArrayList<GameEntity> deadObjs; //Array that Handles dead entities
	private GameEvolver evolver = new GameEvolver();
	private HashMap<Class, ObjectPool> pools = new HashMap<Class, ObjectPool>();

	
	public World() {
		objs = new ArrayList<GameEntity>();
		deadObjs = new ArrayList<GameEntity>();
		
		evolver.start();
	}
	
	public void add(GameEntity entity) {
		objs.add(entity);
		entity.setGameWorld(this);
	}
	
	public void remove(GameEntity entity) {
		objs.remove(entity);
	}
	
	public void update() {
		
		evolver.update();
		
		// check all collisions
		for(int i = 0; i < objs.size(); i++) {
			GameEntity obj1 = objs.get(i);
			
			//Handle collision 
			handleCollision(i, obj1);
			
			//Basic update for entity
			this.updateEntity(obj1);
			
			//Check pools
			if (this.checkObjRecycling(obj1) == true){
				deadObjs.add(obj1);
			}
		}
		
		//Clearing dead objs
		clearDeadObjs();
		
	}
	
	//GameEvent Facade
	public void addEvent(GameEventCallback callback, int time, int type, String name){
		GameEvent event = this.createNewEvent(callback, time, type, name);
		this.evolver.add(event);
	}
	
	public void addEventAfterCurrentTime(GameEventCallback callback, int time, int type, String name){
		GameEvent event = this.createNewEvent(callback, time, type, name);
		event.time += evolver.getCurrentIteration();
		this.evolver.add(event);
	}
	
	private GameEvent createNewEvent(GameEventCallback callback, int time, int type, String name){
		GameEvent event = new GameEvent();
		event.setCallback(callback);
		event.time = time;
		event.name = name;
		event.type = type;
		
		return event;
	}
	
	// Object Pool facade
	public Object createEntity(Class c){
		
		ObjectPool pool = this.pools.get(c);
		Object obj = null;

		if (pool != null){
			obj = pool.release();
		}
		
		return obj;
	}
	public void recycle(Object obj){
		ObjectPool pool = this.pools.get(obj.getClass());

		if (pool != null){
			pool.acquire(obj);
		}
	}
	
	//Collision
	private void handleCollision(int i, GameEntity obj1){
		// i + 1 to not repeat obj collision check
		for(int k = i+1; k <objs.size(); k++) {
			GameEntity obj2 = objs.get(k);

			//Check collision
			if(obj1.isCollidable && obj2.isCollidable && obj1.collided(obj2)) {
				obj1.didContact(obj2);
				obj2.didContact(obj1);
			}
		}
	}
	
	private void updateEntity(GameEntity obj1){
		// Draw and update all objects
		obj1.draw();
		obj1.update();

		// Update object position
		obj1.x += obj1.velx;
		obj1.y += obj1.vely;
	}
	
	private void clearDeadObjs(){
		for (GameEntity deadObj : deadObjs){
			boolean didRemove = objs.remove(deadObj);
			
			if (didRemove == true){
				//System.out.println("Entity removed from the world");
			}else{
				System.out.println("Error removing entity");
			}
		}
		
		deadObjs.clear();
	}
	
	private Boolean checkObjRecycling(Object obj){
		ObjectPool pool = this.pools.get(obj.getClass());
		
		if (pool != null){
			if (pool.shouldRecycle(obj)){
				pool.acquire(obj);
				return true;
			}
		}
		
		return false;
	}
	
	public void addPool(ObjectPool<?> pool){
		this.pools.put(pool.recyclingClass(), pool);
	}
	
	
}

