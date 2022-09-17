package FilipondiosUtils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class WindowUtils {
	
	public static final int screenWidth = getScreenWidth();
	public static final int screenHeight = getScreenHeight();
	
	
	private final static int getScreenWidth() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen  = tk.getScreenSize();
		
		return (int) screen.getWidth();
	};
	
	private final static int getScreenHeight() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen  = tk.getScreenSize();
		return (int) screen.getHeight();
	};	
}
