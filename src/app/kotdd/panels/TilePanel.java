package app.kotdd.panels;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import app.kotdd.frames.MainFrame;
import app.kotdd.utils.Pair;

@SuppressWarnings("serial")
/**Class that extends fom {@link JPanel} and represents a dungeon block.
 * @author Filipondios, Haggernaut
 * @version 23.10.2022*/
public final class TilePanel extends JPanel implements MouseListener{
	
	/** Static variable that counts the rows and columns of the actual block in the dungeon. 
	* When a {@link LayoutPanel} is created, 160 {@TilePanel}s are also created, and added
	* to itself. A proper way to control whether the block is part of the margin or the dungeon,
	* is controlling if the actual block is in a row or a column that is part of a margin of the dungeon.
	* Rows are storaged in cc.key and columns in cc.value */
	private static Pair<Integer, Integer> cc = new Pair<Integer,Integer>(0,0);
	/** Integer that represents the position of this block position in the {@link MainFrame} BOARD_COMPOSITION */
	private int tileID = 0;

	/** Main constructor. Creates a dungeon block by a integer that represents the position that this 
	* block will occupy in the {@link MainFrame} BOARD_COMPOSITION 
	* @param tileID Block id. */
	public TilePanel(int tileID) {
		this.tileID = tileID;
		this.setBackground(assignColor());
		checkPositions();
		this.setBorder(new LineBorder(Color.BLACK));
		this.addMouseListener(this);
	}
	
	/** Assign a color (white/black) depending on the position of this block in the {@link MainFrame}
	* BOARD_COMPOSITION, by the static cc variable that controls the rows and columns of the dungeon.*/
	private Color assignColor() {
		Color color = (cc.getKey()==0 || cc.getKey() ==9 || cc.getValue()==0 || cc.getValue()==15)? Color.black : Color.white;
		MainFrame.BOARD_COMPOSITION.add((color.equals(Color.black))? 1 : 0);
		return color;
	}
	
	/** Method that sets the right rows and columns for the next block to be added to {@link LayoutPanel}.
	* Because we add the blocks row by row in the {@link LayoutPanel}, we need to change if the column
	* value is 15 (real row 16), to 0 and the columns to 0 too. Else, increase the column value. */
	private void checkPositions() {
		if(cc.getValue()==15) {
			cc.setValue(0);
			cc.setKey(cc.getKey()+1);
		} else cc.setValue(cc.getValue()+1);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.setBackground(Color.black);
		MainFrame.BOARD_COMPOSITION.set(tileID, 1);
	}

	public void mousePressed(MouseEvent e) {/*Nothing TODO*/}
	public void mouseReleased(MouseEvent e) {/*Nothing TODO*/}
	public void mouseEntered(MouseEvent e) {/*Nothing TODO*/}
	public void mouseExited(MouseEvent e) {/*Nothing TODO*/}
}
