package traps;

import javax.swing.ImageIcon;

public class BHound extends Trap{
	
	public static final ImageIcon icon = new ImageIcon("resources/images/traps/bh.png");

	@Override
	public boolean canMove() {
		return false;
	}

	@Override
	public boolean canRotate() {
		return true;
	}
		
}
