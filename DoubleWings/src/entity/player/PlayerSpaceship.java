package entity.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import entity.Bullet;
import util.*;
import entity.Enemy;
import entity.GameEntity;
import input.Input;
import jplay.Keyboard;
import util.DelayTimer;

public class PlayerSpaceship extends GameEntity implements DelayDelegate, KeyListener{
	
	// default sprite file path
	private static final String spriteImagePath = "src/assets/img/player_lvl1.png"; 
	private static final int defaultMovimentVel = 4;
	
	private Shield shield;
	private Player player;
	
	// Default values for keys. Can be reset using setKeySet 
	private int upKey = Keyboard.UP_KEY;
	private int downKey = Keyboard.DOWN_KEY;
	private int leftKey = Keyboard.LEFT_KEY;
	private int rightKey = Keyboard.RIGHT_KEY;
	private int shootKey = 0;
	
	public int shootCooldown = 100;
	private boolean canShoot = true;
	private DelayTimer shootCDTimer = new DelayTimer(this, 1);
	
	public double movimentVel = defaultMovimentVel; // default value
	private boolean didDie = false;

	public PlayerSpaceship(Player player, double x, double y, boolean adjust) {
		super(spriteImagePath);
		this.life = maxLife;
		this.shield = new Shield(this);
		this.player = player;
		if (adjust) {
			// x position fixed for sprite width
			this.x = x - this.width / 2;	
		} else {
			this.x = x;
		}
		this.y = y;
		
		setupInputListen();
		setupDefaultAction();
	}

	@Override
	public void didContact(GameEntity entity){
		if (entity.getClass() == Shield.class){
			
		} else if (entity.getClass() == Enemy.class) {
			
			entity.receiveDamage(100); // test purposes
			
			if (shield.getLife() <= 0) { // security check to avoid double dying bug
				this.receiveDamage(20); // test purposes	
			}
			
		}else {
			
		}
	}
	
	public void setupInputListen(){
		Input.addListener(this);
	}
	
	public void setupDefaultAction(){
		
		//shootKey
		this.addActionToKey(KeyEvent.VK_UP, 1, new RunEvent(){
			@Override
			public void run(Object source) {
				fireBullet();
			}
		});
		
		//move left
		this.addActionToKey(KeyEvent.VK_LEFT, 1, new RunEvent(){
			@Override
			public void run(Object source) {
				x -= movimentVel;
			}
		});
		
		//move right
		this.addActionToKey(KeyEvent.VK_RIGHT, 1, new RunEvent(){
			@Override
			public void run(Object source) {
				x += movimentVel;
			}
		});				
						
	}

	public Shield getShield() {
		return this.shield;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	
	public void setKeySet(int upKey, int downKey, int rightKey, int leftKey, int shootKey) {
		this.upKey = upKey;
		this.downKey = downKey;
		this.rightKey = rightKey;
		this.leftKey = leftKey;
		this.shootKey = shootKey;
	}

	@Override
	public void update() {
		
		super.update();
		
		if (this.life <= 0) {
			// security check to avoid double dying bug
			if (didDie == false) {
				didDie = true;
				
				// Enter here if the spaceship is destroyed
				this.player.loseLife();
			} else { /*do nothing*/ }
		} else {
			checkInput();
		}
	}
	
	@Override
	public void reborn(){
		super.reborn();
		this.shield.reborn();
		this.didDie = false;
	}
	
	@Override
	public void setLife(int newlife){
		this.life = newlife;
		
		if (this.life < 0){
			this.life = 0;
		}
	}
	
	@Override
	public boolean isDead(){
		return false;
	}
	
	public void fireBullet(){
		
		if (canShoot){
			canShoot = false;
			this.shootCDTimer.schedule(this.shootCooldown);
			
			//System.out.println("Fire Bullet!");
			Bullet bullet = new Bullet();
			bullet.fireBy(this, -10);
			this.addToGameWorld(bullet);
		}
		
	}
	
	public void checkInput(){
		
		// Deal pressed manually
		for(int key : this.inputStates.keySet()){
			int state = this.inputStates.get(key);
			if (state == 1){
				InputKey input = new InputKey(key, 1); // Creation can over heat memory
				RunEvent l = this.listeners.get(input.hashCode());
				if(l != null){
					l.run(this);
				}
			}
		}
		
	}

	@Override
	public void delayEnded(DelayTimer timer) {
		if (timer.getType() == 1){
			this.canShoot = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		this.handleInputEvent(new InputKey(e.getKeyCode(), 0));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		inputStates.put(e.getKeyCode(), 1);
		//this.handleInputEvent(new InputKey(e.getKeyCode(), 1));
		
		// pressed State is handle manually
		// keyPressed only, produces too much delay
	}

	@Override
	public void keyReleased(KeyEvent e) {
		inputStates.put(e.getKeyCode(), 2);
		this.handleInputEvent(new InputKey(e.getKeyCode(), 2));
	}
	
	private class InputKey {
		private int key = -1;
		private int type = 0;
		private int code = 0;
		
		public InputKey(int k, int t){
			key = k;
			type = t;
			//Creating pair for hashcode
			code = Integer.parseInt(Integer.toString(key) + Integer.toString(type));
		}
		
		public int getKey(){
			return key;
		}
		
		public int getType(){
			return type;
		}
		
		@Override
		public int hashCode(){
			return code;
		}
		
	}
	
	private HashMap<Integer, Integer> inputStates = new HashMap<Integer, Integer>();
	private HashMap<Integer, RunEvent> listeners = new HashMap<Integer, RunEvent>();
	
	//Type:   0 = type,  1 = press, 2 = release 
	//Key: equals KeyEvent keyCode
	public void addActionToKey(int key, int type, RunEvent e){
		
		InputKey input = new InputKey(key, type);
		
		listeners.put(input.hashCode(), e);
		//System.out.println("new " + input.hashCode());
	}
	
	private void handleInputEvent(InputKey e){
		
		//System.out.println("handle " + e.hashCode());
		
		RunEvent event = listeners.get(e.hashCode());
		if(event != null){
			event.run(this);
		}else{
			//System.out.println("Event action not found");
		}
	}
}
