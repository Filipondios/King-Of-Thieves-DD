package traps;

import javax.swing.ImageIcon;

public class Warder extends Trap{
	
	public static final ImageIcon icon = new ImageIcon("resources/images/traps/warder.png");

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean canRotate() {
		return true;
	}
		
}
