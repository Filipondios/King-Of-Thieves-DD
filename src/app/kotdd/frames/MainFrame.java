package app.kotdd.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import app.kotdd.panels.ImagesPanel;
import app.kotdd.panels.DessignPanel;

/**This will be the main Frame for the application. This window sets an area where the user can open different dungeons
 * and place traps images and other stuff into it. The frame its made by two "layers": The first one, at the background
 * is made up by 160 square panels, that makes the dungeon grid and blocks. The second one, at the top, is a panel where
 * the user can place different images, each one made with the class {@link app.kotdd.miscellaneous.DraggableLabel}. This frame
 * also have a menu-bar where the user can add Items and Traps images, import a dungeon and clear up the dungeon.
 * @author Filipondios, Hagernaut 
 * @version 03.12.2022**/
public class MainFrame extends JFrame {
	public static ArrayList<Integer> BOARD_COMPOSITION = new ArrayList<>(160); // Dungeon "tiles"
	JLayeredPane frame_layers = new JLayeredPane(); // Application frame layers: one for the images and other for the "tiles"
	DessignPanel blocks_panel = new DessignPanel(); // Panel filled up with 160 tiles = dungeon layout
	ImagesPanel images_panel = new ImagesPanel(); // Panel where all the traps and items images are placed
	
	/**Method that starts all the configurations for the Frame. **/
	public MainFrame() {
		setIconImage(new ImageIcon("resources/images/icon.gif").getImage());
		this.setTitle("King of Thieves Dungeon Designer");
		this.setSize(1050,695);
		this.setLayout(new BorderLayout());
		frame_layers.setSize(1039,650);
		blocks_panel.setBounds(0,0,1039,650);
		
		this.add(frame_layers);
		blocks_panel.setOpaque(true);		
		images_panel.setOpaque(false);
						
		frame_layers.add(blocks_panel,0,0);
		frame_layers.add(images_panel,1,0);
				
		this.setJMenuBar(createMenuBar());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		importDungeon("resources/data/binStoredBases/4.bs");
		blocks_panel.setScheme(BOARD_COMPOSITION);
	}

	/** Method that creates the menu-bar for the application */
	private JMenuBar createMenuBar() {
		/* * * * * Add the basic options to the menu-bar * * * * */
		JMenu options = new JMenu("Options");
		JMenu items = new JMenu("Add Dungeon Default Stuff");
		JMenu traps = new JMenu("Add Traps");
		
		/* * * * * Add sub-items to the first item of the menu-bar * * * * */
		JMenuItem create = new JMenuItem("Import dungeon (From ID number or name)");
		JMenuItem clear = new JMenuItem("Clear dungeon");
		options.add(create);
		options.add(clear);
		
		/* * * * * Make the items sub-menu * * * * */
		ActionListener items_listener = e -> {
			String id  = e.paramString();
			images_panel.addItem(id.substring(21,id.indexOf("w")-1));
			images_panel.doLayout();
		};
		
		String[] items_images = {"Totem","Door","Anti-Gravity","Platform","Trampoline"};
		
		for (String id : items_images) {
			JMenuItem item = new JMenuItem(id);
			item.addActionListener(items_listener);
			items.add(item);
		}
		
		/* * * * * Make the traps sub-menu * * * * */
		ActionListener traps_listener = e -> {
			String id  = e.paramString();
			images_panel.addTrap(id.substring(21,id.indexOf(",w")));
			images_panel.doLayout();
		};
		
		String[] traps_images = {"Saw","Red Guard","Cannon","Seeker Bird","Homing Cannon",
				"Spinner","Ricochet","Lil Scorcher","Warder","Roaster","BloodHound","Electro Cannon"};
				
		for (String id : traps_images) {
			JMenuItem trap = new JMenuItem(id);
			trap.addActionListener(traps_listener);
			traps.add(trap);
		}

		/* * * * * Add listeners to the options sub-menu * * * * */
		create.addActionListener(e -> {
			String str = JOptionPane.showInputDialog(null, "Type the base number/name to import.\n"
					+ "NOTE: If you want an empty dungeon, type 0.");
			importDungeon("resources/data/binStoredBases/"+str+".bs");
			blocks_panel.setScheme(BOARD_COMPOSITION);
		});
		
		clear.addActionListener(e -> {
			images_panel.removeAll();
			images_panel.repaint();
		});
		
		/* * * * * Adds all the options to the main menu-bar of the application * * * * */
		JMenuBar menu = new JMenuBar();
		menu.add(options);
		menu.add(items);
		menu.add(traps);
		return menu;
	}

	/** Method that imports (overrides) with a {@link ArrayList} that contains the "blocks"
	 * of a dungeon and was stored in a file in "resources/data/binBases" the
	 * current BOARD_COMPOSITION {@link ArrayList}. 
	 * @param path String that represents the name of the file where a dungeon is "saved".*/
	@SuppressWarnings("unchecked")
	private void importDungeon(String path) {
		try {
			ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(path)));
			BOARD_COMPOSITION = (ArrayList<Integer>) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
