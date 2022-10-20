package app.kotdd.panels;

import java.awt.Dimension;
import javax.swing.JPanel;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import app.kotdd.utils.DragableLabel;
import app.kotdd.utils.Pair;

@SuppressWarnings("serial")
public class ImagesPanel extends JPanel{
	
	public ImagesPanel() {
		this.setSize(1050,670);
		this.setOpaque(false);
	}
	
	/**Method that adds to the {@link ImagesPanel} a item or a trap dragable image.
	 * @param name String that represents the name of the trap or item to add.
	 * @param flag Boolean value that indicates whether the target its a trap
	 * or a item image. True if the target is a item, flase if a trap is it. */
	public void add(String name, boolean flag) {
		DragableLabel label = new DragableLabel();
		Pair<String,Dimension> item = find(name, flag);
		label.setIcon(new FlatSVGIcon("svgImages/"+ (flag?"items":"traps") +"/"+name+".svg",
				item.getValue().width, item.getValue().height));
		add(label);
	}
	
	private Pair<String,Dimension> find(String key, Boolean flag){
		Pair<String,Dimension> tmp = new Pair<String, Dimension>(key, null);
		Pair<?,?>[] target = (flag)? ITEMS_DIM : TRAPS_DIM;
		for (Pair<?, ?> pair : target)
			if(pair.equals(tmp)) return (Pair<String, Dimension>) pair;
		return null;
	}
	
	public static final Pair<?,?>[] ITEMS_DIM = {
			new Pair<String,Dimension>("Totem", new Dimension(130,160)),
			new Pair<String,Dimension>("Door", new Dimension(120,150)),
			new Pair<String,Dimension>("Gravity Switch", new Dimension(140,140)),
			new Pair<String,Dimension>("Platform", new Dimension(130,20)),
			new Pair<String,Dimension>("Trampoline", new Dimension(140,36)),
	};
	
	public static final Pair<?,?>[] TRAPS_DIM = {
			new Pair<String,Dimension>("Saw", new Dimension(70,70)),
	};
}
