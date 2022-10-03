package traps;

import javax.swing.ImageIcon;

public class Saw extends Trap{
	
	private static ImageIcon icon = new ImageIcon(commonPath+"saw.png");
	
	public static ImageIcon renderizeTrapImage() {
		if(Trap.trapsNumber<4) {
			Trap.trapsNumber++;
			return icon;
		}return null;
	}
	
	@Override
	public boolean canRotate() {
		return false;
	}

	@Override
	public boolean canMove() {
		return false;
	}
}
