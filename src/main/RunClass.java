package main;

import java.io.IOException;
import frames.InitFrame;
/**This is the main class of the Java application. Obviously, this class contains
 * the public static void main(String[] args) method that permits the application run.
 * The only thing that this method do is calling to the class {@link InitFrame} to create
 * the first frame of the program, the logo and the progressbar. 
 * <br>
 * Note that the frame created by {@link InitFrame} is linked to the rest of frames that
 * are present in the entire program. 
 * @author Filipondios, Hagernaut
 * @see InitFrame
 * @version 19.08.2022*/
public class RunClass {
	public static void main(String[] args) throws IOException {	
		new InitFrame();
	}
}
