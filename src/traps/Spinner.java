package traps;

import javax.swing.ImageIcon;

public class Spinner extends Trap{
	
	public static final ImageIcon icon = new ImageIcon("resources/images/traps/spinner.png");

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean canRotate() {
		return false;
	}
		
}
