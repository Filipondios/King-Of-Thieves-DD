package app.types;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class runClass {
	public static void main(String[] args) throws IOException, ParseException {
		leer();
	}
	
	private static void leer() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("src/config.json");
		Object obj = parser.parse(reader);
		
		JSONArray traps = (JSONArray) obj; //El json es un array de objetos
		for (Object trap : traps) {
			JSONObject tmp_trap = (JSONObject) trap;
			tmp_trap = (JSONObject) tmp_trap.get("Trap");
						
			JSONArray dimension_array = (JSONArray) tmp_trap.get("dimension");
			int[] dimension = {((Long) dimension_array.get(0)).intValue(), ((Long) dimension_array.get(1)).intValue()};
			
			Trap nsnull = new Trap((boolean) tmp_trap.get("can_move"), (boolean) tmp_trap.get("can_rotate"), 
					(String) tmp_trap.get("trap_id"), dimension);
		}
	}
}
