package traps;

import javax.swing.ImageIcon;

public class RedGuard extends Trap{

	public static final ImageIcon icon = new ImageIcon("resources/images/traps/rg.png");
	
	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean canRotate() {
		return false;
	}
}
