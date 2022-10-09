package filipondiosUtils;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DragableLabel extends JLabel {
	
	public DragableLabel() {		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			
			public void mouseDragged(MouseEvent e){
				setLocation(getX()+e.getX()-getWidth()/2,getY()+e.getY()-getHeight()/2);
			}
		};
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.addMouseMotionListener(mouseAdapter);
	}
}