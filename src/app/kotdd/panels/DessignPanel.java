package app.kotdd.panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/** Class that extends from JPanel. This panel is made up with 160 blocks, that makes the
 * representation of a dungeon, with a extra block border.
 * @author Filipondios, Haggernaut 
 * @version 03.12.2022*/
public final class DessignPanel extends JPanel {
	
	/** Array of {@link TilePanel}s that storages all the blocks that a dungeon contains.*/
	private TilePanel[] tiles = new TilePanel[160];
	
	/** Constructor that adds 160 {@link TilePanel}s to the current {@link DessignPanel} object.*/
	public DessignPanel() {
		super(new GridLayout(10,16));
		for (int i = 0; i < 160; i++) {
			tiles[i] = new TilePanel();
			this.add(tiles[i]);
		}
	}
	
	/** Method that sets new colors to the {@link TilePanel}s storaged in the 
	 * tiles array, by an {@link ArrayList} passed in the parameters. 
	 * @param scheme {@link ArrayList} of integers (filled with 1s and 0s) that indicates
	 * the color of each block storaged in the tiles array. 1 = black, 0 = white background*/
	public void setScheme(ArrayList<Integer> scheme) {
		for (int i = 0; i < scheme.size(); i++) {
			if(scheme.get(i)==1) tiles[i].setBackground(Color.black);
			else tiles[i].setBackground(Color.white);
		}
	}
}
