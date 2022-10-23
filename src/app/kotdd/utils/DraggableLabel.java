package app.kotdd.utils;

import app.types.Trap;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public class DraggableLabel extends JLabel {

	private final Trap trap_to_render;

	public DraggableLabel() {
		this.trap_to_render = null;
		initializeProperties();
	}
	
	public DraggableLabel(Trap trap_to_render) {
		this.trap_to_render = trap_to_render;
		initializeProperties();
	}
	
	private void initializeProperties() {
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));

		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				setLocation(getX() + e.getX() - getWidth() / 2, getY() + e.getY() - getHeight() / 2);
			}
		});

		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(trap_to_render != null && trap_to_render.can_Rotate()) {
					RotatedIcon rotated = new RotatedIcon(getIcon(), RotatedIcon.Rotate.DOWN);
					setIcon(rotated);
				}
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
	}
}