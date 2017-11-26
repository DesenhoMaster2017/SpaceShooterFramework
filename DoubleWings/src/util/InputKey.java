package util;

public class InputKey {
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
