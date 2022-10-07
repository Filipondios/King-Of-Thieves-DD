package frames;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public final class BoardPanel extends JPanel {
	
	private TilePanel[] tiles = new TilePanel[160];
	
	public BoardPanel() {
		super(new GridLayout(10,16));
		for (int i = 0; i < 160; i++) {
			tiles[i] = new TilePanel(i);
			this.add(tiles[i]);
		}
	}
	
	public void setScheme(ArrayList<Integer> scheme) {
		for (int i = 0; i < scheme.size(); i++) {
			if(scheme.get(i)==1) tiles[i].setBackground(Color.black);
			else tiles[i].setBackground(Color.white);
		}
	}
}