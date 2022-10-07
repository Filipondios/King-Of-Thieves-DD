package traps;

import javax.swing.ImageIcon;

public class Ricochet extends Trap{
	
	public static final ImageIcon icon = new ImageIcon("resources/images/traps/rico.png");

	@Override
	public boolean canMove() {
		return false;
	}

	@Override
	public boolean canRotate() {
		return true;
	}
		
}