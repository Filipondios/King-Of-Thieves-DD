package frames;

import java.util.ArrayList;

import javax.swing.JPanel;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import filipondiosUtils.DragableLabel;

@SuppressWarnings("serial")
public class ContentPanel extends JPanel{
	
	private static ArrayList<DragableLabel> items = new ArrayList<>();
	
	public ContentPanel() {
		this.setSize(1050,670);
		this.setOpaque(false);      
	}
	
	public void addItem(String name, int width, int height) {
		 DragableLabel label = new DragableLabel();
		 label.setIcon(new FlatSVGIcon("svgImages/items/"+name+".svg",width,height));
		 add(label);
	}
}
