package app.kotdd.main;

import com.formdev.flatlaf.FlatDarkLaf;
import app.kotdd.frames.MainFrame;

/**This is the main class of the Java application. Obviously, this class contains
 * the public static void main(String[] args) method that permits the application run.
 * The only thing that this method do is calling the {@link MainFrame} class for create
 * the first frame of the program, with the editor panel. 
 * <br>
 * Note that the frame created by {@link MainFrame} is linked to the rest of frames that
 * are present in the entire program. 
 * @author Filipondios, Hagernaut
 * @see MainFrame
 * @version 06.10.2022*/
public class RunClass {
	
	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
			new MainFrame();
		} catch (Exception ex) {
			/*Could do a multi try-catch with ClassNotFoundException, InstantiationException,
			 * IllegalAccessException, UnsupportedLookAndFeelException, but it's cleaner to
			 * do a single try-catch with the general Exception because we know that the only thing
			 * we are specting is to get the Windows Theme. Otherwise, the theme will be the default (Metal).*/
			ex.printStackTrace();
		}
	}
}