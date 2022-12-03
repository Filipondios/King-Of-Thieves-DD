package app.kotdd.panels;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import app.kotdd.miscellaneous.DraggableLabel;
import app.kotdd.miscellaneous.Item;
import app.kotdd.miscellaneous.Trap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** Class that extends from JPanel. This panel is made for placing the different traps
 * and items labels that the user puts in the frame.
 * @author Filipondios 
 * @version 03.12.2022*/
public class ImagesPanel extends JPanel{
	
	public static ArrayList<Item> ITEMS = new ArrayList<>(5); // ArrayList with every item data: width, height
	public static ArrayList<Trap> TRAPS = new ArrayList<>(12); // ArrayList with every item data: width, height, can rotate, etc.
	
	/** Creates a Images Panel. Then catches all the config data from the 
	 * <code>resources/data/jsonAppConfig/</code> dir. The config is saved in those files
	 * because thats the best way for the user to adjust the items and traps render
	 * if there was a problem with that. */
	public ImagesPanel() {
		this.setSize(1050, 670);
		this.setOpaque(false);
		importTrapsPreferences();
		importItemsPreferences();
	}

	/**
	 * Adds a item to the panel.
	 * @param id ID of the item to add.
	 */
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

	/**
	 * Adds a trap to the panel.
	 * @param id ID of the trap to add.
	 */
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

	/** Imports the traps config from the file <code>resources/data/jsonAppConfig/traps_config.json</code> */
	private void importTrapsPreferences() {
		try {
			JSONParser parser = new JSONParser();
			FileReader reader = new FileReader("resources/data/jsonAppConfig/traps_config.json");
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
	
	/** Imports the items config from the file <code>resources/data/jsonAppConfig/items_config.json</code> */
	private void importItemsPreferences() {
		try {
			JSONParser parser = new JSONParser();
			FileReader reader = new FileReader("resources/data/jsonAppConfig/items_config.json");
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
