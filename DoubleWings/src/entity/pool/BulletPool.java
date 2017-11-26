package entity.pool;

import entity.Bullet;
import entity.Enemy;
import entity.GameEntity;

public class BulletPool extends ObjectPool<Bullet>{

	@Override
	public Bullet create(){
		Bullet bullet = new Bullet();
		return bullet;
	}
	
	@Override
	public Boolean shouldRecycle(Bullet b){
		return b.isDead();
	}
	
	@Override
	public Class recyclingClass(){
		return Bullet.class;
	}
	
	@Override
	public Bullet release(){
		Bullet e = super.release();
		e.reborn();
		return e;
	}
}
