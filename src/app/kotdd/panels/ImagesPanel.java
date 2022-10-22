package app.kotdd.panels;

import java.awt.Dimension;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import app.kotdd.utils.DragableLabel;
import app.kotdd.utils.Pair;
import app.types.Item;
import app.types.Trap;

@SuppressWarnings("serial")
public class ImagesPanel extends JPanel{
	
	public static ArrayList<Item> ITEMS = new ArrayList<Item>(5); // ArrayList with every item data: width, height
	public static ArrayList<Trap> TRAPS = new ArrayList<Trap>(12); // ArrayList with every item data: width, height, can rotate, etc.
	
	public ImagesPanel() {
		this.setSize(1050, 670);
		this.setOpaque(false);
		importTrapsPrefferences();
		importItemsPrefferences();
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
		Pair<?>[] target = (flag)? ITEMS : TRAPS;
		for (Pair<?, ?> pair : target)
			if(pair.equals(tmp)) return (Pair<String, Dimension>) pair;
		return null;
	}
	
	private void importTrapsPrefferences() {
		try {
			JSONParser parser = new JSONParser();
			FileReader reader = new FileReader("resources/data/json/traps_config.json");
			Object obj = parser.parse(reader);
			JSONArray traps = (JSONArray) obj;
			
			for (Object trap : traps) {
				JSONObject tmp_trap = (JSONObject) trap;
				tmp_trap = (JSONObject) tmp_trap.get("Trap");
							
				JSONArray dimension_array = (JSONArray) tmp_trap.get("dimension");
				int[] dimension = {((Long) dimension_array.get(0)).intValue(), ((Long) dimension_array.get(1)).intValue()};
				
				TRAPS.add(new Trap((boolean) tmp_trap.get("can_move"), (boolean) tmp_trap.get("can_rotate"), 
						(String) tmp_trap.get("trap_id"), dimension));
			}
		}catch(IOException | ParseException e) { }
	}
	
	private void importItemsPrefferences() {
		try {
			JSONParser parser = new JSONParser();
			FileReader reader = new FileReader("resources/data/json/items_config.json");
			Object obj = parser.parse(reader);
			JSONArray traps = (JSONArray) obj;
			
			for (Object trap : traps) {
				JSONObject tmp_trap = (JSONObject) trap;
				tmp_trap = (JSONObject) tmp_trap.get("Item");
							
				JSONArray dimension_array = (JSONArray) tmp_trap.get("dimension");
				int[] dimension = {((Long) dimension_array.get(0)).intValue(), ((Long) dimension_array.get(1)).intValue()};
				
				ITEMS.add(new Item((String) tmp_trap.get("item_id"), dimension));
			}
		}catch(IOException | ParseException e) { }
	}
}
