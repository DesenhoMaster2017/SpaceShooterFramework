import jplay.Window; 
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Sprite; 



public class Main {
	//Window dimensions
	public static final int HEIGHT = 600;
	public static final int WIDTH = 800;

	public static void main(String[] args) {
		System.out.println("It's running!");

		//It creates an windows with 800 pixels of width and 600 pixels of height   
		Window window = new Window(WIDTH,HEIGHT);

		//Loading backgound image
		GameImage background = new GameImage("assets/img/temp_background.png");

		//Creating player sprite
		Sprite player = new Sprite("assets/img/temp_player.png");
		//Putting player on the center-bottom of the screen
		player.x = WIDTH/2 - player.width/2;
		player.y = HEIGHT - player.height; 

		//Game main loop
		while(true) {
			//Draw the images for the game
			background.draw();
			player.draw();

			//Player movement
			player.moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, 4);//velocity = 1
			player.moveY(Keyboard.UP_KEY, Keyboard.DOWN_KEY, 4);//velocity = 1
			
			//Refresh the screen
			window.update();
		}
	}

}
