package app.kotdd.miscellaneous;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * Class that makes a JLabel draggable. Useful to make traps and icons images draggable
 * at the applicattion frame.
 * @author Filipondios
 * @version 03.12.2022
 */
public final class DraggableLabel extends JLabel {

	/** Trap to render at a Jlabel icon. If the object to render is a item,
	 * this propierty must be null. */
	@SuppressWarnings("unused")
	private final Trap trap_to_render;

	/** Creates a simple DragableLabel, thought for the items representation, not traps. */
	public DraggableLabel() {
		this.trap_to_render = null;
		initializeProperties();
	}

	/**
	 * Creates a DragableLabel, made up for a trap representation.
	 * @param trap_to_render Trap to render.
	 */
	public DraggableLabel(Trap trap_to_render) {
		this.trap_to_render = trap_to_render;
		initializeProperties();
		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(trap_to_render.can_Rotate()) {
					RotatedIcon rotated = new RotatedIcon(getIcon());
					setIcon(rotated);
				}
			}
		});
	}

	/** Initializes the common properties of a draggable label that is used
	 * to represent a trap or a item. */
	private void initializeProperties() {
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));

		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) { // Make the JLabel draggable
				setLocation(getX() + e.getX() - getWidth() / 2, getY() + e.getY() - getHeight() / 2);
			}
		});
	}

	/**
	 * Class created to render the icon of every trap and item icon. This class
	 * calculates a 90 degrees rotated icon form a given one. Useful when cliking on 
	 * a {@link DraggableLabel} and a trap is rotable.
	 * @author Filipondios
	 */
	private final class RotatedIcon implements Icon{

		/** Desired icon to be rotated. */
		private final Icon icon;

		/** Creates and initializes the icon to be rotated.
		 * @param icon Icon to be rotated */
		public RotatedIcon(Icon icon) {
			this.icon = icon;
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2 = (Graphics2D) g.create();

			int cWidth = icon.getIconWidth()>>1; // >>1 its the same as /2
			int cHeight = icon.getIconHeight()>>1;

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setClip(x, y, getIconWidth(), getIconHeight());
			g2.translate((getIconWidth() - icon.getIconWidth()) / 2, (getIconHeight() - icon.getIconHeight()) / 2);
			g2.rotate(Math.toRadians(90), x + cWidth, y + cHeight);
			icon.paintIcon(c, g2, x, y);
			
			g2.dispose();
		}

		@Override
		public int getIconWidth() {
			return icon.getIconWidth();
		}

		@Override
		public int getIconHeight() {
			return icon.getIconHeight();
		}

	}
}