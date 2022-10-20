package app.kotdd.panels;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import app.kotdd.frames.MainFrame;
import app.kotdd.utils.Pair;

@SuppressWarnings("serial")
public final class TilePanel extends JPanel implements MouseListener{
	
	private static Pair<Integer, Integer> cc = new Pair<Integer,Integer>(0,0);
	private int tileID=0;
		
	public TilePanel(int tileID) {
		this.tileID = tileID;
		this.setBackground(assignColor());
		checkPositions();
		this.setBorder(new LineBorder(Color.BLACK));
		this.addMouseListener(this);
	}
	
	private Color assignColor() {
		Color color = (cc.getKey()==0 || cc.getKey() ==9 || cc.getValue()==0 || cc.getValue()==15)? Color.black : Color.white;
		MainFrame.BOARD_COMPOSITION.add((color.equals(Color.black))? 1: 0);
		return color;
	}
	
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