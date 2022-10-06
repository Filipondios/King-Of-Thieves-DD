package traps;

import javax.swing.ImageIcon;

public class ECannon extends Trap{
	
	public static final ImageIcon icon = new ImageIcon("resources/images/traps/ecannon.png");

	@Override
	public boolean canMove() {
		return false;
	}

	@Override
	public boolean canRotate() {
		return false;
	}
		
}
