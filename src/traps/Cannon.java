package traps;

import java.awt.Image;
import javax.swing.ImageIcon;
import frames.MainFrame;

public class Cannon extends Trap{
	
	public static ImageIcon renderizeTrapImage() {
		ImageIcon icon = new ImageIcon(Trap.commonPath+"Cannon.gif"); 
		//icon = new ImageIcon(icon.getImage().getScaledInstance(MainFrame.TILE_SIDE, MainFrame.TILE_SIDE, Image.SCALE_DEFAULT));
		return icon;
	}
	
	@Override
	public boolean canRotate() {
		return true;
	}

	@Override
	public boolean canMove() {
		return false;
	}
}
