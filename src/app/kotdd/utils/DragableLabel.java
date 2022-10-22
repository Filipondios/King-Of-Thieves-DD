package app.kotdd.utils;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DragableLabel extends JLabel {
	
	public DragableLabel() {		
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e){
				setLocation(getX()+e.getX()-getWidth()/2,getY()+e.getY()-getHeight()/2);
			}
		});
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RotatedIcon rotated =  new RotatedIcon(getIcon(), RotatedIcon.Rotate.DOWN);
				setIcon(rotated);
			}
			
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
	}
}