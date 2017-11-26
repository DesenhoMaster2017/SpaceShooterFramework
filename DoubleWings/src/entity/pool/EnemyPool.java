package entity.pool;

import entity.Enemy;

public class EnemyPool extends ObjectPool<Enemy>{

	@Override
	protected Enemy create(){
		Enemy enemy = new Enemy("src/assets/asteroid.png");
		return enemy;
	}

	@Override
	public Boolean shouldRecycle(Enemy obj) {
		return obj.isDead();
	}

	@Override
	public Class recyclingClass() {
		return Enemy.class;
	}
	
	@Override
	public Enemy release(){
		Enemy e = super.release();
		e.reborn();
		return e;
	}
}
