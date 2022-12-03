package app.kotdd.panels;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import app.kotdd.frames.MainFrame;

/**Class that extends fom {@link JPanel} and represents a dungeon block.
 * @author Filipondios, Haggernaut
 * @version 03.12.2022*/
public final class TilePanel extends JPanel {
	
	/** Static variable that counts the rows and columns of the actual block in the dungeon. 
	* When a {@link DessignPanel} is created, 160 {@TilePanel}s are also created, and added
	* to itself. A proper way to control whether the block is part of the margin or the dungeon,
	* is controlling if the actual block is in a row or a column that is part of a margin of the dungeon.
	* Rows are storaged in tile.x and columns in tile.y */
	private static Point tile = new Point(0, 0);

	/** Main constructor. Creates a dungeon block by a integer that represents the position that this 
	* block will occupy in the {@link MainFrame} BOARD_COMPOSITION 
	* @param tileID Block id. */
	public TilePanel() {
		this.setBackground(assignColor());
		checkPositions();
		this.setBorder(new LineBorder(Color.BLACK));
	}
	
	/** Assign a color (white/black) depending on the position of this block in the {@link MainFrame}
	* BOARD_COMPOSITION, by the static cc variable that controls the rows and columns of the dungeon.*/
	private Color assignColor() {
		Color color = (tile.x == 0 || tile.x == 9 || tile.y == 0 || tile.y == 15)? Color.black : Color.white;
		MainFrame.BOARD_COMPOSITION.add((color.equals(Color.black))? 1 : 0);
		return color;
	}
	
	/** Method that sets the right rows and columns for the next block to be added to {@link DessignPanel}.
	* Because we add the blocks row by row in the {@link DessignPanel}, we need to change if the column
	* value is 15 (real row 16), to 0 and the columns to 0 too. Else, increase the column value. */
	private void checkPositions() {
		if(tile.y == 15) {
			tile.y = 0;
			tile.x++;
		} else tile.y++;
	}
}
