package app.kotdd.panels;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import app.kotdd.types.Item;
import app.kotdd.types.Trap;
import app.kotdd.utils.DraggableLabel;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ImagesPanel extends JPanel{
	
	public static ArrayList<Item> ITEMS = new ArrayList<>(5); // ArrayList with every item data: width, height
	public static ArrayList<Trap> TRAPS = new ArrayList<>(12); // ArrayList with every item data: width, height, can rotate, etc.
	
	public ImagesPanel() {
		this.setSize(1050, 670);
		this.setOpaque(false);
		importTrapsPreferences();
		importItemsPreferences();
	}

	public void addItem(String id){
		Item tmp = new Item(id,null);
		for (Item item : ITEMS) {
			if(item.equals(tmp)){
				DraggableLabel label = new DraggableLabel();
				label.setIcon(new FlatSVGIcon("svgImages/items/"+id+".svg",item.getWidth(),item.getHeight()));
				this.add(label);
				break;
			}
		}
	}

	public void addTrap(String id) {
		Trap tmp = new Trap(false,false,id,null);
		for (Trap trap : TRAPS) {
			if(trap.equals(tmp)){
				DraggableLabel label = new DraggableLabel(trap);
				label.setIcon(new FlatSVGIcon("svgImages/traps/"+id+".svg",trap.getWidth(),trap.getHeight()));
				this.add(label);
				break;
			}
		}
	}
	private void importTrapsPreferences() {
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
		}catch(IOException | ParseException e) { e.printStackTrace(); }
	}
	
	private void importItemsPreferences() {
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
		}catch(IOException | ParseException e) { e.printStackTrace(); }
	}
}
