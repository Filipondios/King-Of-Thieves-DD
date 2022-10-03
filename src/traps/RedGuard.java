package traps;

import java.awt.Image;

import javax.swing.ImageIcon;

import frames.MainFrame;

public class RedGuard extends Trap{

	public static ImageIcon renderizeTrapImage() {
		ImageIcon icon = new ImageIcon(Trap.commonPath+"RedGuard.gif"); 
		//icon = new ImageIcon(icon.getImage().getScaledInstance(MainFrame.TILE_SIDE, MainFrame.TILE_SIDE, Image.SCALE_DEFAULT));
		return icon;
	}
	
	@Override
	public boolean canRotate() {
		return false;
	}

	@Override
	public boolean canMove() {
		return true;
	}

}
