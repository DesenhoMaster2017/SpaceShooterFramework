import jplay.Window; 
import jplay.GameImage;

public class Main {

	public static void main(String[] args) {
		System.out.println("It's running!");
		
		//It creates an windows with 800 pixels of width and 600 pixels of height   
        Window window = new Window(800,600);
        
        //Loading backgound image
        GameImage background = new GameImage("assets/img/temp_background.png");
        
        //Game main loop
        while(true) {
        		//Draw the backgound
        		background.draw();
        		
        		//Refresh the screen
        		window.update();
        }
	}

}
