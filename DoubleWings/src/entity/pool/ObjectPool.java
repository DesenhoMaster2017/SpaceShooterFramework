package entity.pool;

import java.util.ArrayList;

import entity.Bullet;

public abstract class ObjectPool<Type> {

	private ArrayList<Type> objects = new ArrayList<Type>();
	
	public Type release(){
		
		Type obj;
		
		if (objects.isEmpty()){
			obj = create();
		}else{
			int last = objects.size() -1;
			obj = objects.remove(last);
		}
		
		return obj;
	}
	
	protected abstract Type create();
	public abstract Boolean shouldRecycle(Type obj);
	public abstract Class recyclingClass();
	
	public void acquire(Type obj){
		if (objects.contains(obj) == false){
			objects.add(obj);
		}
	}
	
}
